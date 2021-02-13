package me.yjh.demospring5bu.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Primary;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Locale;

@Component
public class AppRunner implements ApplicationRunner {

//    @Autowired
//    Single single;
//    @Autowired
//    Proto proto;

    @Autowired
    ApplicationContext ctx;

    //6부
    @Autowired
    BookRepository bookRepository;

   // @Value("${app.name}")
   // String appName;

    @Autowired
    MessageSource messageSource;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        //System.out.println(proto);
        //System.out.println(single.getProto());
        //찍어본 결과 레퍼런스 즉 값이 같다
        //me.yjh.demospring5bu.demo.Proto@1a2e2935
        //me.yjh.demospring5bu.demo.Proto@1a2e2935

//        5부 실습
//        System.out.println("proto"); //@Scope("prototype")에 의해 매번 다른 인스턴스가 찍힐것이다.
//
//        System.out.println(ctx.getBean(Proto.class));
//        System.out.println(ctx.getBean(Proto.class));
//        System.out.println(ctx.getBean(Proto.class));
//
//        System.out.println("single");
//
//        System.out.println(ctx.getBean(Single.class));
//        System.out.println(ctx.getBean(Single.class));
//        System.out.println(ctx.getBean(Single.class));
//
//
//        System.out.println("proto by single");
//
//        System.out.println(ctx.getBean(Single.class).getProto());
//        System.out.println(ctx.getBean(Single.class).getProto());
//        System.out.println(ctx.getBean(Single.class).getProto());

         //6부
        Environment environment = ctx.getEnvironment();
        //System.out.println(Arrays.toString(environment.getActiveProfiles()));
        //System.out.println(Arrays.toString(environment.getDefaultProfiles()));

        //6-2부
        //System.out.println(environment.getProperty("app.name")); //configuration에 VM옵션을 -Dapp.name=spring5 로 지정
        //System.out.println(environment.getProperty("app.about"));

        //6-2 vm이 계층이 더 높다
        //System.out.println(environment.getProperty("app.name"));
        //System.out.println(appName);

        //7부 - messageSource
        System.out.println(messageSource.getClass());
//        System.out.println(messageSource.getMessage("greeting", new String[]{"yyy"}, Locale.getDefault()));
        while(true) {
            System.out.println(messageSource.getMessage("greeting", new String[]{"yjh"}, Locale.US));
            System.out.println(messageSource.getMessage("greeting", new String[]{"yjh"}, Locale.getDefault()));
            Thread.sleep(1000l);
        }
    }
}
