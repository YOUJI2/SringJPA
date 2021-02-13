package me.yjh.demospring17.spring17;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;

@Component
@Aspect
public class PerfAspect {


        //해야할 일들을 정의 해준다
        //@Around("execution(* me.yjh..*.EventService.*(..))")  // 포인터 컷도 함께 정의
        @Around("@annotation(PerLogging )")
        public Object logPerf(ProceedingJoinPoint pjp) throws Throwable{  //ProceedingJoinPoint는 이 advice가 적용되는 대상을 의미
                                                         // 여기서는 createevent, publishevent를 의미한다.
            long begin = System.currentTimeMillis();
            Object retVal = pjp.proceed();
            System.out.println(System.currentTimeMillis() - begin);
            return retVal;
        }
}
