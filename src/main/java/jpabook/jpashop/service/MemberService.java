package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    //회원 가입
    public Long join(Member member) {
        validateDuplicateMember(member);
        memberRepository.saveMember(member);
        return member.getId();
    }

    //회원 전체 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Member findMemberById(Long memberId) {
        return memberRepository.findMemberById(memberId);
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findMemberByName(member.getUsername());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }
}
