package jpabook.jpashop.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.*;

@Entity
public class Category extends BaseEntity{

    @Id @GeneratedValue
    private Long id;

    private String name;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "PARENT_ID")
    private Category parent;

    @OneToMany(mappedBy = "parent")   //위에 parent하고 연결한다
    private List<Category> child = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "CATEGORY_ITEM",
    joinColumns = @JoinColumn(name = "CATEGORY_ID"),  //이건 내가 조인해야하는거고 (CATEGORY로 조인 )
    inverseJoinColumns = @JoinColumn(name = "ITEM_ID") //이건 반대쪽에서 조인을 해야하는것! (ITEM으로 조인)
    )
    private List<Item> items = new ArrayList<>();






}

