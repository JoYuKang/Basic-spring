package basic.core.autowired;

import basic.core.AutoAppConfig;
import basic.core.discount.DiscountPolicy;
import basic.core.member.Grade;
import basic.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

public class AllBeanTest  {

    @Test
    void findAllBean(){
        ApplicationContext  ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);

        DiscountService discountService = ac.getBean(DiscountService.class);

        Member member = new Member(1L, "지원", Grade.VIP);
        int price1 = discountService.discount(member, 10000, "fixDiscountPolicy");
        int price2 = discountService.discount(member, 20000, "rateDiscountPolicy");
        System.out.println("fixDiscountPolicy price = " + price1);
        System.out.println("rateDiscountPolicy price = " + price2);
    }

    // @RequiredArgsConstructor
    static class DiscountService{

        private final Map<String, DiscountPolicy> policyMap;
        private final List<DiscountPolicy> policies;

        public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policies) {
            this.policyMap = policyMap;
            this.policies = policies;
            System.out.println("policyMap = " + policyMap);
            System.out.println("policies = " + policies);
        }

        public int discount(Member member, int price, String rateDiscountPolicy) {
            DiscountPolicy discountPolicy = policyMap.get(rateDiscountPolicy);

            return discountPolicy.discount(member, price);
        }
    }
}
