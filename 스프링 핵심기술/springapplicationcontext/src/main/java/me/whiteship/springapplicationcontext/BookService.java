package me.whiteship.springapplicationcontext;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    //autowired를 통해 의존성을 주입하도록 한다.
    //@Autowired
    BookRepository bookRepository;

    // command + N : 생성자 단축키
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
}
