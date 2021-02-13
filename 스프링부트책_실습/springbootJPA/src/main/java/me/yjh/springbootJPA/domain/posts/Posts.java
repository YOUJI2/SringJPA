package me.yjh.springbootJPA.domain.posts;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.yjh.springbootJPA.domain.BaseTimeEntity;

import javax.persistence.*;

@Getter             //롬복 기능으로 자동으로 getter 생성
@NoArgsConstructor  //기본 생성자를 자동으로 추가해준다!! -> 생성자 생성은 자동으로 빈 등록이 된다.
@Entity    //이 entity가 테이블과 링크될 클래스임을 의미한다!!   (JPA )
public class Posts extends BaseTimeEntity {

    @Id    //해당 테이블의 PK(primery key)필드를 나타낸다.
    @GeneratedValue(strategy = GenerationType.IDENTITY) //PK 생성 규칙을 의미한다. 반드시 추가해준다.
    private Long id;

    @Column(length = 500, nullable = false) //테이블의 칼럼을 나타낸다 없어도 자동으로 생긴다!!
    private String title;

    @Column(columnDefinition = "text", nullable = false)
    private String content;

    private String author;

    @Builder   // 해당 클래스의 빌더 패턴을 생성한다. 생성자에 포함된 필드만 빌더에 포함
    public Posts(String title, String content, String author){
        this.title=title;
        this.content=content;
        this.author=author;
    }


    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }



}
