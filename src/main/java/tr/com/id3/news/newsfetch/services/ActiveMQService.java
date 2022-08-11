package tr.com.id3.news.newsfetch.services;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tr.com.id3.news.newsfetch.Repository.ProducerRepository;
import tr.com.id3.news.common.model.ArticleDTO;

@Service
@Slf4j
public class ActiveMQService {

    @Autowired
    private ProducerRepository producerRepository;
    public void sendToQueue(ArticleDTO articleDTO) {
        log.info("Sending data to queue.");
        try {
            producerRepository.sendMessage(articleDTO);
            log.info(new ResponseEntity<>("Successfully sended.", HttpStatus.OK).toString());
        }
        catch (Exception e){
            log.error(e.toString());
        }
    }
}
