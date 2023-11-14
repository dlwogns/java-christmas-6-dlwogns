package christmas.model;

import static christmas.constant.Badge.NONE;
import static christmas.constant.Badge.SANTA;
import static christmas.constant.Badge.STAR;
import static christmas.constant.Badge.TREE;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class EventBadgeTest {
    @Test
    void 배지_조건_미충족_체크() {
        assertThat(new EventBadge(1000).toString())
                .isEqualTo(NONE.getValue());
    }

    @Test
    void 별_배지_체크() {
        assertThat(new EventBadge(5000).toString())
                .isEqualTo(STAR.getValue());
    }

    @Test
    void 나무_배지_체크() {
        assertThat(new EventBadge(10000).toString())
                .isEqualTo(TREE.getValue());
    }

    @Test
    void 산타_배지_체크() {
        assertThat(new EventBadge(20000).toString())
                .isEqualTo(SANTA.getValue());
    }
}