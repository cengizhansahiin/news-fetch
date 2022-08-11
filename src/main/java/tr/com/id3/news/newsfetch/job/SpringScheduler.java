package tr.com.id3.news.newsfetch.job;


import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import tr.com.id3.news.newsfetch.services.BrokerControllerService;

@Slf4j
@Component
public class SpringScheduler {
    @Autowired
    private BrokerControllerService fetchService;
    @Scheduled(fixedDelay = 5*60*1000,initialDelay = 1000)
    public void scheduledJob() throws JsonProcessingException {
        log.info("Triggered from scheduler.");
        this.fetchService.executeFetchNews();
        log.info(new ResponseEntity<>("Job completed.", HttpStatus.OK).toString());
    }

}
