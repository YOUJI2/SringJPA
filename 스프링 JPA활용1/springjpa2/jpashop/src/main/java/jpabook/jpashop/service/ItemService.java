package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true) //오직 읽기만 하기 떄문에 다른곳은 트랜잭션을 걸어주어야 한다.
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    //변경감지 (더티체킹 사용 )
    @Transactional
    public void updateItem(Long itemId, String name, int price, int stockQuantity){
        Item findItem = itemRepository.fineOne(itemId);
        findItem.setPrice(price);
        findItem.setName(name);
        findItem.setStockQuantity(stockQuantity);
    }



    //저장을 해야하기 때문에 트랜잭션을 걸어준다.
    @Transactional
    public void saveItem(Item item){
        itemRepository.save(item);
    }

    //트랜잭션이 없기때문에 readonly가 먹히게 된다.
    public List<Item> findItems(){
        return itemRepository.findAll();
    }

    //이것도 단순히 읽기 전용이기 때문에 readonly로 읽히게 한다.
    public Item findOne(Long itemId){
        return itemRepository.fineOne(itemId);
    }

}
