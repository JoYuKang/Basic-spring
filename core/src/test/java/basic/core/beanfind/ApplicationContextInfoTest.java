package basic.core.beanfind;

import basic.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextInfoTest  {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("Bean 출력")
    void findBeanAll(){
        String[] names = ac.getBeanDefinitionNames();
        for (String name : names) {
            Object bean = ac.getBean(name);
            System.out.println("name = " + name + " Object = " +  bean);
        }
    }
     @Test
    @DisplayName("애플리케이션 빈 출력")
    void findBeanApplication (){
        String[] names = ac.getBeanDefinitionNames();
        for (String name : names) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(name);
            // 내가 직접 등록한 빈일 경우 ROLE_APPLICATION
            // 스프링 내부에서 등록된 빈 ROLE_INFRASTRUCTURE
            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION){
                Object bean = ac.getBean(name);
                System.out.println("name = " + name + " Object = " +  bean);
            }

        }
    }

}
