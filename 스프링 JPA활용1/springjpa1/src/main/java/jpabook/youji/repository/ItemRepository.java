package jpabook.youji.repository;

import jpabook.youji.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    public void save(Item item){
        if(item.getId() == null){ //아이디 값이 없다 == 새로 생성한 아이디
            em.persist(item);     //신규로 등록한다는 의미
        }else {
            em.merge(item);       //업데이트랑 비슷 하다고 생각하면 된다 . 일단은.... 뒤에서 설명할 예정
        }
    }

    public Item findOne(Long id){
        return em.find(Item.class, id);
    }

    public List<Item> findAll(){
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }

}
