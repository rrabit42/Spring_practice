package hello.core.order;

import hello.core.AppConfig;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderSerivceTest {

    MemberService memberService;
    OrderSerivce orderSerivce;

    // 각 test 실행하기 전에 무조건 실행 되는 것
    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderSerivce = appConfig.orderService();
    }

    @Test
    void createOrder() {
        Long memberId = 1L; // primitive type인 long 써도되는데, 그럼 null 값을 넣을 수 없음
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderSerivce.createOrder(memberId, "itemA", 10000);

        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}
