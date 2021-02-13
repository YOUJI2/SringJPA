package jpabook.jpashop.api;

import jpabook.jpashop.domain.Order;
import jpabook.jpashop.repository.OrderRepository;
import jpabook.jpashop.repository.OrderSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/*
    성능 최적화에 대한 단계별 api 개발

    1. XtoOne (ManyToOne, OneToOne)
    order
    order -> Member
    Order -> Delivery
 */
@RestController
@RequiredArgsConstructor
public class OrderSimpleApiController {

    private final OrderRepository orderRepository;


    //결론적으로 이 방법은 hibernate5module로 어느정도 해결했지만 entity객체를 그대로 노출하기 때문에 좋지 않은 방법이다
    // entity객체를 직접 노출하는 것은 보안상의 이유나 문제를 야기하기 때문에 피해야 한다.
    @GetMapping("/api/v1/simple-orders")
    public List<Order> ordersV1(){
        List<Order> all = orderRepository.findAllByString(new OrderSearch());
        for (Order order : all) {
            order.getMember().getName(); // getMember호출까지는 proxy가 가짜 멤버를 생성하여 가져오지만
                                         // getName까지 하게되면 이름을 불러오기 위해서 force lazy가 걸리게 된다.(lazy 강제 초기화)
            order.getDelivery().getAddress(); //lazy 강제 초기화 (위와 같은 원리이다. 정말 중요함!!! )

        }
        return all;
    }


}
