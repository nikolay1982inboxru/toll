package jdev.servercore;

import jdev.dto.PointDTO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
public class CoreMain {
    public static void main(String[] args) {
//        SpringApplication.run(CoreMain.class, args);
        new SpringApplicationBuilder(CoreMain.class)
                .profiles("Test", "Prod")
                .run(args);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder
                .build();
    }
}
