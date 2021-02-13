package me.yjh.demospring5bu.demo;

import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository
//@Profile("test") //이런식으로도 지정하여 설정할 수 이다
public class TestBookRepository implements BookRepository {



}
