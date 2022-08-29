package basic.foundation.repository;

import basic.foundation.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemberRepositoryTest {

    MemoryMemberRepository memberRepository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("지원");

        memberRepository.save(member);

        //memberRepository.findByName("지원");
        Member result = memberRepository.findById(member.getId()).get();
        Member member2 = new Member();
        member2.setName("민기");

        //Assertions.assertEquals(member, result);

        //Assertions.assertEquals(member, member2);
        assertThat(member).isEqualTo(result);


    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("석영");
        memberRepository.save(member1);

        Member member2 = new Member();
        member2.setName("유진");
        memberRepository.save(member2);

        Member result = memberRepository.findByName("석영").get();

        assertThat(member1).isEqualTo(result);

    }

    @Test
    public void sizeMember(){
        Member member1 = new Member();
        member1.setName("석영");
        memberRepository.save(member1);

        Member member2 = new Member();
        member2.setName("유진");
        memberRepository.save(member2);

        List<Member> memberList = memberRepository.findAll();

        assertThat(memberList.size()).isEqualTo(2);
    }


}
