package me.yjh.springbootJPA.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import me.yjh.springbootJPA.domain.posts.Posts;

@Getter
public class PostsResponseDto {  //DTO는 Controller 와 Service에서 사용된다.
    private Long id;
    private String title;
    private String content;
    private String author;

    public PostsResponseDto(Posts entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
    }
}
