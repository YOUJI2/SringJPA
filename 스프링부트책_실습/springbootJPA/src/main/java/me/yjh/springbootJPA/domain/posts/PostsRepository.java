package me.yjh.springbootJPA.domain.posts;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

//이 interface는 Posts클래스로 Database를 접근하게 해줄 DBlayer이다
//보통 DAO라고 생각하면 되고 JPA에서는 repository라고 부른다.
//이렇게 상속만 받으면 CRUD 메소드가 자동으로 추가된다.
// posts 와 repository는 함께 세트로 생각한다.
public interface PostsRepository extends JpaRepository<Posts, Long> { //post 클래스, PK 타입 설정

    @Query("SELECT p From Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();

}
