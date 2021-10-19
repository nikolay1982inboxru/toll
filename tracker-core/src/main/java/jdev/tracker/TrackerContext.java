package jdev.tracker;

import jdev.tracker.services.ServiceGPS;
import jdev.tracker.services.ServiceSaveMsg;
import jdev.tracker.services.ServiceSendMsg;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@Configuration
@PropertySource("classpath:application-Prod.properties")
@EnableScheduling
public class TrackerContext {
    @Bean
    public ServiceGPS serviceGPS() {return new ServiceGPS();}

    @Bean
    public ServiceSendMsg serviceSendMsg() {return new ServiceSendMsg();}

    @Bean
    public ServiceSaveMsg serviceSaveMsg() {return new ServiceSaveMsg();}

    @Bean
    public TaskScheduler poolScheduler() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setThreadNamePrefix("TrackerCore_pool_");
        scheduler.setPoolSize(10);
        return scheduler;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
