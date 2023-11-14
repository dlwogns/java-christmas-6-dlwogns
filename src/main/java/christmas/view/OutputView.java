package christmas.view;

import christmas.model.EventBadge;
import christmas.model.EventData;
import christmas.model.Menu;
import christmas.model.OrderAmount;

public class OutputView {
    private static OutputView instance;

    public static OutputView getInstance() {
        if (instance == null) {
            return instance = new OutputView();
        }
        return instance;
    }

    public void printHelloMessage() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    public void printMenu(Menu menu) {
        System.out.println("12월 26일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
        System.out.println("<주문 메뉴>");
        System.out.println(menu.toString());
    }

    public void printOrderAmount(OrderAmount orderAmount) {
        System.out.println("<할인 전 총주문 금액>");
        System.out.println(orderAmount.toString());
    }

    public void printEventData(EventData eventData) {
        System.out.println(eventData.toString());
    }

    public void printEventBadge(EventBadge eventBadge) {
        System.out.println("<12월 이벤트 배지>");
        System.out.println(eventBadge.toString());

    }
}
