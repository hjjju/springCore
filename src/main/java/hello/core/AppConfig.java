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

public class AppConfig {

    //구현객첵를 생성,연결
    //이전에는 객체를 생성하고 어떤 인터페이스가 들어가야하는지 할당하는것을 MemberService안에서 직접함
    //생성자주입
    public MemberService memberService() { //MemberSerivice를 부르면
//        return new MemberServiceImpl(new MemoryMemberRepository()); //이때 memroyMemberRepository가 여기서 들어감  //ctrl + alt + m
        return new MemberServiceImpl(memberRepository()); //이때 memroyMemberRepository가 여기서 들어감  //ctrl + alt + m
    }

    private MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());//생성자 주입
    }

    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy(); //shift + f10 마지막실행된게 실행
    }
}
