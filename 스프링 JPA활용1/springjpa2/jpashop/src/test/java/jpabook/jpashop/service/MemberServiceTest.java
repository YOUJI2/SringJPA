package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)  //junit실행할때 Spring이란 엮어서 실행하기 위함
@SpringBootTest
@Transactional  //데이터 변경과 롤백을 해야하기때문에 사용한다. ==> 그래서 실행 했을때 insert문이 안보인다. 롤백때문에 영속성 컨텍스트가
public class MemberServiceTest {                  //   굳이 DB에 insert문을 날릴 필요가 없다 판단하여 flush를 안해버린다.

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    EntityManager em;



    //롤백이지만 쿼리 날라가는건 보고 싶다
    //==> em.flush 이용

//    @Test
//    @Rollback(false) //false로 롤백을 안하게 되며 DB에 insert문이 나간것을 확인 할 수 있다.
    @Test
    public void 회원가입() throws Exception{

        //given
        Member member = new Member();
        member.setName("kim");

        //when
        Long savedId = memberService.join(member);

        //then
       // em.flush(); //이걸로 DB에 나갔는지 확인 가능
        assertEquals(member, memberRepository.findOne(savedId));
    }


    @Test(expected = IllegalStateException.class)
    public void 중복_회원_예외() throws Exception{

        //given
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");

        //when
        memberService.join(member1);
        memberService.join(member2);   //expected를 통해 예외를 자동적으로 거르게 해준다.!! 밑에 코드 생략가능

//        try{
//            memberService.join(member2); //예외 발생! 해야한다.
//        }catch (IllegalStateException e){
//            return;
//        }
        //then
        fail("예외가 발생해야한다."); //코드가 진행되다가 여기에 실행이 된다면 error를 발생시킨다.
    }

}