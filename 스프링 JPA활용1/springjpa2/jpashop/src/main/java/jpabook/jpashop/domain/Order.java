package jpabook.jpashop.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.aspectj.weaver.ast.Or;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;



    //===== 연관관계 편의 메서드 ======
     public void setMember(Member member1){      //order에서의 member에 Member calss를 넣은뒤
         this.member = member1;                  //member1에도 orders에 이 값을 넣어준다.
         member1.getOrders().add(this);          //즉, 연관되어 있는 두 곳에 같이 넣어주는 것이다.
     }

     public void addOrderItem(OrderItem orderItem){   //여기에서도 orderItem list에 OrderItem 값을 넣어주고
         orderItems.add(orderItem);                   //반대편인 orderitem에도 order정보를 넣어준다.
         orderItem.setOrder(this);
     }

     public void setDelivery(Delivery delivery){
         this.delivery=delivery;
         delivery.setOrder(this);
     }


     //====== 생성 메서드 ===========
     //order안에서도 여러가지 복잡한 연관관계가 존재하기 ㄱ때문에 이를 관리하기 위해 생성메서드가 있으면 편리하다
    public static Order createOrder(Member member, Delivery delivery, OrderItem... orderItems){ //...문법으로 여러개를 넘길수 있다.
        Order order = new Order();
        order.setMember(member);
        order.setDelivery(delivery);
        for(OrderItem orderItem : orderItems){
            order.addOrderItem(orderItem);
        }
        order.setOrderDate(LocalDateTime.now());
        order.setOrderStatus(OrderStatus.ORDER);
        return order;

        //order의 주문 상태랑 주문 시각, 주문상태등을 한번에 입력받아서 생성해준다.
        //이 방법이 좋은 이유는 무언가 하나를 바꾸게 된다면 일일히 찾을 필요 없이 여기서 수정해서 찾아주기만 하면 된다.
    }

    //========비지니스 로직===========
    /*
        주문 취소
     */
    public void cancel(){
        if(delivery.getDeliveryStatus() == DeliveryStatus.COMP){ //배송이 이미 완료
            throw new IllegalStateException("이미 배송된 상품은 취소가 불가능 합니다");
        }

        this.setOrderStatus(OrderStatus.CANCEL);
        for(OrderItem orderItem : orderItems){
            orderItem.cancel();
        }
    }

    //==조회 로직===
    /*
        전체 주문 가격 조회
     */
    public int getTotalPrice(){
//        int totalPrice=0;
//        for(OrderItem orderItem : orderItems){
//            totalPrice += orderItem.getTotalPrice();
//        }
//        return totalPrice;

        //람다식 사용 : replace --- sum() => option + enter로 가능하다
        return orderItems.stream().mapToInt(OrderItem::getTotalPrice).sum();
    }

}
