package basic.core.order;

import basic.core.discount.DiscountPolicy;
import basic.core.discount.FixDiscountPolicy;
import basic.core.member.Member;
import basic.core.member.MemberRepository;
import basic.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository= new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();



    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {

        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
