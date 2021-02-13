package me.yjh.SpringExternalConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class SampleRunner implements ApplicationRunner {

//    @Value("${jihoon.fullname}")
//    private String name;
//
//    @Value("${jihoon.age}")
//    private int age;

    @Autowired
    jihoonProperties jihoonproperties;
//
//    @Override
//    public void run(ApplicationArguments args) throws Exception {
//        System.out.println("============================");
//        System.out.println(jihoonproperties.getName());
//        System.out.println(jihoonproperties.getAge());
//        System.out.println(jihoonproperties.getSessionTimeout());
//        System.out.println("============================");
//
//    }
    @Autowired
    public String hello;

//    @Override
//    public void run(ApplicationArguments args) throws Exception {
//        System.out.println("============================");
//        System.out.println(hello);
//        System.out.println(jihoonproperties.getName());
//        System.out.println("============================");
//
//    }

    private Logger logger = LoggerFactory.getLogger(SampleRunner.class);

    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.debug("============================");
        logger.debug(hello);
        logger.debug(jihoonproperties.getName());
        logger.debug("============================");

    }



}
