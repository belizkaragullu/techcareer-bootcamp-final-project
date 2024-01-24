package com.example.blogproject.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    //SecurityFilterChain is interface and default security filter chain is implementation class
    //SecurityFilterChain enables to security settings and configuration

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception { //bean

        //disable csrf (web application security measure)
        http.csrf((csrf) -> csrf.disable())
                .authorizeHttpRequests((authorize) -> authorize.anyRequest().authenticated()) //configure all http requests to be authenticated

                .httpBasic(Customizer.withDefaults()); //username-password

        return http.build(); //Save bean
    }
}
