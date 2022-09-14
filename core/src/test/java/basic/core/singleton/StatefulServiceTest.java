package basic.core.singleton;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton () {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2  = ac.getBean(StatefulService.class);

        // ThreadA 주문
        statefulService1.order("지원", 10000);
        // ThreadB 주문
        statefulService2.order("민기", 20000);

        /*
            주소값이 같기 때문에 같은 값이 수정되어 기대값과 다르게 나온다.
            스프링 빈은 무상태로 설계해야한다.
         */
        int price = statefulService1.getPrice();
        System.out.println("price = " + price);

    }

    static class TestConfig{
        @Bean
        public StatefulService statefulService(){
             return new StatefulService();
        }
    }


}