package hello.login.domain.member;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MemberRepositoryTest {

    private MemberRepository memberRepository = new MemberRepository();


    @Test
    void save() {
        Member member = new Member();
        member.setName("김기리");
        member.setPassword("1234");
        member.setLoginId("test!@");
        memberRepository.save(member);

        List<Member> members = memberRepository.findAll();
        Member findMember = memberRepository.findById(member.getId());

        for (Member m: members) {
            System.out.println("m = " + m);
        }
        System.out.println("findMember = " + findMember);

    }
}