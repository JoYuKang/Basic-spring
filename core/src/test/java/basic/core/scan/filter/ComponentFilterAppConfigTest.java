package basic.core.scan.filter;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.springframework.context.annotation.ComponentScan.*;

public class ComponentFilterAppConfigTest {

    @Test
    void filterScan(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);
        BeanA beanA = ac.getBean("beanA",BeanA.class);
        Assertions.assertThat(beanA).isNotNull();

    }

    @Configuration
    @ComponentScan(
            excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = MyExcludeComponet.class),
            includeFilters = @Filter(type = FilterType.ANNOTATION, classes = MyIncludeComponet.class))
    static class ComponentFilterAppConfig {

    }

}
