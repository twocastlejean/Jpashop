package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
@Transactional
public class MemberServiceTest {
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    MemberService memberService;


    @Test
    public void 회원가입() throws Exception {
        //given
        Member member = new Member();
        member.setUsername("kim");

        //when
        Long savedId = memberService.join(member);

        //then
        Assertions.assertEquals(member, memberRepository.findMemberById(savedId));
    }

    @Test
    public void 중복_회원_예외() throws Exception{
        //given
        Member member1 = new Member();
        member1.setUsername("kim");

        Member member2 = new Member();
        member2.setUsername("kim");

        //when
        memberService.join(member1);
        try {
            memberService.join(member2);
        } catch (IllegalStateException e) {
            return;
        }

        //then
        fail("예외가 발생해야 한다");
    }
}