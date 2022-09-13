package basic.core;

import basic.core.member.*;
import basic.core.order.Order;
import basic.core.order.OrderService;
import basic.core.order.OrderServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {
    public static void main(String[] args) {
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();
//        OrderService orderService = appConfig.orderService();

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        OrderService orderService = applicationContext.getBean("orderService", OrderService.class);

        Long memberId = 1L;
        Member member = new Member(memberId,"지원",Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "맥북에어", 1200000);
        System.out.println("order : " + order.toString());
        System.out.println("order calculate : " + order.calculatePrice());


    }
}
