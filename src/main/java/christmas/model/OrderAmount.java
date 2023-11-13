package christmas.model;

import static christmas.constant.Format.DECIMALFORMAT;
import static christmas.constant.PrintStrings.MONEYUNIT;

import java.text.DecimalFormat;

public class OrderAmount {
    private final Integer orderAmount;

    public OrderAmount(Menu menu) {
        orderAmount = calculateOrderAmount(menu);
    }

    private Integer calculateOrderAmount(Menu menu) {
        Integer orderAmount = 0;
        for (String menuName : menu.getMenu().keySet()) {
            orderAmount += checkMenuBoard(menuName) * menu.getMenu().get(menuName);
        }
        return orderAmount;
    }

    private Integer checkMenuBoard(String menuName) {
        Integer menuPrice = 0;
        for (MenuBoard menuBoard : MenuBoard.values()) {
            if (menuBoard.getValue().containsKey(menuName)) {
                menuPrice = menuBoard.getValue().get(menuName);
            }
        }
        return menuPrice;
    }

    public Integer getOrderAmount() { // for test
        return orderAmount;
    }

    @Override
    public String toString() {
        DecimalFormat decimalFormat = new DecimalFormat(DECIMALFORMAT.getValue());
        return decimalFormat.format(orderAmount) + MONEYUNIT.getValue();
    }
}
