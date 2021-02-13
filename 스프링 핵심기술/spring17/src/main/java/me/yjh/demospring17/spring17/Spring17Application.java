package me.yjh.demospring17.spring17;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Spring17Application {

	public static void main(String[] args) {
//		SpringApplication.run(Spring17Application.class, args);

		//spring boot  실행 이렇게 하면 좀더 빠르게 실행이 가능하고 실제 서버가 기능하지 않는다?
		SpringApplication app = new SpringApplication(Spring17Application.class);
		app.setWebApplicationType(WebApplicationType.NONE);
		app.run(args);

	}

}
