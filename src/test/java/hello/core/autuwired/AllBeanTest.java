package hello.core.autuwired;

import hello.core.AutoAppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class AllBeanTest {

    @Test
    void findAllBean() {
        //스프링 컨테이너생성, 빈으로 등록
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);

        DiscountService discountService = ac.getBean(DiscountService.class);
        Member member = new Member(1L, "userA", Grade.VIP);
        int discountPrice = discountService.discount(member, 10000, "fixDiscountPolicy");

        assertThat(discountService).isInstanceOf(DiscountService.class);

        assertThat(discountPrice).isEqualTo(1000);


        int rateDiscountPrice= discountService.discount(member, 20000, "rateDiscountPolicy");
        assertThat(rateDiscountPrice).isEqualTo(2000);

    }


    static class DiscountService{

        //DiscountPolicy 타입의 객체가 value로 들어감
        private final Map<String, DiscountPolicy> policyMap;
        private final List<DiscountPolicy> policies;


        //constructor 주입
        //@Autowired가 붙은 메서드의 파라미터로 Map<String, BeanType>이 온다면
        // 스프링은 BeanType에 해당하는 빈들을 찾아서 Map으로 의존관계를 주입해줍니다.
        // 이는 스프링에서 제공하는 기능 중 하나
        
        //List는 값이 인스턴스로 들어옴
        @Autowired
        public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policies) {
            this.policyMap = policyMap;
            this.policies = policies;

            System.out.println("policyMap = " + policyMap);
            System.out.println("policies = " + policies);
        }


        //할인 코드를 빈이름이랑 매칭
        public int discount(Member member, int price, String discountCode) {
            DiscountPolicy discountPolicy = policyMap.get(discountCode);

            return discountPolicy.discount(member, price);
        }
    }

}

