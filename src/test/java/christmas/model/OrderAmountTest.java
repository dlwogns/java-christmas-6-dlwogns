package christmas.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class OrderAmountTest {
    @Test
    void 총주문금액_체크() {
        OrderAmount orderAmount = new OrderAmount(new Menu("티본스테이크-1,제로콜라-1,양송이스프-2"));
        assertThat(orderAmount.getOrderAmount()).isEqualTo(70000);
    }
}