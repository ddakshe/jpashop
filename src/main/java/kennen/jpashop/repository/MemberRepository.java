package kennen.jpashop.repository;

import kennen.jpashop.domain.item.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor // Lombok 방식
public class MemberRepository {

//    전통 방식
//    @PersistenceUnit
//    private EntityManagerFactory managerFactory;

//    정석 방식
//    @PersistenceContext
//    private EntityManager em;

//    생성자 방식
//    public MemberRepository(EntityManager em) {
//        this.em = em;
//    }

    private final EntityManager em;

    public Long save(Member member) {
        em.persist(member);
        return member.getId();
    }

    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }

    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }

}
