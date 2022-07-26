package tr.com.id3.news.newsfetch.services;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tr.com.id3.news.newsfetch.model.Article;

import java.lang.reflect.Array;
import java.util.ArrayList;

@Service("rst")
public class RestTemplateFetch {
    @Value("${connection.url}")
    private String url;
    @Value("${connection.apiKey}")
    private String apiKey;
    @Value("${connection.parameters}")
    private String parameters;

    private ArrayList<Article> arr = new ArrayList<Article>();

    public ResponseEntity<String> connect(){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(this.url + this.parameters + this.apiKey,String.class);
        return response;
    }
    public ArrayList<Article> fetchData(ResponseEntity<String> response) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response.getBody()); // root pathi araştır
        root = root.get("articles");
        for(int i = 0;i < root.size();i++){
            Article article = mapper.readValue(root.get(i).toString(),Article.class);
            this.arr.add(article);
        }
        return this.arr;
    }
}
