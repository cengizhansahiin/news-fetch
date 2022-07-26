package tr.com.id3.news.newsfetch.job;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tr.com.id3.news.newsfetch.services.FetchService;
/*@Component
public class FetchJob implements Job {
    @Autowired
    private FetchService jobService;
    public void execute(JobExecutionContext context) throws JobExecutionException{
        try {
            jobService.executeFetchNews();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
*/