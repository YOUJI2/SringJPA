package me.yjh.springbootSecurity;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {

    @GetMapping("/hello")
    public String Hello(){
        return "hello";
    }

    @GetMapping("/my")
    public String bye(){
        return "my";
    }


}
