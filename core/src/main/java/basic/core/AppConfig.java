package basic.core;

import basic.core.discount.DiscountPolicy;
import basic.core.discount.FixDiscountPolicy;
import basic.core.discount.RateDiscountPolicy;
import basic.core.member.MemberRepository;
import basic.core.member.MemberService;
import basic.core.member.MemberServiceImpl;
import basic.core.member.MemoryMemberRepository;
import basic.core.order.OrderService;
import basic.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

 @Configuration // spring 설정정보
public class AppConfig {
    /*
    의존관계 주입을 위해 AppConfig를 만들어서 해당 클래스에서 무엇을 쓸지 결정한다.
    실제 실행하는 클래스에서는 무엇이 생성되었는지 모르고 실행만 하기 위해서 해당 클래스를 사용한다.
    이런 방식이 의존관계 주입이라고 한다.
    Bean을 사용하면 Spring 컨테이너에 등록이 된다.
     */
    @Bean
    public MemberService memberService(){

        System.out.println("AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {

        System.out.println("AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService(){

        System.out.println("AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy(){
        //return new FixDiscountPolicy();
        System.out.println("AppConfig.discountPolicy");
        return new RateDiscountPolicy();
    }

}
