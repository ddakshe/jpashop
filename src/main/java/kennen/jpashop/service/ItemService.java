package kennen.jpashop.service;

import kennen.jpashop.domain.item.Book;
import kennen.jpashop.domain.item.Item;
import kennen.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    /**
     * Merge 방식의 Update
     */
    @Transactional
    public void saveItem(Book book){
//        Long countByName = itemRepository.countByName(book.getIsbn());
//        if (countByName > 0) {
//            throw new IllegalStateException("이미 존재하는 ISBN 입니다.");
//        }
        itemRepository.save(book);
    }

    /**
     * 변경 감지를 통한 Update
     */
    @Transactional
    public void updateItem(Long itemId, Book param){
        Item findItem = itemRepository.findOne(itemId);
        findItem.setPrice(param.getPrice());
        findItem.setName(param.getName());
        findItem.setStockQuantity(param.getStockQuantity());
    }

    public List<Item> findItems(){
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId){
        return itemRepository.findOne(itemId);
    }

    public void updateItem(Long itemId, String name, int price, int stockQuantity) {
        Item findItem = itemRepository.findOne(itemId);
        findItem.setName(name);
        findItem.setPrice(price);
        findItem.setStockQuantity(stockQuantity);
    }
}