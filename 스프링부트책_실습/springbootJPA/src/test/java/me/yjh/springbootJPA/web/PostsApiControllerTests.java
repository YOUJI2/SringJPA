package me.yjh.springbootJPA.web;

import me.yjh.springbootJPA.domain.posts.Posts;
import me.yjh.springbootJPA.domain.posts.PostsRepository;
import me.yjh.springbootJPA.web.dto.PostsSaveRequestDto;
import me.yjh.springbootJPA.web.dto.PostsUpdateRequestDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static me.yjh.springbootJPA.domain.posts.Posts.builder;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) //랜덤포트를 발생시킨다.
public class PostsApiControllerTests {

    @LocalServerPort
    private int port; //지전된 port로 위에서 생성한 랜덤 포트가 들어간다.

    @Autowired
    private TestRestTemplate restTemplate;  //외부 연동과 관련된 부분만 확성화되고 JPA기능까지 테스트가 가능하다.

    @Autowired
    private PostsRepository postsRepository; //CRUD메서드 실행을 위해 사용

    @After
    public void tearDown() throws Exception{
        postsRepository.deleteAll();     //delete 쿼리를 발생시킨다.
    }

//    @Test
    public void posts_등록된다() throws Exception{

        //given
        String title = "title123";
        String content = "conconconconconc";
        PostsSaveRequestDto postsSaveRequestDto = PostsSaveRequestDto.builder() //DTO로 인서트 발생시킨다.
                                .title(title)
                                .content(content)
                                .author("youjihoon")
                                .build();


        String url = "http://localhost:" +port+"/api/v1/posts";  //url 생성

        //when
        // URL 과 만든 DTO + PK 타입
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url,postsSaveRequestDto, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> all = postsRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(title);
        assertThat(all.get(0).getContent()).isEqualTo(content);
    }

    @Test
    public void Posts_수정된다() throws Exception{

        //given
        Posts savePosts = postsRepository.save(Posts.builder()
        .title("title")
        .content("content")
        .author("auth")
        .build());

        Long updateId = savePosts.getId();
        String expectedtitle = "title2";
        String expectedcontent = "content2";
        PostsUpdateRequestDto requestDto = PostsUpdateRequestDto.builder()
                .title(expectedtitle)
                .content(expectedcontent)
                .build();

        String url = "http://localhost:" +port+"/api/v1/posts/" + updateId;
        HttpEntity<PostsUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);


        //when
        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> all = postsRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(expectedtitle);
        assertThat(all.get(0).getContent()).isEqualTo(expectedtitle);
    }


}
