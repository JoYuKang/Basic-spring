package basic.core;

import basic.core.member.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        Member member = new Member(1L, "민기", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findById(1L);
        System.out.println("new member : " + member.toString());
        System.out.println("find member : " + findMember.toString());
    }
}
