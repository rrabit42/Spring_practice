// RateDiscountPolicy.java에서 discount method에 ctrl+shift+t 를 누르면 테스트 class가 이렇게 알아서 만들어짐

package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*; // static import
import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPolicyTest {
    // RateDiscountPolicy가 정말 10% 할인이 되는지 확인
    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

    // 성공 테스트
    @Test
    @DisplayName("VIP는 10% 할인이 적용되어야 한다")
    void vip_o() {
        // given
        Member member = new Member(1L, "memberVIP", Grade.VIP);

        // when
        int discount = discountPolicy.discount(member, 10000);
        
        // then
        assertThat(discount).isEqualTo(1000); // static method가 더 나음(Assertions.assertThat()이 이렇게 바뀜, Alt+enter)
    }

    // 실패 테스트
    @Test
    @DisplayName("VIP가 아니면 할인이 적용되지 않아야 한다")
    void vip_x() {
        // given
        Member member = new Member(1L, "memberBASIC", Grade.BASIC);

        // when
        int discount = discountPolicy.discount(member, 10000);

        // then
        assertThat(discount).isEqualTo(0);
    }
}
