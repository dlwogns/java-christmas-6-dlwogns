package christmas.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class EventDataTest {
    @Test
    void 이벤트_할인_정보_확인(){
        OrderDate orderDate = new OrderDate("3");
        Menu menu = new Menu("해산물파스타-2,레드와인-1");
        MenuChecker menuChecker = new MenuChecker();
        menuChecker.checkMenu(menu.getMenu());
        OrderAmount orderAmount = new OrderAmount(menu);
        EventData eventData = new EventData(menuChecker,orderDate,orderAmount);
        assertThat(eventData.toString()).contains(
                "<증정 메뉴>",
                "샴페인 1개",
                "크리스마스 디데이 할인: -1,200",
                "특별 할인: -1,000",
                "증정 이벤트: -25,000"
        );

    }

}