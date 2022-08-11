package tr.com.id3.news.newsfetch.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
@Service
@Slf4j
public class BrokerControllerService {
    public void executeFetchNews() throws JsonProcessingException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("tr.com.id3.news.newsfetch");
        BrokerService rft = context.getBean(BrokerService.class);
        log.warn("Trying to connect to target url.");
        try {
            ResponseEntity<String> response = rft.connect();
            log.info(new ResponseEntity<>("Connection successfull.",HttpStatus.OK).toString());
            log.info("Started to fetch data.");
            try {
                rft.fetchData(response);
                log.info(response.toString());
            }
            catch (Exception e){
                log.error(e.toString());
            }
            }
        catch (Exception e){
            log.error(new ResponseEntity<>("Failed to connect target URL.",HttpStatus.BAD_REQUEST).toString());
        }
        context.close();
    }
}
