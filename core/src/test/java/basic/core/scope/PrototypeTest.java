package basic.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class PrototypeTest {
    /*
        프로토타입 빈은 스프링 컨테이너에서 생성과 의존관계 주입 그리고 초기화 까지만 관여하고 더이상 관리하지 않는다. close()메소드를 사용해도
        종료되지 않는다.클라이언트가 직접 종료시켜줘야 한다.
        프로토타입 빈은 클라이언트가 요청을 보낼 때 마다 스프링 컨테이너에서 새로 생성 후 클라이언트에게 생성된 빈을 넘겨주어 직접 관리하게 만든다.
     */
    @Test
    void prototypeBeanFind(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean bean1 = ac.getBean(PrototypeBean.class);
        System.out.println("bean1 = " + bean1);

        PrototypeBean bean2= ac.getBean(PrototypeBean.class);
        System.out.println("bean2 = " + bean2);

        Assertions.assertThat(bean1).isNotSameAs(bean2);
        ac.close();
    }

    @Scope("prototype")
    static class PrototypeBean{
        @PostConstruct
        public void init(){
            System.out.println("SingletonBean.init");
        }
        @PreDestroy
        public void destroy(){
            System.out.println("SingletonBean.destroy");
        }
    }
}
