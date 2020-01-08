package kennen.jpashop.repository;

import kennen.jpashop.domain.item.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final EntityManager em;

    public void save(Order order){
        em.persist(order);
    }

    public Order findOne(Long id){
        return em.find(Order.class, id);
    }

    /*사용 불가*/
    public List<Order> findAll(OrderSearch orderSearch){
        List<Order> resultList = em.createQuery("select o from Order o join o.member m " +
                "where o.status = :status " +
                "and m.name like :name", Order.class)
                .setParameter("status", orderSearch.getOrderStatus())
                .setParameter("name", orderSearch.getMemberName())
                .setFirstResult(100) //시작 지
                .setMaxResults(1000) //최대 갯수
                .getResultList();
        return resultList;
    }

    /**
     * JPA Criteria
     */
    public List<Order> findAllByCriteria(OrderSearch orderSearch){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Order> query = cb.createQuery(Order.class);
        Root<Order> from = query.from(Order.class);
        Join<Object, Object> member = from.join("member", JoinType.INNER);

        List<Predicate> criteria = new ArrayList<>();

        // 주문 상태 검색
        if(orderSearch.getOrderStatus() != null){
            Predicate status = cb.equal(from.get("status"), orderSearch.getOrderStatus());
            criteria.add(status);
        }

        // 회원 이름 검색
        if(StringUtils.hasText(orderSearch.getMemberName())){
            Predicate name = cb.like(from.get("name"), "%" + orderSearch.getMemberName() + "%");
            criteria.add(name);
        }
        query.where(cb.and(criteria.toArray(new Predicate[criteria.size()])));
        TypedQuery<Order> orderTypedQuery = em.createQuery(query).setMaxResults(1000);
        return orderTypedQuery.getResultList();
    }

}