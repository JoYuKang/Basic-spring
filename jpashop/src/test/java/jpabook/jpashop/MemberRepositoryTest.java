package jpabook.jpashop;

import jpabook.jpashop.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
//@ExtendWith(SpringExtension.class) // @RunWith(SpringRunner.class) junit4에서 사용 junit5에서는 @ExtendWith 사용(생략 가능)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    @Transactional
    void save() {

        Member member1 = new Member();
        member1.setUsername("지원");
        Long saveMemberId = memberRepository.save(member1);
        Member findMember = memberRepository.find(saveMemberId);
        Assertions.assertThat(member1.getId()).isEqualTo(saveMemberId);
        Assertions.assertThat(member1).isEqualTo(findMember);

    }

    @Test
    void find() {
    }




}