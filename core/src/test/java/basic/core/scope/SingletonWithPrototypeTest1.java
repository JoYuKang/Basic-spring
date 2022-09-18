package basic.core.scope;

import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;

public class SingletonWithPrototypeTest1 {

    @Test
    void prototypeFind(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ProtoTypeBean.class);
        ProtoTypeBean bean1 = ac.getBean(ProtoTypeBean.class);
        ProtoTypeBean bean2 = ac.getBean(ProtoTypeBean.class);
        bean1.addCount();
        System.out.println("bean1 = " + bean1.count);
        bean2.addCount();
        System.out.println("bean2 = " + bean2.count);

    }

    @Test
    void singletonClientUsePrototype(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class, ProtoTypeBean.class);
        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 = clientBean1.logic();
        System.out.println("count1 = " + count1);

        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean1.logic();
        System.out.println("count2 = " + count2);
        Assertions.assertThat(count1).isSameAs(count2);
    }

    @Scope("singleton")
    // @RequiredArgsConstructor
    static class ClientBean{
        //private final ProtoTypeBean protoTypeBean; // 생성시점에 의존관계 주

//        @Autowired
//        private ObjectProvider<ProtoTypeBean> protoTypeBeanProvider;
        @Autowired
        private Provider<ProtoTypeBean> protoTypeBeanProvider;

//        @Autowired
//        public ClientBean(ProtoTypeBean protoTypeBean) {
//            this.protoTypeBean = protoTypeBean;
//        }
        public int logic(){
            ProtoTypeBean protoTypeBean = protoTypeBeanProvider.get();
            protoTypeBean.addCount();
            return protoTypeBean.getCount();
        }
    }

    @Scope("prototype")
    static class ProtoTypeBean{
        private int count = 0;

        public int getCount() {
            return count;
        }

        public void addCount() {
            count++;
        }
        @PostConstruct
        public void init(){
            System.out.println("ProtoTypeBean.init" + this);
        }
        @PreDestroy
        public void destroy(){
            System.out.println("ProtoTypeBean.destroy");
        }

    }

}
