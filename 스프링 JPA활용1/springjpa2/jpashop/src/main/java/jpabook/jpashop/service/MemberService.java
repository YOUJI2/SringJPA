package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional   //이렇게 전체적으로 걸어도 되고 메소드 마다 상황에 맞게 써도 된다.
@RequiredArgsConstructor
public class MemberService {

    //필드 인젝션 방법중 하나
    // 1. @autowired사용
//  @Autowired
    //final 을 붙임으로서 밑에 생성자에서 값 세팅을 해주지 않으면 빨간줄로 알려준다. == 컴파일시점에 체크해준다.
    private final MemberRepository memberRepository;

    //위에 필드로는 한번 주입되면 바꿀 수 없게된다.
    // 2. setter 방법
    //그래서 좀더 좋은방법으로 setter injection을 사용해 준다.
    //하지만 이경우에도 치명적인 단점이 있다. 보통 우리가 어플리케이션을 실행하면 모든 로직이 완성되어 올라간다.
    //그리고 set을 통해서 한번 등록한 경우에는 다시 바꿀일은 거의 없기 때문에 좋지는 않다
    //한가지 장점이 있다면 test를 할때 가자 repo를 넣어주어 사용이 가능하다 .
//    @Autowired
//    public void setMemberRepository(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }

    //3. 생성자 injection 방법
    // 요즘 권장하는 방법이다.
    // 이렇게 하면 메인에서도 service를 생성할때 파라미터값으로 무엇이 들어가야하는지 정확히 알려줄 수 있다.
    //@Autowired를 생략해도 상관없다 . (생성자가 하나있으면 자동적으로 injection해준다. )
//    @Autowired
//    public MemberService(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }
    //*****************************
    // 코드를 좀더 간결하게 해주고 싶다면 롬복의 기능중 하나인 class 위에 @AllArgsConstructor를 해주면 위에 메서드를 자동생성해준다.
    // final 로 묶여있다면 @RequiredArgsConstructor로 해주면 알아서 생성해준다. ===> 최종적으로 가장 깔끔하다.
    //******************************

    //회원 가입
    public Long join(Member member){ //단순히 member만 레포에 넣어주면 된다.
        //중복 방지를 위해서 예외문을 만든다.
        validateDuplicateMember(member);  //중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        //이로직을 통해서는 만약 DB에 동시에 같은 이름으로 insert시 이 validation을 그냥 통과하게 된다.
        //따라서 이렇게 통과하게되면 최종적으로 검증할 메소드를 하나 더 만들어 두어야한다.
        // 이름에 대한 uniqe 제약조건이라든지, 멀티스레드를 고려한 검증이 필요하다!!


        //exception
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    //회원 전체 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }


    //회원 한건만 조회
    //@Transactional(readOnly = true) //이렇게 하면 내용을 변경하지 않고 단순 불러오는 방식으로 최적화된 방법으로 DB에게 요청하여 가져온다.
    public Member findOne(Long memberId){
        return memberRepository.findOne(memberId);
    }


    //회원수정API - 변경 감지 이용
    @Transactional
    public void update(Long id, String name) {
        Member member = memberRepository.findOne(id);
        member.setName(name);
    }


}
