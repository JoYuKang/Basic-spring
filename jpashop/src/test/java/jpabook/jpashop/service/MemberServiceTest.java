package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    void join() {

        Member member = new Member();
        member.setName("kim");
        Long joinId = memberService.join(member);

        Assertions.assertThat(joinId).isEqualTo(member.getId());
    }

    @Test
    void validateDuplicateMember() {
        Member member1 = new Member();
        Member member2 = new Member();
        member2.setName("kim");
        member1.setName("kim");
        memberService.join(member1);
        try {
            memberService.join(member2);
        }catch (IllegalStateException e){
            return;
        }


    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}