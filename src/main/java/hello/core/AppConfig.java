package hello.core;

import hello.core.discount.FixDiscountPolicy;
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
        return new MemberServiceImpl(new MemoryMemberRepository()); //이때 memroyMemberRepository가 여기서 들어감
    }

    public OrderService orderService() {
        return new OrderServiceImpl(new MemoryMemberRepository(),new FixDiscountPolicy());//생성자 주입
    }
}
