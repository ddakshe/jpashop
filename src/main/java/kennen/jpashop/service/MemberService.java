package kennen.jpashop.service;

import kennen.jpashop.domain.item.Member;
import kennen.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * 회원 가입
      */

    @Transactional
    public Long join(Member member){
        validateDuplicateMember(member);
        return memberRepository.save(member);
    }

    private void validateDuplicateMember(Member member) {
        /*DB 에서 name 을 unique 하게*/
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) throw new IllegalStateException("이미 존재하는 회원입니다.");
    }

    // 회원 전체 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId){
        return memberRepository.findOne(memberId);
    }
}
