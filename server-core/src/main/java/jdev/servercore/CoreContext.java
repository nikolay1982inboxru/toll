package jdev.servercore;

import jdev.servercore.services.ServiceTakeMsg;
import jdev.servercore.services.ServiceTakeRoute;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.client.RestTemplate;

@Configuration
@PropertySource("classpath:application-Prod.properties")
@EnableScheduling
public class CoreContext {
    @Bean
    public ServiceTakeMsg serviceTakeMsg() {return new ServiceTakeMsg(new RestTemplate());}

    @Bean
    public ServiceTakeRoute serviceTakeRoute() {return  new ServiceTakeRoute(new RestTemplate());}

    @Bean
    public TaskScheduler poolScheduler() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setThreadNamePrefix("ServerCore_pool_");
        scheduler.setPoolSize(10);
        return scheduler;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
