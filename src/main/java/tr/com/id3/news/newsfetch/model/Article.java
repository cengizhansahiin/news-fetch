package tr.com.id3.news.newsfetch.model;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;


@Data
public class Article {

    private String author;
    private String title;
    private String description;
    private String url;
    private String urlToImage;
    private String publishedAt;
    private String content;

}
