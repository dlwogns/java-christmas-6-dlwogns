package christmas.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class MenuTest {
    @Test
    void 매뉴_형식_확인() {
        assertThatThrownBy(() -> new Menu("봉골레파스타1,피자-2"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 메뉴개수_1_이상인지_확인() {
        assertThatThrownBy(() -> new Menu("파스타-0"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 중복메뉴_입력_확인() {
        assertThatThrownBy(() -> new Menu("파스타-1,파스타-2"))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void 메뉴개수_20개이상_체크() {
        assertThatThrownBy(() -> new Menu("파스타-10,피자-20,햄버거-30"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 메뉴판에_없는_메뉴_체크() {
        assertThatThrownBy(() -> new MenuChecker().checkMenu(new Menu("피자-1").getMenu()))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 음료만_주문_체크() {
        assertThatThrownBy(() -> {
            MenuChecker menuChecker = new MenuChecker();
            menuChecker.checkMenu(new Menu("레드와인-1").getMenu());
            menuChecker.checkOnlyBeverage();
        }).isInstanceOf(IllegalArgumentException.class);
    }


}