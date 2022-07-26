package tr.com.id3.news.newsfetch.job;


import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import tr.com.id3.news.newsfetch.model.Article;
import tr.com.id3.news.newsfetch.services.FetchService;
import tr.com.id3.news.newsfetch.services.RestTemplateFetch;

import java.util.ArrayList;
import java.util.logging.Logger;



@Component
public class ScheduledFetching {
    @Autowired
    private FetchService fetchService;
    @Scheduled(fixedDelay = 5*60*1000,initialDelay = 1000)
    public void scheduledJob() throws JsonProcessingException {
        this.fetchService.executeFetchNews();
    }

}
