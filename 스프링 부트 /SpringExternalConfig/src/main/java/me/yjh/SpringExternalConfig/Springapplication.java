package me.yjh.SpringExternalConfig;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
//@EnableConfigurationProperties(jihoonProperties.class)  필요없음
public class Springapplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Springapplication.class);
        app.setWebApplicationType(WebApplicationType.NONE);
        app.run(args);

    }

}
