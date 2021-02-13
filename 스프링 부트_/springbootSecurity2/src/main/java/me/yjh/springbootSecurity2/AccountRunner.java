package me.yjh.springbootSecurity2;

import me.yjh.springbootSecurity2.account.Account;
import me.yjh.springbootSecurity2.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class AccountRunner implements ApplicationRunner {

    @Autowired
    AccountService accountService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Account account = accountService.createAccount("jihoon","1234");
        System.out.println(account.getUsername() + " password : " + account.getPassword());

    }
}
