package christmas.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class OrderDateTest {
    @Test
    void 주문날짜_숫자_확인() {
        assertThatThrownBy(() -> new OrderDate("asdf"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 주문날짜_범위_확인_큰수() {
        assertThatThrownBy(() -> new OrderDate("50"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 주문날짜_범위_확인_작은수() {
        assertThatThrownBy(() -> new OrderDate("-10"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 주말_체크() {
        assertThat(new OrderDate("8").checkWeekEnd()).isEqualTo(true);
    }

    @Test
    void 평일_체크() {
        assertThat(new OrderDate("20").checkWeekEnd()).isEqualTo(false);
    }

    @Test
    void 특별할인_체크() {
        assertThat(new OrderDate("24").checkStarDay()).isEqualTo(true);
    }
}