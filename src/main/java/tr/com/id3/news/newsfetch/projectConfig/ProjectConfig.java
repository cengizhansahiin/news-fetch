package tr.com.id3.news.newsfetch.projectConfig;

import org.quartz.*;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.quartz.QuartzDataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
//import tr.com.id3.news.newsfetch.job.FetchJob;

import javax.sql.DataSource;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

@Configuration
@EnableScheduling
@ComponentScan("tr.com.id3.news.newsfetch")
@PropertySource("application.properties")
@EnableAutoConfiguration
@DisallowConcurrentExecution
public class ProjectConfig {
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer(){
        return new PropertySourcesPlaceholderConfigurer();
    }
    /*@Bean
    public JobDetail jobDetail(){
        return JobBuilder.newJob().ofType(FetchJob.class)
                .storeDurably()
                .withIdentity("Qrtz_Job_Detail")
                .withDescription("Invoke Fetch Job service...")
                .build();
    }
    @Bean
    public Trigger trigger(JobDetail job) {
        return TriggerBuilder.newTrigger().forJob(job)
                .withIdentity("Qrtz_Trigger")
                .withDescription("Fetch Trigger")
                .withSchedule(CronScheduleBuilder.cronSchedule("0 0/5 * 1/1 * ? *"))
                .build();
    }*/
}
