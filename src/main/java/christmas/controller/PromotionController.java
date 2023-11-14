package christmas.controller;

import static christmas.constant.Numbers.EVENT_CHECK_VALUE;

import christmas.model.EventBadge;
import christmas.model.EventData;
import christmas.model.Menu;
import christmas.model.MenuChecker;
import christmas.model.NotEventData;
import christmas.model.OrderAmount;
import christmas.model.OrderDate;
import christmas.view.InputView;
import christmas.view.OutputView;

public class PromotionController {
    private static final InputView inputView = InputView.getInstance();
    private static final OutputView outputView = OutputView.getInstance();
    private MenuChecker menuChecker;

    public void play() {
        outputView.printHelloMessage();
        OrderDate orderDate = getDate();
        Menu menu = getMenu();
        OrderAmount orderAmount = getOrderAmount(menu);
        EventData eventData = getEventData(orderAmount, menuChecker, orderDate);
        EventBadge eventBadge = getEventBadge(eventData.getTotalDiscount());
    }

    private OrderDate getDate() {
        try {
            OrderDate orderDate = new OrderDate(inputView.readDate());
            return orderDate;
        } catch (IllegalArgumentException e) {
            outputView.printException(e);
            return getDate();
        }
    }

    private Menu getMenu() {
        try {
            Menu menu = new Menu(inputView.readMenu());
            checkMenu(menu);
            outputView.printMenu(menu);
            return menu;
        } catch (IllegalArgumentException | IllegalStateException e) {
            outputView.printException(e);
            return getMenu();
        }
    }
    private void checkMenu(Menu menu){
        MenuChecker menuChecker =  new MenuChecker(menu);
        this.menuChecker = menuChecker;
    }

    private OrderAmount getOrderAmount(Menu menu) {
        OrderAmount orderAmount = new OrderAmount(menu);
        outputView.printOrderAmount(orderAmount);
        return orderAmount;
    }

    private EventData getEventData(OrderAmount orderAmount, MenuChecker menuChecker, OrderDate orderDate) {
        EventData eventData = null;
        if (orderAmount.getOrderAmount() >= EVENT_CHECK_VALUE.getValue()) {
            eventData = new EventData(menuChecker, orderDate, orderAmount);
        }
        if (orderAmount.getOrderAmount() < EVENT_CHECK_VALUE.getValue()) {
            eventData = new NotEventData(menuChecker, orderDate, orderAmount);
        }
        outputView.printEventData(eventData);
        return eventData;
    }

    private EventBadge getEventBadge(Integer totalDiscount) {
        EventBadge eventBadge = new EventBadge(totalDiscount);
        outputView.printEventBadge(eventBadge);
        return eventBadge;
    }


}
