package me.yjh.SpringExternalConfig;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@TestPropertySource(locations = "classpath:/test.properties")
//@SpringBootTest(properties = "jihoon.name = jihoon2")
@SpringBootTest
public class SpringapplicationTests {

    @Autowired
    Environment environment;

    @Test
    public void contextLoads(){
        assertThat(environment.getProperty("jihoon.name")).isEqualTo("jihoon2");

    }

}
