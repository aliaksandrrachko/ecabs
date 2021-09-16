package com.senlainc.bsdd.ecabs.booking.producer.controller.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Enable CORS and disable CSRF
        http = http.cors().and().csrf().disable();

        // Set permissions on endpoints
        http.authorizeRequests()
                // Authentication endpoints
                .anyRequest().authenticated();
        http.httpBasic();
    }
}
