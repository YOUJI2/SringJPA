package me.yjh.demospring51;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository //@Primary 자주 사용하는 빈 설정 시 (autowire 사용시 여러개중 하나를 주로 사용한다)
public class YoujiRepository implements BookRepository{

}
