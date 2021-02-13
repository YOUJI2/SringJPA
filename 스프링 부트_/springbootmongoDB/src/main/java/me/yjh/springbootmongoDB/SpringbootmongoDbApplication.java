package me.yjh.springbootmongoDB;

import me.yjh.springbootmongoDB.account.Account;
import me.yjh.springbootmongoDB.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;

@SpringBootApplication
public class SpringbootmongoDbApplication {

	@Autowired
	MongoTemplate mongoTemplate;


	@Autowired
	AccountRepository accountRepository;


	public static void main(String[] args) {
		SpringApplication.run(SpringbootmongoDbApplication.class, args);
	}

	@Bean
	public ApplicationRunner applicationRunner(){

//		return new ApplicationRunner() {
//			@Override
//			public void run(ApplicationArguments args) throws Exception {
//
//			}
//		}
		//  option + enter 로 람다식으로 간편하게 바꿀 수 있다.
		return args -> {
			Account account = new Account();
			account.setEmail("dbwlgna98@naver.com");
			account.setUsername("youjihoon");
//			mongoTemplate.insert(account);

			accountRepository.insert(account);

			System.out.println("finished");


		};

	}



}
