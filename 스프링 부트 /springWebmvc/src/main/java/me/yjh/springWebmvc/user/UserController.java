package me.yjh.springWebmvc.user;

import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {


    //@RestController 에 public 다음에 @ResponseBody가 생략 되어 있다.
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    // @ResponseBody 생략 가능 !!! (@RestController)
//    @PostMapping
//    public @ResponseBody User create(@RequestBody User user){
//        return null;
//    }

    @PostMapping("/users/create")
    public User create(@RequestBody User user){


        return user;
    }

}
