package tr.com.id3.news.newsfetch;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.ResponseEntity;
import tr.com.id3.news.newsfetch.services.NewsApp;
import tr.com.id3.news.newsfetch.services.RestTemplateFetch;

import java.util.logging.Logger;

@SpringBootApplication
public class NewsFetchApplication {

	public static void main(String[] args) throws JsonProcessingException {
		SpringApplication.run(NewsFetchApplication.class, args);
		NewsApp na = new NewsApp();
		na.run();

	}
}
