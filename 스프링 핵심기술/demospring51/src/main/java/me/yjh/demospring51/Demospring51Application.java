package me.yjh.demospring51;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import out.MyService;

import java.util.function.Supplier;

@SpringBootApplication
public class Demospring51Application {

	public static void main(String[] args) {
		//SpringApplication.run(Demospring51Application.class, args);

		var app = new SpringApplication(Demospring51Application.class); //자바 10부터는 local variable : var 사용이 가능하다
		//원하는 application context를 주입 받을 수 있
		app.addInitializers((ApplicationContextInitializer<GenericApplicationContext>) ctx -> {
			ctx.registerBean(MyService.class);
			ctx.registerBean(ApplicationRunner.class,  () -> args1 -> System.out.println("Functional Bean Definition!!!!!!"));

			// bean 2개를 등록할 수 있다
		});
		app.run(args);



	}

}
