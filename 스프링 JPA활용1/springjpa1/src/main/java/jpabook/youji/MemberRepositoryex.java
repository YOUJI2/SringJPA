package jpabook.youji;

import jpabook.youji.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

//@Repository
//public class MemberRepositoryex {
//
//    //JPA를 쓰기 위해 entity 매니저 생성
//    @PersistenceContext
//    private EntityManager em;
//
//    public Long save(Member member){
//        em.persist(member);
//        return member.getId();
//    }
//
//    public Member find(Long id){
//        return em.find(Member.class, id);
//    }
//
//}
