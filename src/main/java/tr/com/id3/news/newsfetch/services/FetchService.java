package tr.com.id3.news.newsfetch.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
@Service
public class FetchService {
    public void executeFetchNews() throws JsonProcessingException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("tr.com.id3.news.newsfetch");
        RestTemplateFetch rft = context.getBean(RestTemplateFetch.class);
        ResponseEntity<String> response = rft.connect();
        rft.fetchData(response);
        context.close();
    }
}
