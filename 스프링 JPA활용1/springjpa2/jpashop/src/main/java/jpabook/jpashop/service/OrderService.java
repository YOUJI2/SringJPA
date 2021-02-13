package jpabook.jpashop.service;

import jpabook.jpashop.domain.Delivery;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderItem;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import jpabook.jpashop.repository.MemberRepository;
import jpabook.jpashop.repository.OrderRepository;
import jpabook.jpashop.repository.OrderSearch;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    //여기서는 크게 2가지가 중요하다
    //주문,  주문취소, 검색

    //주문
    // => 데이터를 변경하기 떄문에 transactional 필요
    //
    @Transactional
    public Long order(Long memberId, Long itemId, int count){  //주문 아이디, 상품아이디, 수량이 넘어와야 한다.

        //엔티티 조회
        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.fineOne(itemId);

        //배송정보 생성
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());

        //주문상품생성
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

          //여기에서 생성할때 set을 이용해 각각 값을 전부 집어넣어 하게되면 나중에 값이 퍼져나가는 중에 오류가 발생할 수 있다.
          //이를 방지하기 위해서 protected를 걸어서 방지해 준다.
          //orderIten에 걸기!!
          //new orderItem() 시 빨간줄로 오류를 체크한다.

        //주문생성
        Order order = Order.createOrder(member, delivery, orderItem);
        // 여기에도 @NoArgsConstructor(access = AccessLevel.PROTECTED)를 걸어준다.

        //주문저장
        orderRepository.save(order);
         // 이걱 하나만 저장해줘도 order의 cascade 떄문에 이와 묶인 것들도 같이 persist가 된다.

        return order.getId();
    }


    /*
        주문 취소
     */
    @Transactional
    public void cancelOrder(Long orderId){
        //주문 엔티티조회
        Order order = orderRepository.findOne(orderId);
        //주문 취소
        order.cancel();

         // 어 왜이리 취소가 간단하지? => 비지니스 로직 만든걸 봐보자!
         // 대부분의 사람들은 이렇게 하지 않는다.
         // 여기에서 JPA의 강점이 나온다.
         // 평상시 같으면 이렇게 취소하게되면 재고수량을 다시 받아서 update쿼리를 직접 다 날려줘야한다.
         // 그런데 jpa를 활용하면 데이터만 바꾸면 jpa가 알아서 변경이 된 point들을 "더티체킹"(변경력 감지)이 발생하면서
         // 업데이트 쿼리를 자동으로 날려준다.

        //보통 이렇게 서비스 계층에서는 불어오는 방식으로만 설계하는것을 "도메인 모델 패턴"이라 한다. => pdf참고하기
        //[ 도메인 안에 핵심 비지니스 로직을 넣는것 ]
        //유지보수하기 좋은쪽으로 설계하는 것이 제일 좋다! (트렌젝션 모델이 더 효율일때도 있음 )
    }

    /*
        검색
     */
     public List<Order> findOrders(OrderSearch orderSearch){
         //return orderRepository.findAllByString(orderSearch);
        return  orderRepository.findAllByCriteria(orderSearch);
     }


}
