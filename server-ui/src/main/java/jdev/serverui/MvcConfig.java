package jdev.serverui;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig  implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/routes").setViewName("routes");
        registry.addViewController("/payments").setViewName("payments");
        registry.addViewController("/registerClient").setViewName("registerClient");
        registry.addViewController("/registerManager").setViewName("registerManager");
        registry.addViewController("/css/img/login").setViewName("/css/img/login");
        registry.addViewController("/error").setViewName("error");
    }

}