package me.yjh;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HellomanConfiguration {

    @Bean
    public Helloman helloman(){
        Helloman helloman = new Helloman();
        helloman.setHowlong(5);
        helloman.setName("youji");
        return helloman;


    }
}
