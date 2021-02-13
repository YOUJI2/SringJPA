package me.yjh.springapplicationTwo;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;


//@Component   => context를 만들기 전에 사용하므로 직접 등록이 필요하다 그래서 빈으로 등록 할 필요가 없다 .
//public class SampleListener implements ApplicationListener<ApplicationStartingEvent> {
//
//    @Override
//    public void onApplicationEvent(ApplicationStartingEvent event) {
//        System.out.println("============================");
//        System.out.println("Application is Starting");
//        System.out.println("============================");
//    }
//}


// -> applicationStartedEvent 예시
//@Component
//public class SampleListener implements ApplicationListener<ApplicationStartedEvent> {
//
//    @Override
//    public void onApplicationEvent(ApplicationStartedEvent event) {
//        System.out.println("============================");
//        System.out.println("Application is Started");
//        System.out.println("============================");
//    }
//}

@Component
public class SampleListener implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("foo :"+ args.containsOption("foo"));
        System.out.println("bar :"+ args.containsOption("bar"));
    }
}
