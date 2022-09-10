package basic.core;

import basic.core.member.*;
import basic.core.order.Order;
import basic.core.order.OrderService;
import basic.core.order.OrderServiceImpl;

public class OrderApp {
    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();
        OrderService orderService = appConfig.orderService();

        Long memberId = 1L;
        Member member = new Member(memberId,"지원",Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "맥북에어", 1200000);
        System.out.println("order : " + order.toString());
        System.out.println("order calculate : " + order.calculatePrice());


    }
}
