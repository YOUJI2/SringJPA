package jpabook.youji.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded
    private Address address;

    //연관관계의 거울 역할을 한다 그래서 mappedBy = "member" => 난 mamber에 의해 맵핑 받는 다
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

}
