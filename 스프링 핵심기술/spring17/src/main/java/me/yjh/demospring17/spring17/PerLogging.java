package me.yjh.demospring17.spring17;

import java.lang.annotation.*;

/*
*    이 에노테이션을 사용하면 성능을 측정해줍니다
* */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.CLASS) //이 에노테이션 정보를 얼마나 유지시킬 것인
public @interface PerLogging {

}
