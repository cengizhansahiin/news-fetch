package tr.com.id3.news.newsfetch.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tr.com.id3.news.newsfetch.model.Article;

import java.util.ArrayList;
import java.util.logging.Logger;
@Service
public class FetchService {
    ArrayList<Article> arr = new ArrayList<Article>();
    private Logger logger = Logger.getLogger(FetchService.class.getName());
    public void executeFetchNews() throws JsonProcessingException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("tr.com.id3.news.newsfetch");
        RestTemplateFetch rft = context.getBean(RestTemplateFetch.class);
        ResponseEntity<String> response = rft.connect();
        arr.addAll(rft.fetchData(response));
        for(int i = 0;i < arr.size();i++){
            logger.info("Author: " + arr.get(i).getAuthor());
            logger.info("Title: " + arr.get(i).getTitle());
            logger.info("Description: " + arr.get(i).getDescription());
            logger.info("Url: " + arr.get(i).getUrl());
            logger.info("UrlToImage: " + arr.get(i).getUrlToImage());
            logger.info("Published At: " + arr.get(i).getPublishedAt());
            logger.info("Content: " + arr.get(i).getContent());
        }
        logger.info("Sum of fetched news: " + String.valueOf(arr.size()));
        context.close();
    }
}
