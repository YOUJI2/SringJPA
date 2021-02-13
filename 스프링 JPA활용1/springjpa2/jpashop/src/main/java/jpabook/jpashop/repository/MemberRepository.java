package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

//    @PersistenceContext
//    private EntityManager em;

    //스프링 데이터 JPA를 쓴다면 위에 @persistanceContext 대신 @Autowired가 가능해진다.(이 기능을 지원해준다.)
    //그렇다면 기본 생성자를 통해 주입이 가능해지고
    // final 로 묶어서 @RequiredArgsConstructor 사용해주면 간편해진다. == 일관성이 생긴다. (service나 repository)
    private final EntityManager em;


    //+ 원래 EntityManager는 @autowired로는 안되고 반드시 @persistanceContext 표준 annotation이 있어야 인젝션을 해준다.
    //  근데 스프링 부트가 autowired로도 지원을 해줘서 가능하게 된다.


    public void save(Member member){
        em.persist(member);
    }

    public Member findOne(Long id){
        return em.find(Member.class, id);
    }

    public List<Member> findAll(){
        //jpql 사용
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    public List<Member> findByName(String name1){
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name",name1)
                .getResultList();
    }

}
