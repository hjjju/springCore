package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {


    //@Bean -> memberService -> new MemoryMemberRepository()
    //@Bean -> orderService -> new MemoryMemberRepository()

    //call AppConfig.memberService
    //call AppConfig.memberRepository //1
    //call AppConfig.memberRepository //2
    //call AppConfig.orderService
    //call AppConfig.memberRepository //3

    //call AppConfig.memberService
    //call AppConfig.memberRepository
    //call AppConfig.orderService


    //factory method를 이용해 등록하는 방법

    //구현객첵를 생성,연결
    //이전에는 객체를 생성하고 어떤 인터페이스가 들어가야하는지 할당하는것을 MemberService안에서 직접함
    //생성자주입
    @Bean //@Bean을 적어두면 스프링컨테이너에 등록됨
    public MemberService memberService() { //MemberSerivice를 부르면  ,key
//        return new MemberServiceImpl(new MemoryMemberRepository()); //이때 memroyMemberRepository가 여기서 들어감  //ctrl + alt + m
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository()); //이때 memroyMemberRepository가 여기서 들어감  //ctrl + alt + m  //value
    }

    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());//생성자 주입
//        return null;
    }


    @Bean
    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy(); //shift + f10 마지막실행된게 실행
    }
}
