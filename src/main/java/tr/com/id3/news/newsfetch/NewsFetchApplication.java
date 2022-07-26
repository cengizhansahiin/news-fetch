package tr.com.id3.news.newsfetch;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class NewsFetchApplication {

	public static void main(String[] args) throws JsonProcessingException {
		SpringApplication.run(NewsFetchApplication.class, args);
	}
}
