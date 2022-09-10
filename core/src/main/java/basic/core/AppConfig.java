package basic.core;

import basic.core.discount.FixDiscountPolicy;
import basic.core.member.MemberService;
import basic.core.member.MemberServiceImpl;
import basic.core.member.MemoryMemberRepository;
import basic.core.order.OrderService;
import basic.core.order.OrderServiceImpl;

public class AppConfig {
    /*
    의존관계 주입을 위해 AppConfig를 만들어서 해당 클래스에서 무엇을 쓸지 결정한다.
    실제 실행하는 클래스에서는 무엇이 생성되었는지 모르고 실행만 하기 위해서 해당 클래스를 사용한다.
    이런 방식이 의존관계 주입이라고 한다.
     */
    public MemberService memberService(){
        return new MemberServiceImpl(new MemoryMemberRepository());
    }
    public OrderService orderService(){
        return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
    }
}
