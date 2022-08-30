package basic.foundation.service;

import basic.foundation.domain.Member;
import basic.foundation.repository.MemoryMemberRepository;
import org.junit.jupiter.api.*;


public class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void signup() {
        //given
        Member member = new Member();
        member.setName("지원");
        //when
        Long saveId = memberService.signup(member);

        //then
        Member result = memberService.findOne(saveId).get();
        //Assertions.assertThat(member.getName()).isEqualTo(result.getName());

        Assertions.assertEquals(member.getName(), result.getName());
    }

    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("석영");
        Member member2 = new Member();
        member2.setName("석영");
        //when
        memberService.signup(member1);
        //memberService.signup(member2);

        //then
        IllegalStateException e = Assertions.assertThrows(IllegalStateException.class, () -> memberService.signup(member2));
        Assertions.assertEquals(e.getMessage(),"이미 존재하는 회원입니다.");

    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}
