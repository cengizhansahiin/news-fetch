package tr.com.id3.news.newsfetch.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tr.com.id3.news.newsfetch.services.FetchService;

@RestController
@RequestMapping
public class NewsController {
    @Autowired
    private FetchService fetchService;

    @PostMapping("/start/fetchService")
    public void triggerJob() throws JsonProcessingException {
        this.fetchService.executeFetchNews();
    }

}
