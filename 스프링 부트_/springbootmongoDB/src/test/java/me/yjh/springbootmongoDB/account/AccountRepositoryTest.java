package me.yjh.springbootmongoDB.account;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataMongoTest
public class AccountRepositoryTest {

    @Autowired
    AccountRepository accountRepository;

    @Test
    public void findByEmail(){
        Account account = new Account();
         account.setUsername("Uji");
         account.setEmail("yjh@sejong.ac.kr");
         accountRepository.save(account);


         Optional<Account> byid =accountRepository.findById(account.getId());
         assertThat(byid).isNotEmpty();

         Optional<Account> byemail = accountRepository.findByEmail(account.getEmail());
         assertThat(byemail).isNotEmpty();
         assertThat(byemail.get().getUsername()).isEqualTo("Uji");

    }

}
