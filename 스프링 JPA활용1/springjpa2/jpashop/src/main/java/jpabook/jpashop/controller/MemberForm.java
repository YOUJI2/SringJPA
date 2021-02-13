package jpabook.jpashop.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class MemberForm {  //html 홈에서 회원등록으로 가기 위해 만들어야 하는 class

    @NotEmpty(message = "회원 이름은 필수 입니다")
    private String name;  //값이 비어있게 되면 오류 메시지를 전달해준다.

    private String city;
    private String street;
    private String zipcode;

}
