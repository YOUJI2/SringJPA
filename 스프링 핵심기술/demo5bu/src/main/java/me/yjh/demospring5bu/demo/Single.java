package me.yjh.demospring5bu.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Single {

    //싱글이 프로토를 가지고 있게끔 하자
    @Autowired
     private Proto proto;

    public Proto getProto() {
        return proto;
    }
}
