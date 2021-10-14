package jdev.tracker;

import jdev.tracker.services.ServiceGPS;
import jdev.tracker.services.ServiceSaveMsg;
import jdev.tracker.services.ServiceSendMsg;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@Configuration
@EnableScheduling
@PropertySource("classpath:/application.properties")
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
}
