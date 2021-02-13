package me.yjh.springbootJPA.web;

import lombok.RequiredArgsConstructor;
import me.yjh.springbootJPA.service.posts.PostService;
import me.yjh.springbootJPA.web.dto.PostsResponseDto;
import me.yjh.springbootJPA.web.dto.PostsSaveRequestDto;
import me.yjh.springbootJPA.web.dto.PostsUpdateRequestDto;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor //final 선언 필드 생성자생성
@RestController
public class PostsApiController { //외부요청과 응답에 전반적인 역할을 수행한다.! => web으로 API 날리는 controller
                                    //web페이지에서 api 호출시 controller에 의해서 service로 요청을 보낸다.
    private final PostService postService;

    //등록
    @PostMapping("/api/v1/posts")  //mapping 요청이 있을시 DTO의 데이터를 가져와서 service로 보내준다.
    public Long save(@RequestBody PostsSaveRequestDto requestDto){ //항상 보낼때 request바디에 실어서 보낸다.
        return postService.save(requestDto); //서비스의 save로 데이터 전송!
}

    //수정
    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id ,@RequestBody PostsUpdateRequestDto rrequestDto){
        return postService.update(id,rrequestDto);
    }
    // insert 문을 날리고 update 해보기
    // UPDATE posts SET content='change content', title='change title' where id=1;
    //결과값을 보면 content와 title이 바뀌는걸 알 수 있다.

    //조회
    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id){
        return postService.findById(id);
    }

    //삭제
    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id){
        postService.delete(id);
        return id;
    }


}
