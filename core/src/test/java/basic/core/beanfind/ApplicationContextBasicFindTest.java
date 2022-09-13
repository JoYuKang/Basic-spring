package basic.core.beanfind;

import basic.core.AppConfig;
import basic.core.member.MemberService;
import basic.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class ApplicationContextBasicFindTest  {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("bean 이름으로 조회")
    public void findByBeanName(){
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        System.out.println("memberService = " + memberService);
        System.out.println("memberService.getClass() = " + memberService.getClass());

         assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("bean 타입으로 조회")
    public void findByBeanType(){
        MemberService memberService = ac.getBean(  MemberService.class);
        System.out.println("memberService = " + memberService);
        System.out.println("memberService.getClass() = " + memberService.getClass());

         assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("구체 타입으로  조회")
    public void findByBeanName2(){
        // 인터페이스로 조회하지않고 클래스로도 조회가 가능하지만 역할에 의존해야지 구현에 의존해서는 안된다.
        MemberService memberService = ac.getBean("memberService", MemberServiceImpl .class);
        System.out.println("memberService = " + memberService);
        System.out.println("memberService.getClass() = " + memberService.getClass());

        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("빈 이름으로 조회 X")
    public void findByBeanX(){
        //MemberService bean = ac.getBean("xxxx", MemberService.class);
        assertThrows(NoSuchBeanDefinitionException.class, () ->  ac.getBean("xxxx", MemberService.class));

    }
}
