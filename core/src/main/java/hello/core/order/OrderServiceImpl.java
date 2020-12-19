package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderSerivce{

    private final MemberRepository memberRepository = new MemoryMemberRepository();
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy(); // --> DCP, OCP 위반 --> 인터페이스에만 의존하도록 의존관계 변경
    private DiscountPolicy discountPolicy; // 인터페이스에만 의존하도록 해결 but 구현체가 없음!

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId); // 회원 정보 조회
        int discountPrice = discountPolicy.discount(member, itemPrice); // 할인 정책 적용(grade만 넘겨도 되지만 그냥 멤버 자체 넘김)

        return new Order(memberId, itemName, itemPrice, discountPrice); // 최종 생성된 주문
    }
}
