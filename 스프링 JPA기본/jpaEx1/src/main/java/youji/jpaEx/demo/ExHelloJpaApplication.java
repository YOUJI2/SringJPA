package youji.jpaEx.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.*;
import java.util.List;

//@SpringBootApplication
public class ExHelloJpaApplication {


	public static void main(String[] args)
	{

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();

		EntityTransaction tx = em.getTransaction();
		tx.begin();

		try {
			//등록
//			Member member = new Member();
//			member.setId(2L);
//			member.setName("HelloB");
//			em.persist(member);

			//수정
//			Member findMember = em.find(Member.class, 1L);
//			findMember.setName("ChangeHelloA");

			//삭제
//			em.remove(findMember);

			//조회
//			List<Member> result = em.createQuery("select m from Member as m", Member.class)
//					.getResultList();

			// 엔티티 등록 연습
			//영속
//			Member member = new Member(1500L, "A");
//			Member member2 = new Member(1510L, "B");
//
//			em.persist(member);
//			em.persist(member2);
//
//			System.out.println("======================");

			Member member = new Member();
			member.setUsername("A");

			em.persist(member);

			tx.commit(); //이 시점에 쿼리가 날라간다.
		}catch (Exception e){
			tx.rollback();
		}finally {
			em.close();
		}
		emf.close();
//		SpringApplication.run(ExHelloJpaApplication.class, args);
	}

}
