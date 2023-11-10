package christmas.model;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class OrderDateTest {
    @Test
    void 주문날짜_숫자_확인(){
        assertThatThrownBy(()-> new OrderDate("asdf"))
                .isInstanceOf(IllegalArgumentException.class);
    }
}