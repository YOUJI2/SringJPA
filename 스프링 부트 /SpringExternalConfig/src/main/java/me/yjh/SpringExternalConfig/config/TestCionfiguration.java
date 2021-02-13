package me.yjh.SpringExternalConfig.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("test")
@Configuration
public class TestCionfiguration {

    @Bean
    public String hello(){
        return "hello test";
    }
}
