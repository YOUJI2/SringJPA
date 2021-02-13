package jpabook.jpashop.controller;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members/new")  //회원가입을 열어보는걸 의미
    public String createForm(Model model){ //여기서 model이란
        //controller에서 view로 넘어갈때 model에 데이터를 실어서 전송한다.
        model.addAttribute("memberForm",new MemberForm());
        return "members/createMemberForm";
    }

    @PostMapping("/members/new") //회원가입에 실제로 데이터를 등록하는것
    public String create(@Valid MemberForm form, BindingResult result){ //memberform이 넘어온다.
                            //valid을 통해 notempty기능 외 여러가지를 쓸 수 있다.
                            //@valid다음에 BindingResult를 쓰게 되면 form자체가 오류가 담겨서 실행이 된다.
        if(result.hasErrors()){  //여기로 넘어온다는건 form에서의 error를 감지
            return "/members/createMemberForm";
        }


        Address address = new Address(form.getCity(),form.getStreet(),form.getZipcode());

        Member member = new Member();
        member.setName(form.getName());
        member.setAddress(address);

        memberService.join(member); //호출하면 저장이 완료

        return "redirect:/"; //이러면 첫번쨰 페이지로 이동한다 (홈)
    }


    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "/members/memberList";
    }



}

