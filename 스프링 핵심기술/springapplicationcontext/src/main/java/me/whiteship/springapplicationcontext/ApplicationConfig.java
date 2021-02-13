package me.whiteship.springapplicationcontext;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = DemoApplication.class)//모든 클래스에 붙어있는 @를 찾아 bean으로 등록해준다
public class ApplicationConfig {


    /*
    //이 방법도 xml과 비슷하게 일일히 한 방법이다 조금더 쉽게 할수 있다.---> @componentScan(basePackageClasses )
    @Bean
    public BookRepository bookRepository() {
        return new BookRepository();
    }

    @Bean
    public BookService bookService() {
        BookService bookService = new BookService();
        bookService.setBookRepository(bookRepository()); //setter가 있어서 의존성 주입을 해줄수 있었다.
        return bookService;
    }
    */




}
