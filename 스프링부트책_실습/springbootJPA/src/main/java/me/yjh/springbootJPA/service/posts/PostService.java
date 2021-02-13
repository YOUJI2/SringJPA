package me.yjh.springbootJPA.service.posts;

import com.samskivert.mustache.Mustache;
import lombok.RequiredArgsConstructor;
import me.yjh.springbootJPA.domain.posts.Posts;
import me.yjh.springbootJPA.domain.posts.PostsRepository;
import me.yjh.springbootJPA.web.dto.PostListResponseDto;
import me.yjh.springbootJPA.web.dto.PostsResponseDto;
import me.yjh.springbootJPA.web.dto.PostsSaveRequestDto;
import me.yjh.springbootJPA.web.dto.PostsUpdateRequestDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;


@RequiredArgsConstructor  //final로 선언된 모든 필드를 인자값으로 하는 생성자를 만든다. (빈으로 등록된다.)
@Service
public class PostService { //controller와 DAO(repository)의 중간역할을 한다.

    private final PostsRepository postsRepository;

    //트랜잭션의 목적은 .save로 쿼리를 발생시킬때 오류가 나면 자동으로 rollback시키기 위해서다.
    @Transactional // 이클래스에 트랜잭션 기능이 적용된 프록시 객체가 생성된다.
    public Long save(PostsSaveRequestDto requestDto) { //DTO로 저장된 데이터를 가져와서 쿼리를 발생시킨다.
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    //update를 위한 쿼리 날리기
    @Transactional   //id와 update DTO 를 확인
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        posts.update(requestDto.getTitle() , requestDto.getContent());
        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id+" + id));
        return new PostsResponseDto(entity);
    }

    //전제조회
    @Transactional
    public List<PostListResponseDto> findAllDesc(){
        return postsRepository.findAllDesc().stream()
                .map(PostListResponseDto::new)
                .collect(Collectors.toList());
    }

    //삭제
    @Transactional
    public void delete (Long id){
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException
                ("해당 게시글이 없습니다.. Id+"+id));
        postsRepository.delete(posts);
    }

}
