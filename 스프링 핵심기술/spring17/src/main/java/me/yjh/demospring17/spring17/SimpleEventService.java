package me.yjh.demospring17.spring17;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

@Service
public class SimpleEventService implements EventService {

    @PerLogging
    @Override
    public void createevent() {
//        long begin = System.currentTimeMillis();    => 이런 기능코드를 추가하지 않고 건들지 않고 만들고 싶다
        try {                                          // 그럴때 Proxy pettern 사용
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Created an event");
//        System.out.println(System.currentTimeMillis() - begin);
    }

    @PerLogging
    @Override
    public void publishEvent() {
//        long begin = System.currentTimeMillis();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Published an event");
  //      System.out.println(System.currentTimeMillis() - begin);

    }

    public void deleteEvent(){
        System.out.println("Deleted an event");
    }


}
