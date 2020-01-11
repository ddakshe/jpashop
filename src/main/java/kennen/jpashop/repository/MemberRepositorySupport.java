package kennen.jpashop.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kennen.jpashop.domain.item.Member;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

import static kennen.jpashop.domain.item.QMember.member;

@Repository
public class MemberRepositorySupport extends QuerydslRepositorySupport {

    private final JPAQueryFactory queryFactory;

    public MemberRepositorySupport(JPAQueryFactory queryFactory) {
        super(Member.class);
        this.queryFactory = queryFactory;
    }


    public List<Member> findAllSearchName(String name){
        List<Member> fetch = queryFactory.selectFrom(member)
                                         .where(member.name.like(name))
                                         .fetch();
        return fetch;
    }

}
