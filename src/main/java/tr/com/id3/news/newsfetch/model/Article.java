package tr.com.id3.news.newsfetch.model;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;

@Entity
@Data
public class Article {

    private JsonNode source;
    private String author;
    private String title;
    private String description;
    private String url;
    private String urlToImage;
    private String publishedAt;
    private String content;

}
