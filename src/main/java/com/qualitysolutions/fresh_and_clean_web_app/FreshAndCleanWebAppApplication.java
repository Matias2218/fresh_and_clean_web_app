package com.qualitysolutions.fresh_and_clean_web_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.time.*;
import java.util.Date;
import java.util.TimeZone;

@SpringBootApplication
public class FreshAndCleanWebAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(FreshAndCleanWebAppApplication.class, args);
    }
    
    @Bean
    public RestTemplate getRestTemplate()
    {
        return  new RestTemplate();
    }
    @PostConstruct
    public void init(){
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }
}
