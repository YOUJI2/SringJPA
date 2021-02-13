package hellojpa.jpql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.*;
import java.util.List;

@SpringBootApplication
public class JpqlApplication {

	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();

		EntityTransaction tx = em.getTransaction();
		tx.begin();

		try {
//			Member member = new Member();
//			member.setUsername("member1");
//			member.setAge(10);
//			em.persist(member);

//			em.flush();
//			em.clear();

			//jpql 프로젝션 - 3번쨰 방법 new 명령어로 조회
//			List<MemberDTO> result = em.createQuery("select new hellojpa.jpql.MemberDTO(m.username, m.age) from Member m", MemberDTO.class)
//					.getResultList();
//			MemberDTO memberDTO = result.get(0);
//			System.out.println("memberDTO = " + memberDTO.getUsername());
//			System.out.println("memberDTO = " + memberDTO.getAge());

			//페이징 API
//			for(int i=0;i<100;i++){
//				Member member1 = new Member();
//				member1.setUsername("member"+i);
//				member1.setAge(i);
//				em.persist(member1);
//			}
//			em.flush();
//			em.clear();
//			List<Member> result = em.createQuery("select m from Member m order by m.age desc", Member.class)
//					.setFirstResult(1)
//					.setMaxResults(10)
//					.getResultList();
//
//			System.out.println("result size = "+ result.size());
//			for(Member member1 : result){
//				System.out.println("Member1 = " + member1);
//			}

			//조인 예시
			Team team = new Team();
			team.setName("teamA");
			em.persist(team);

			Member member = new Member();
			member.setUsername("member1");
			member.setAge(10);
			member.setTeam(team);
			member.setType(MemberType.ADMIN);
			em.persist(member);

			em.flush();
			em.clear();

			String query = "select m from Member m inner join m.team"; //inner 는 생략 가능 outer 조인은 left join

			//조인 대상 필터링
			//회원과 팀을 조인하면서, 팀이름이 teamA인
			String query1 =  "select m from Member m left join m.team t on t.name = 'teamA'";

			//연관관계 없는 엔티티 외부 조인
			//회원의 이름과 팀의 이름이 같은 대상 외부 조인
//			String query2 =  "select m from Member m left join Team t on m.username = t.name";
//
//			List<Member> result = em.createQuery(query2, Member.class).getResultList();
//
//			System.out.println("result = " + result.size());

			//JQPL 타입 표현
			String query3 = "select m.username, 'HELLO', true from Member m "+
							"where m.type = :userType";
			List<Object[]> result1 = em.createQuery(query3)
									.setParameter("userType", MemberType.ADMIN)
									.getResultList();
			for(Object[] objects : result1){
				System.out.println("objects = " +objects[0]);
				System.out.println("objects = " +objects[1]);
				System.out.println("objects = " +objects[2]);
			}

			tx.commit(); //이 시점에 쿼리가 날라간다.
		}catch (Exception e){
			tx.rollback();
		}finally {
			em.close();
		}
		emf.close();
		//SpringApplication.run(JpashopApplication.class, args);

	}

}
