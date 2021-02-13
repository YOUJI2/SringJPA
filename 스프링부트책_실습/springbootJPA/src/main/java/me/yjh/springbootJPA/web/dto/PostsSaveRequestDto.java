package me.yjh.springbootJPA.web.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.yjh.springbootJPA.domain.posts.Posts;

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {

    private String title;
    private String content;
    private String author;

    //생성자로 하여금 빌드시킨다.
    @Builder
    public PostsSaveRequestDto(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    //entity클랳스는 데이터베이스와 맞닿은 핵심클래스이다, 그리고 entity클래스를 request와 response클래스로 사용해서는 안된다.
    public Posts toEntity() {
        return Posts.builder()
                    .title(title)
                    .content(content)
                    .author(author)
                    .build();
    }


}
