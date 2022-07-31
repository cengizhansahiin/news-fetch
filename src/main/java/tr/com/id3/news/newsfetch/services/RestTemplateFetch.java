package tr.com.id3.news.newsfetch.services;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tr.com.id3.news.newsfetch.model.Article;

import javax.jms.TextMessage;
import java.util.ArrayList;

@Service("rst")
public class RestTemplateFetch {
    @Value("${connection.url}")
    private String url;
    @Value("${connection.apiKey}")
    private String apiKey;
    @Value("${connection.parameters}")
    private String parameters;
    @Autowired
    JmsTemplate jmsTemplate;
    @Value("${spring.activemq.queue}")
    String queue;

    public ResponseEntity<String> connect(){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(this.url + this.parameters + this.apiKey,String.class);
        return response;
    }
    public void fetchData(ResponseEntity<String> response) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response.getBody());
        root = root.get("articles");
        for(int i = 0;i < root.size();i++){
            Article article = new Article();
            for(int j = 1;j<root.get(i).size();j++){
                article.setAuthor(root.get(i).get("author").toString());
                article.setTitle(root.get(i).get("title").toString());
                article.setDescription(root.get(i).get("description").toString());
                article.setUrl(root.get(i).get("url").toString());
                article.setUrlToImage(root.get(i).get("urlToImage").toString());
                article.setPublishedAt(root.get(i).get("publishedAt").toString());
                article.setContent(root.get(i).get("content").toString());
            }
            try {
                String articleObject = new ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(article);
                jmsTemplate.send(queue, messageCreator -> {
                    TextMessage message = messageCreator.createTextMessage();
                    message.setText(articleObject);
                    return message;
                });
            }
            catch (Exception ex){ System.err.println("ERROR sending message! " + ex.toString());}
        }
    }
}
