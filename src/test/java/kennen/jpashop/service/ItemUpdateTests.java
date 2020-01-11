package kennen.jpashop.service;

import kennen.jpashop.domain.item.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemUpdateTests {

    @Autowired
    EntityManager entityManager;

    @Test
    public void updateTest(){
        Book book = entityManager.find(Book.class, 1L);

        //TX
        book.setName("sdfsdf");

        //변경 감지 == dirty checking
        //TX Commit
    }
}
