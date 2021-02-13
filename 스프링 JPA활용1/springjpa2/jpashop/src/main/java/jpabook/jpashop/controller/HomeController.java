package jpabook.jpashop.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@Slf4j   //롬복이 있으면 사용가능하다 .
public class HomeController {

    @RequestMapping("/")
    public String hone(){
        log.info("home controller");  //홈컨트롤러에 대한 것이 찍힌다.
        return "home";
    }



}
