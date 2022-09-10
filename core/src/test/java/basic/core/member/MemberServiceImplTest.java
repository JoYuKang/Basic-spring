package basic.core.member;

import basic.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceImplTest {

    MemberService memberService;

    @BeforeEach
    public void beforeEach(){
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }

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