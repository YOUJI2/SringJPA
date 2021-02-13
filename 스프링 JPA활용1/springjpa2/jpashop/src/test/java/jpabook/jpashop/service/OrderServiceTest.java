package jpabook.jpashop.service;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.exception.NotEnoughStockException;
import jpabook.jpashop.repository.OrderRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.fail;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderServiceTest {

    @Autowired
    EntityManager em;

    @Autowired
    OrderService orderService;

    @Autowired
    OrderRepository orderRepository;

    @Test
    public void 상품주문() throws Exception{
        //given
        Member member = createMember();

        Book book = createBook("JPA 활용1", 10000, 10);

        int orderCount =2;
        //when
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

        //then
        Order getOrder = orderRepository.findOne(orderId);

        //검증
        Assert.assertEquals("상품 주문시 상태는 order ", OrderStatus.ORDER, getOrder.getOrderStatus());
        Assert.assertEquals("주문한 상품 종류 수가 정확해야한다." ,1, getOrder.getOrderItems().size());
        Assert.assertEquals("주문 가격은 상품 수량 * 가격 이다 ",10000*orderCount, getOrder.getTotalPrice());
        Assert.assertEquals("주문 수량만큼 재고가 줄어야 한다.",8, book.getStockQuantity());
    }


    //주문 기능 테스트 : 9분 29초
    @Test(expected = NotEnoughStockException.class)
    public void 상품주문_재고수량초과() throws Exception{

        //given
        Member member = createMember();
        Item item = createBook("JPA 활용1", 10000, 10);

        int orderCount = 11; // 이러면 exception 터져야 한다.

        //when
        orderService.order(member.getId(),item.getId(),orderCount);

        //then
        fail("재고 수량 부족 예외가 터져야 한다."); //위에 예외문이 터지지 않을경우를 대비 (여기서 확인 )
        //여기 까지 오면 안된다는 의미
    }



    @Test
    public void 주문취소() throws Exception{

        //given
        Member member = createMember();
        Book item = createBook("JPA 활용1", 10000, 10);

        int orderCount = 2;
        Long orderId = orderService.order(member.getId(), item.getId(), orderCount);

        //when
        orderService.cancelOrder(orderId); //이러면 취소가 되어야 한다.

        //then ==> 재고가 정상적으로 복구 됐는지 검사한다.
        Order getOrder = orderRepository.findOne(orderId);
        Assert.assertEquals("주문취소시 상태는 cancel 이 되어야 한다.",OrderStatus.CANCEL,getOrder.getOrderStatus());
        Assert.assertEquals("주문이 취소된 상품은 그만큼 재고가 증가해야한다.",10,item.getStockQuantity());


    }


    private Book createBook(String name, int price, int stockQuantity) {   //메서드 만들기 영역을 드래그 후 option + command + M 으로 메서드 생성
        Book book = new Book();   // command + option + P 로 파라미터로 보낼 수 있다.
        book.setName(name);
        book.setPrice(price);
        book.setStockQuantity(stockQuantity);
        em.persist(book);
        return book;
    }

    private Member createMember() {
        Member member = new Member();
        member.setName("회원1");
        member.setAddress(new Address("서울","강가","123-123"));
        em.persist(member);
        return member;
    }


}