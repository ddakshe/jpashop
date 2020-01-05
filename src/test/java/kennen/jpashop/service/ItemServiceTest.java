package kennen.jpashop.service;

import kennen.jpashop.domain.item.Book;
import kennen.jpashop.repository.ItemRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ItemServiceTest {

    @Autowired
    ItemService itemService;
    @Autowired
    ItemRepository itemRepository;

    @Test
    public void buy() {
        Book item = new Book();
        item.setAuthor("kkt");
        item.setIsbn("123123");

        itemService.saveItem(item);
        Assert.assertEquals(item, itemRepository.findOne(item.getId()));
    }

    @Test(expected = IllegalStateException.class)
    public void duplicateIsbn(){
        Book item = new Book();
        item.setAuthor("kkt");
        item.setIsbn("1234");

        Book item2 = new Book();
        item2.setAuthor("kkt");
        item2.setIsbn("1234");

        itemService.saveItem(item);
        itemService.saveItem(item2);

        Assert.fail("에러가 발생해야 한다.");
    }
}