package tr.com.id3.news.newsfetch.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tr.com.id3.news.newsfetch.NewsFetchApplication;
import tr.com.id3.news.newsfetch.model.Article;

import java.util.ArrayList;
import java.util.logging.Logger;

@Service
public class NewsApp {
    Logger logger = Logger.getLogger(NewsFetchApplication.class.getName());

    public void run() throws JsonProcessingException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("tr.com.id3.news.newsfetch");
        RestTemplateFetch rft = context.getBean(RestTemplateFetch.class);
        ResponseEntity<String> response = rft.connect();
        ArrayList<Article> data = rft.fetchData(response);
        for(int i = 0;i < data.size();i++){
            logger.info(data.get(i).getAuthor());
            logger.info(data.get(i).getTitle());
            logger.info(data.get(i).getDescription());
            logger.info(data.get(i).getUrl());
            logger.info(data.get(i).getUrlToImage());
            logger.info(data.get(i).getPublishedAt());
            logger.info(data.get(i).getContent());
        }
        context.close();
    }
}
