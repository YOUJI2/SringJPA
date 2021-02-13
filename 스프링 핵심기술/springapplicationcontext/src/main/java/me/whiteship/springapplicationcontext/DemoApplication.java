package me.whiteship.springapplicationcontext;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.awt.print.Book;
import java.util.Arrays;

//@SpringBootApplication  => 스프링 에노테이션 제거로 밑에 명령문은 동작하지 않는다
	//-> 사실상 이거 하나로 밑에있는 allicationcontext 를 뿐만 아니라 지원!! 즉 밑에 작업들이 따로 필요 없다.
public class DemoApplication {


	public static void main(String[] args) {

		ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		//위에 넣은 ApplicationConfig.class를 bean설정으로 사용하는것
		// bean 정의를 읽어서 bean을 등록을 하고 의존성 정의를 주입해준다.

		//ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
		String[] beanDefinitionNames = context.getBeanDefinitionNames();
		System.out.println(Arrays.toString(beanDefinitionNames));
		BookService bookService = (BookService) context.getBean("bookService");
		System.out.println(bookService.bookRepository != null);



	}

			}
