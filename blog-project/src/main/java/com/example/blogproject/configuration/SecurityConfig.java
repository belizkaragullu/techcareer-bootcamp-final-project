package com.example.blogproject.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    //uses bycrypt algho to encode password

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    //SecurityFilterChain is interface and default security filter chain is implementation class
    //SecurityFilterChain enables to security settings and configuration

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception { //bean

        //disable csrf (web application security measure)
        http.csrf((csrf) -> csrf.disable())
                .authorizeHttpRequests((authorize) -> authorize.requestMatchers(HttpMethod.GET, "/api/**").permitAll().anyRequest().authenticated()) //provide all users to access GET endpoints

                .httpBasic(Customizer.withDefaults()); //username-password

        return http.build(); //Save bean
    }

    //create multiple users, assign role to each user,store it in a memory object
    // implement role based auth
    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails beliz = User.builder()
                .username("beliz")
                .password(passwordEncoder().encode("12345"))
                .roles("ADMIN")
                .build();

        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("1a2b3c"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(beliz,admin);

    }

}
