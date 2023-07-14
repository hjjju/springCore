package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

    //회원을 찾아야하니까
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    //discountPolicy
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    //잘된설계
    @Override /* 주문을 만들어서 반환해주기만 하면 orderService의 역할이 ㄱ끝남 */
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        return new Order(memberId,itemName, itemPrice,discountPrice);
    }
}
