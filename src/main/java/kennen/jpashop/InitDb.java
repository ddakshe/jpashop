package kennen.jpashop;

import kennen.jpashop.domain.item.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

/**
 *
 */

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init(){
        initService.dbInit1();
        initService.dbInit2();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService{

        private final EntityManager em;

        public void dbInit1(){
            Member member = creatMember("memberA", "서울", "1", "2");

            Book book = createBook("JPA BOok", 10000, 100);

            Book book2 = createBook("JPA BOok2", 20000, 100);

            OrderItem orderItem = OrderItem.createOrderItem(book, 10000, 1);
            OrderItem orderItem1 = OrderItem.createOrderItem(book2, 20000, 2);

            Delivery delivery = createDelivery(member);
            Order order = Order.createOrder(member, delivery, orderItem, orderItem1);

            em.persist(order);
        }

        public void dbInit2(){
            Member member = creatMember("memberB", "부산", "2", "3");

            Book book = createBook("spring1 BOok", 30000, 100);

            Book book2 = createBook("spring2 BOok2", 40000, 100);

            OrderItem orderItem = OrderItem.createOrderItem(book, 30000, 3);
            OrderItem orderItem1 = OrderItem.createOrderItem(book2, 40000, 4);

            Delivery delivery = createDelivery(member);
            Order order = Order.createOrder(member, delivery, orderItem, orderItem1);

            em.persist(order);
        }

        private Book createBook(String s, int i, int stockQuantity) {
            Book book = new Book();
            book.setName(s);
            book.setPrice(i);
            book.setStockQuantity(stockQuantity);
            em.persist(book);
            return book;
        }

        private Member creatMember(String name, String city, String street, String zipCode) {
            Member member = new Member();
            member.setName(name);
            member.setAddress(new Address(city, street, zipCode));
            em.persist(member);
            return member;
        }

    }

    private static Delivery createDelivery(Member member) {
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());
        return delivery;
    }

}
