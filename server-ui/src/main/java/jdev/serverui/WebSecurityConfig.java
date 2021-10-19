package jdev.serverui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    // Сервисный интерфейс для кодирования паролей
    @Autowired
    PasswordEncoder passwordEncoder;

    // Реализация сервисного интерфейса для кодирования паролей
    // через BCryptPasswordEncoder()
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/home").authenticated()
                    .antMatchers("/error").authenticated()
                    .antMatchers("/routes", "/routes/**","/payments","/payments/**").hasRole("CLIENT")
                    .antMatchers("/registerClient", "/registerClient/**").hasRole("MANAGER")
                    .antMatchers("/registerManager","/registerManager/**").hasRole("ROOT")
                    .and()
                .formLogin()
                    .loginPage("/css/img/login")
                    .permitAll()
                    .and()
                .logout()
                    .permitAll();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                    .withUser("client").password(passwordEncoder.encode("123456")).roles("CLIENT")
                    .and()
                    .withUser("manager").password(passwordEncoder.encode("123456")).roles("CLIENT", "MANAGER")
                    .and()
                    .withUser("root").password(passwordEncoder.encode("123456")).roles("CLIENT", "MANAGER", "ROOT");
    }
}