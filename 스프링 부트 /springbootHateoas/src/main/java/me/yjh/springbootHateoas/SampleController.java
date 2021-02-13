package me.yjh.springbootHateoas;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.hateoas.Resource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class SampleController {

    @GetMapping("/hello")
    public Resource<Hello> hello(){

        Hello hello = new Hello();
        hello.setPrefix("Hey,");
        hello.setName("jihoon");

        //링크정보 추가하기
        Resource<Hello> helloResource = new Resource< >(hello);
        helloResource.add(linkTo(methodOn(SampleController.class).hello()).withSelfRel());


        return helloResource;
    }
}
