package me.yjh.demospring17.spring17;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements ApplicationRunner {


    // 17 spEL (spring exepression Language)
//    @Value("#{1 + 1}")
//    int value;
//
//    @Value("#{'hello ' + 'world'}")
//    String greeting;
//
//    @Value("#{1 eq 1}")
//    boolean trueOfFalse;
//
//    @Value("${my.value}")
//    int myValue;
//
//    @Override
//    public void run(ApplicationArguments args) throws Exception {
//        System.out.println("=====================");
//        System.out.println(value);
//        System.out.println(greeting);
//        System.out.println(trueOfFalse);
//        System.out.println(myValue);
//    }


    // 18,19 프록시 기반 AOP 예제

   //interface가 있는 경우에는 interface로 주입받는게 좋다(권장 !!!)
    @Autowired
    EventService eventService;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        eventService.createevent();
        eventService.publishEvent();
        eventService.deleteEvent();
    }
}

