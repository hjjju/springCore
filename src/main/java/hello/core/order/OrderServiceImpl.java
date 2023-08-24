package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor //final붙은 필수값을 가지고 생성자 만들어줌
public class OrderServiceImpl implements OrderService{

    //회원을 찾아야하니까
//    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberRepository memberRepository; //여기에 값이 할당됨 ,인터페이스에만 의존,생성자에서만 값을 넣을수 있음
//    @Autowired private  MemberRepository memberRepository; //여기에 값이 할당됨 ,인터페이스에만 의존
//    private  MemberRepository memberRepository; //여기에 값이 할당됨 ,인터페이스에만 의존

//    @Autowired
//    private DiscountPolicy rateDiscountPolicy;
    //discountPolicy
    private final DiscountPolicy discountPolicy;

    //@Autowired 필드주입
//    @Autowired private  DiscountPolicy discountPolicy;
//    private  DiscountPolicy discountPolicy;

//    @Autowired(required = false) //선택적 의존관계 주입
//    public void setMemberRepository(MemberRepository memberRepository) {
//        System.out.println("memberRepository = " + memberRepository);
//        this.memberRepository = memberRepository;
//    }
//    @Autowired
//    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
//        System.out.println("discountPolicy = " + discountPolicy);
//        this.discountPolicy = discountPolicy;
//    }

    /**생성자 주입
     *생성자 호출시점에 딱 1번만 호출되는 것이 보장된다.
     * 불변, 필수 의존관계에 사용
     * 생성자가 1개만있으면 @Autuwired를 생략해도 자동 주입된다.(물론 스프링 빈에만 해당)
     * @param memberRepository
     * @param discountPolicy
     */




    //new OrderServiceImpl(memberRepository,discountPolicy);
    @Autowired //1번  ///스프링 컨테이너에서 스프링빈을 꺼내 주입해줌
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
//    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
//        System.out.println("memberRepository = " + memberRepository);
//        System.out.println("discountPolicy = " + discountPolicy);
        //soutm
//        System.out.println("1. OrderServiceImpl.OrderServiceImpl");
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }



    //잘된설계
    @Override /* 주문을 만들어서 반환해주기만 하면 orderService의 역할이 ㄱ끝남 */
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        return new Order(memberId,itemName, itemPrice,discountPrice);
    }

    //테스트용도 //2번
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
