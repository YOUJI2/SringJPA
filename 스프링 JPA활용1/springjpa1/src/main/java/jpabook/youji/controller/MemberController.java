package jpabook.youji.controller;

import javassist.bytecode.annotation.MemberValue;
import jpabook.youji.domain.Address;
import jpabook.youji.domain.Member;
import jpabook.youji.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.dom4j.rule.Mode;
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

    @GetMapping("/members/new")
    public String createForm(Model model){
        model.addAttribute("memberForm", new MemberForm());
        return "members/createMemberForm";
    }


    @PostMapping("/members/new")
    public String create(@Valid MemberForm form, BindingResult result){ //@Valid로 유효하게 쓸 수 있다.
                                                //BindingResult 을 통해서 오류 를 같이 보내준다
        if(result.hasErrors()){
            return "members/createMemberForm"; //오류에 대한 정보를 가지고 다시 회원가입 폼으로 돌아간다
                        //th:class="${#fields.hasErrors('name')}? 'form-control fieldError' : 'form-control'">
                        // html에 위 기능으로 error메시지를 출력해준다.
        }

        Address address = new Address(form.getCity(), form.getStreet(), form.getZipcode());
        Member member = new Member();
        member.setName(form.getName());
        member.setAddress(address);

        memberService.join(member); //이걸하게 되면 저장이 된다.
        return "redirect:/"; //다시 첫번쨰 페이지로 이동한다.
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members",members);
        return "members/memberList";
    }


}
