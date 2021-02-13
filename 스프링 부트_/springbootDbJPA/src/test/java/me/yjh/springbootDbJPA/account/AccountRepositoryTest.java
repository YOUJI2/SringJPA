package me.yjh.springbootDbJPA.account;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AccountRepositoryTest {

    //test를 돌릴때는 embeded로 돌리는게 빠르고 h2 사용하는게 나쁘지 않다!
    //database repository test하기 좋다

   @Autowired
    DataSource dataSource;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    AccountRepository accountRepository;

    @Test
    public void di() throws SQLException {  //비어있는 테스트를 먼저 띄워서 아래 두가지를 확인한다!! TIP
        // 1. 테스트 애플리케이션이 잘 돌아가는지 확인
        // 2. 빈으로 잘 등록되는지 확인
//           try(Connection connection = dataSource.getConnection()){
//               DatabaseMetaData metaData = connection.getMetaData();
//               System.out.println(metaData.getURL());
//               System.out.println(metaData.getDriverName());
//               System.out.println(metaData.getUserName());
//           }

           //실제 적용해서 test 해본다
        Account account = new Account();
        account.setUsername("jihoon");
        account.setPassword("pass");

        Account newAccount = accountRepository.save(account);
        assertThat(newAccount).isNotNull();

        Account existingAccount = accountRepository.findByUsername(newAccount.getUsername());
        assertThat(existingAccount).isNotNull();

    }


}
