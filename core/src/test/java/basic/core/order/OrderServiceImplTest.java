package basic.core.order;

import basic.core.member.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceImplTest {

    MemberRepository memberRepository = new MemoryMemberRepository();
    MemberService memberService = new MemberServiceImpl(memberRepository);
    OrderService orderService = new OrderServiceImpl();

    @Test
    void createOrder() {

        Long memberId = 1L;
        Member member = new Member(memberId, "지원", Grade.VIP);
        memberService.join(member);
        Order order = orderService.createOrder(memberId, "부트캠프", 1900000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);

    }
}