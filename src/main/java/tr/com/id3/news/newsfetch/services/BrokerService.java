package tr.com.id3.news.newsfetch.services;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tr.com.id3.news.common.model.ArticleDTO;

@Service("rst")
public class BrokerService {
    @Value("${connection.url}")
    private String url;
    @Value("${connection.apiKey}")
    private String apiKey;
    @Value("${connection.parameters}")
    private String parameters;

    @Autowired
    ActiveMQService activeMQService;


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
            ArticleDTO articleDTO = new ArticleDTO();
            for(int j = 1;j<root.get(i).size();j++){
                articleDTO.setAuthor(root.get(i).get("author").toString());
                articleDTO.setTitle(root.get(i).get("title").toString());
                articleDTO.setDescription(root.get(i).get("description").toString());
                articleDTO.setUrl(root.get(i).get("url").toString());
                articleDTO.setUrlToImage(root.get(i).get("urlToImage").toString());
                articleDTO.setPublishedAt(root.get(i).get("publishedAt").toString());
                articleDTO.setContent(root.get(i).get("content").toString());
            }
            activeMQService.sendToQueue(articleDTO);
        }
    }
}
