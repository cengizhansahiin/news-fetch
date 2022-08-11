package tr.com.id3.news.newsfetch.Repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Repository;
import tr.com.id3.news.common.model.ArticleDTO;

import javax.jms.ObjectMessage;

@Repository
public class ProducerRepository {

    @Autowired
    private JmsTemplate jmsTemplate;
    @Value("${spring.activemq.queue}")
    private String queue;

    public void sendMessage(ArticleDTO articleDTO){
        jmsTemplate.send("News", session -> {
            ObjectMessage objectMessage = session.createObjectMessage();
            objectMessage.setObject(articleDTO);
            return objectMessage;
        });

    }
}
