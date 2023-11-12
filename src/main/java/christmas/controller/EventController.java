package christmas.controller;

import christmas.model.MenuChecker;
import christmas.model.OrderAmount;

public class EventController {
    private MenuChecker menuChecker;
    private OrderAmount orderAmount;

    public EventController(MenuChecker menuChecker, OrderAmount orderAmount) {
        this.menuChecker = menuChecker;
        this.orderAmount = orderAmount;
    }
    public boolean checkPromotionAble(){
        return orderAmount.getOrderAmount() >= 10000;
    }
    public boolean checkChampagne(){
        return orderAmount.getOrderAmount() >= 120000;
    }

}
