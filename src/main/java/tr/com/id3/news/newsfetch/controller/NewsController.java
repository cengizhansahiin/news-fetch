package tr.com.id3.news.newsfetch.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tr.com.id3.news.newsfetch.services.BrokerControllerService;

@RestController
@RequestMapping
@Slf4j
public class NewsController {
    @Autowired
    private BrokerControllerService fetchService;

    @PostMapping("/start/fetchService")
    public void triggerJob() throws JsonProcessingException {
        log.warn("Triggered from SwaggerUI.");
        this.fetchService.executeFetchNews();
        log.warn(new ResponseEntity<>("Job completed.", HttpStatus.OK).toString());
    }

}
