package basic.core.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceImplTest {

    MemberRepository memberRepository = new MemoryMemberRepository();
    MemberService memberService = new MemberServiceImpl(memberRepository);

    @Test
    void join() {
        //given
        Member member = new Member(1L,"민기",Grade.VIP);

        //when
        memberService.join(member);
        Member findMember = memberService.findById(1L);

        //then
        Assertions.assertThat(member).isEqualTo(findMember);

    }

    @Test
    void findById() {
    }
}