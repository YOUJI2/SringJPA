package me.yjh;

import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.boot.web.servlet.context.ServletWebServerInitializedEvent;
import org.springframework.stereotype.Component;

import static org.springframework.boot.web.servlet.context.ServletWebServerInitializedEvent.*;

@Component
public class PortListener implements ApplicationListener<ServletWebServerInitializedEvent> {


    @Override
    public void onApplicationEvent(ServletWebServerInitializedEvent servletWebServerInitializedEvent) {
        ServletWebServerApplicationContext applicationContext = servletWebServerInitializedEvent.getApplicationContext();
        System.out.println(applicationContext.getWebServer().getPort());
    }
}
