package com.example.restfullwebservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.AndRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@EnableMethodSecurity(securedEnabled = true, prePostEnabled =true)
@EnableWebSecurity
@Component
public class SecurityConfig {

/*
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
        };
    }
*/


    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication()
                .withUser("test")
                .password("{noop}1111")
                .roles("USER");
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, HandlerMappingIntrospector introspector) throws Exception {
        http
             .csrf(AbstractHttpConfigurer::disable)
             .sessionManagement((sessionManagement) ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
             )

                .authorizeHttpRequests(request -> request
                        .requestMatchers(new MvcRequestMatcher(introspector,"/**")).permitAll()
                        .requestMatchers(new MvcRequestMatcher(introspector,"/jpa/*8")).permitAll()
                    .requestMatchers(new MvcRequestMatcher(introspector,"/api/hello")).permitAll()
                        .requestMatchers(new MvcRequestMatcher(introspector,"/h2-console")).permitAll()
                        .requestMatchers(new MvcRequestMatcher(introspector,"/h2-console/**")).permitAll()
                    .anyRequest().permitAll())
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

}
