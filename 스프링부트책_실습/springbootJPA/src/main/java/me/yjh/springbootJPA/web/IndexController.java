package me.yjh.springbootJPA.web;

import lombok.RequiredArgsConstructor;
import me.yjh.springbootJPA.config.auth.LoginUser;
import me.yjh.springbootJPA.config.auth.dto.SessionUser;
import me.yjh.springbootJPA.service.posts.PostService;
import me.yjh.springbootJPA.web.dto.PostsResponseDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostService postService;
    private final HttpSession httpSession;
    @GetMapping("/")
    public String index(Model model , @LoginUser SessionUser user){  //model은 서버 템플릿 엔진에서 사용할 수 있는 객체를 지정할 수 있다.
        model.addAttribute("posts" , postService.findAllDesc());
                        // findAllDesc에서 가져온 결과를 posts로 index.mustache에 전달한다.

        // @LoginUser 어노테이션으로 생략 가능하게 만들었다.
//        SessionUser user = (SessionUser) httpSession.getAttribute("user");

        if(user != null){
            model.addAttribute("userName", user.getName());
        }

        return "index";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model){

        PostsResponseDto dto = postService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }

    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }

}
