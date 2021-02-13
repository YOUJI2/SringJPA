package me.yjh.springbootJPA.domain.posts;


import me.yjh.springbootJPA.domain.posts.Posts;
import me.yjh.springbootJPA.domain.posts.PostsRepository;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.Matchers.is;
//import static org.junit.Assert.assertThat;


import java.time.LocalDateTime;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {  //Post가 잘 만들어 졌는지 Test한다.

    //먼저 테이블 클래스의 DAO를 주입해준다.
    @Autowired
    PostsRepository postsRepository;

    @After       //junit에서 단위 테스트가 끝날때마다 수행하는 메소드를 지정 + 테스트간 침범을 막기 위해 사용한다.
    public void cleanUp(){
        postsRepository.deleteAll();
    }

//    @Test
    public void 게시글저장_불러오기(){

        //given :  content, title, author
        String title = "Springboot and Aws";   //데이터를 저장할걸 지정해준다
        String content = "블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라블라";

        // 테이블(Posts)에 insert / update 쿼릐를 실행한다.
        // 만약 ID가 값이 있다면 UPdate가 발생하고, 없으면 insert쿼리가 실행된다.
        postsRepository.save(Posts.builder()
                                .title(title)
                                .content(content)
                                .author("dbwlgna98@naver.com")
                                .build());

        //when
        List<Posts> postsList = postsRepository.findAll(); //테이블posts에 있는 모든 데이터를 불러온다.

        //then
        Posts post = postsList.get(0);
        assertThat(post.getTitle()).isEqualTo(title);   //검증 절차
        assertThat(post.getContent()).isEqualTo(content);
    }

    @Test
    public void BaseTimeEntity_등록(){

        //given
        LocalDateTime now = LocalDateTime.of(2020,7,29,0,0,0);
        postsRepository.save(Posts.builder()
        .title("title")
        .content("content")
        .author("jihoon")
        .build());

        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);

        System.out.println(">>>>>>>> createDate="+posts.getCreatedDate()+", modifiedDate="+posts.getModifiedDate());

        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);


    }

}
