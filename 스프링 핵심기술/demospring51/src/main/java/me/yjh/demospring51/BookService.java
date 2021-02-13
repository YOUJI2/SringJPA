package me.yjh.demospring51;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.ls.LSOutput;

import java.awt.print.Book;
import java.util.List;

@Service
public class BookService {


      // 방법 1    - 생성자 사용  : 빈을 만들때도 개입이 된다
//    autowire을 이용한 의존성 주입
//    @Autowired
//    BookRepository bookRepository;
//    public BookService(BookRepository bookRepository){
//        this.bookRepository = bookRepository;
//    }

    //방법 2   -  세터에 사용
//    BookRepository bookRepository;
//    @Autowired(required = false)
//    //required를 통해 옵셔널 설정 인스턴스만 만들어서 반으로 등록을 했고
//    //bookrepository는 의존성 주입이 안된상태로 등록이 된것이다!
//    public void setBookRepository(BookRepository bookRepository) {
//        this.bookRepository = bookRepository;
//    }

    //방법 3    -   필드에 사용
//  @Autowired(required = false) //옵셔널로 북서비스가 해당하는 의존성 없이도 빈으로 등록할수 있도록 한다
//  BookRepository bookRepository;



//    @Autowired
//    BookRepository bookRepository;
//

    //같은 타입의 빈 여러개 받기 !!
    @Autowired
    List<BookRepository> bookRepositories;

    public void printBookRepository(){
        this.bookRepositories.forEach(System.out::println);
//        System.out.println(bookRepository.getClass());
    }



}

//IOC 3부 autowire 2:55부터 듣기