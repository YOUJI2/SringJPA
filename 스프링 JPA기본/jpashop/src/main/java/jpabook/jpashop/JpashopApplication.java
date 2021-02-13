package jpabook.jpashop;

import jpabook.jpashop.domain.Book;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderItem;
import org.aspectj.weaver.ast.Or;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

@SpringBootApplication
public class JpashopApplication {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();

		EntityTransaction tx = em.getTransaction();
		tx.begin();

		try {

			Book book = new Book();
			book.setName("JPA");
			book.setAuthor("김영한");
			em.persist(book);

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
