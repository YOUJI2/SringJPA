package jpabook.jpashop.api;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MemberApiController {
        //restController에 대한 자세한 내용은 MVC 강의를 참고하도록 하자

    private final MemberService memberService;
    @PostMapping("/api/v1/members")  //version1
    public CreateMemberResponse saveMemberV1(@RequestBody @Valid Member member){ //spring mvc내
                //json 데이터를 valid를 통해 member값에 넣어준다.

            // 결국 이 방식은 나중에 큰 장애를 불러온다. (엔티티를 그대로 받아오는 방법)
            // ==> ex) name => username으로 변경되는 순간 api가 작동을 안하게 된다.
            // ==> 엔티티 (json)으로 오는걸 바로 바인딩해서 사용하면 안된다.!!
            // api에서 받아오는 body를 그대로 받으면 안되ㅣ고 별도의 DTO를 만들어서
            //   == api 스펙을 위한 별도의 data Transion Object를 만들어 주어야 한다.
            // 변환한다음 받아야한다. (엔티티를 parameter로 받지 말자)
            // 그것을 v2로 해서 만들거다!

        Long id = memberService.join(member);
        return new CreateMemberResponse(id);
    }

    @PostMapping("/api/v2/members")  //이렇게 해야 안전해 진다.
    public CreateMemberResponse saveMemberV2(@RequestBody @Valid CreateMemberRequest request){

        Member member = new Member();
        member.setName(request.getName());

        Long id = memberService.join(member);
        return new CreateMemberResponse(id);
    }


    //회원수정 API
    @PutMapping("/api/v2/members/{id}") //응답 response와 응답 request dto를 따로 구성함
    public UpdateMemberResponse updateMemberV2(
            @PathVariable("id") Long id,
            @RequestBody @Valid UpdateMemberRequest request){

        //될수 있으면 변경감지 이용
        memberService.update(id, request.getName());
        Member findMember = memberService.findOne(id);
        return new UpdateMemberResponse(findMember.getId(), findMember.getName());
    }


    //모든 회원 조회
    @GetMapping("api/v1/members")
    public List<Member> membersV1(){
        return memberService.findMembers();
    }



    @GetMapping("/api/v2/members")
    public Result memberV2(){
        List<Member> findMembers = memberService.findMembers();
        List<MemberDTO> collect = findMembers.stream()    //member DTO 로 바꿔줘야한다.
                .map(m -> new MemberDTO(m.getName()))
                .collect(Collectors.toList());

        return new Result(collect.size(), collect);
    }

    @Data
    @AllArgsConstructor
    static class Result<T>{
        private int count;
        private T data;

    }

    @Data
    @AllArgsConstructor
    static class MemberDTO{   //DTO가 엔티티와 1:1 대응하여 필요한 부분만 보여줄 수 있다.(반드시 변환 필요)
        private String name;
    }


    @Data
    static class UpdateMemberRequest{
        private String name;
    }

    @Data
    @AllArgsConstructor
    static class UpdateMemberResponse{
        private Long id;
        private String name;
    }


    @Data
    static class CreateMemberRequest{
        private String name;
    }



    @Data
    static class CreateMemberResponse{
        private Long id;

        public CreateMemberResponse(Long id) {
            this.id = id;
        }
    }
}
