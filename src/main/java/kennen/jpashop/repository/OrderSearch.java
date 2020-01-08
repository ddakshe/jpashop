package kennen.jpashop.repository;

import kennen.jpashop.domain.item.OrderStatus;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderSearch {
    private String memberName; // 주문한 사람
    private OrderStatus orderStatus; // 주문 상태
}
