//package me.yjh.demospring17.spring17;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Primary;
//import org.springframework.stereotype.Service;
//
//
//@Primary
//@Service
//public class ProxySimpleEventService implements EventService{// proxy 패턴을 구현할때 같은 interface로 구현한다.
//
//    //proxy 같은 경우는 subject bean으로 주입받아야 한다
//    @Autowired
//    SimpleEventService simpleEventService;
//
//    @Override
//    public void createevent() {
//        long begin = System.currentTimeMillis();
//        simpleEventService.createevent();
//        System.out.println(System.currentTimeMillis() - begin);
//    }
//
//    @Override
//    public void publishEvent() {
//        long begin = System.currentTimeMillis();
//        simpleEventService.publishEvent();
//        System.out.println(System.currentTimeMillis() - begin);
//    }
//
//    @Override
//    public void deleteEvent() {
//        simpleEventService.deleteEvent();
//    }
//
//
//}
