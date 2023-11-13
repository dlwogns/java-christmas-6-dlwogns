package christmas.model;

import java.text.DecimalFormat;

public class EventData {
    private final Integer dDayDiscount;
    private final Integer weekDayDiscount;
    private final Integer weekEndDiscount;
    private final Integer starDiscount;
    private final Integer totalDiscount;
    private final boolean champagne;
    protected final Integer orderAmount;
    private final DecimalFormat decimalFormat = new DecimalFormat("#,###");

    public EventData(MenuChecker menuChecker, OrderDate orderDate, OrderAmount orderAmount) {
        dDayDiscount = calculatedDayDiscount(orderDate);
        weekDayDiscount = calculateWeekDayDiscount(menuChecker, orderDate);
        weekEndDiscount = calculateWeekEndDiscount(menuChecker, orderDate);
        starDiscount = calculateStarDayDiscount(orderDate);
        champagne = checkChampagne(orderAmount);
        totalDiscount = calculateTotalDiscount();
        this.orderAmount = orderAmount.getOrderAmount();
    }

    private Integer calculatedDayDiscount(OrderDate orderDate) {
        if (orderDate.getOrderDate() > 25) {
            return 0;
        }
        return 1000 + 100 * (orderDate.getOrderDate() - 1);
    }

    private Integer calculateWeekDayDiscount(MenuChecker menuChecker, OrderDate orderDate) {
        if (orderDate.checkWeekEnd()) {
            return 0;
        }
        return menuChecker.getMenuChecker().get(MenuBoard.DESSERT) * 2023;
    }

    private Integer calculateWeekEndDiscount(MenuChecker menuChecker, OrderDate orderDate) {
        if (!orderDate.checkWeekEnd()) {
            return 0;
        }
        return menuChecker.getMenuChecker().get(MenuBoard.MAIN_COURSE) * 2023;
    }

    private Integer calculateStarDayDiscount(OrderDate orderDate) {
        if (!orderDate.checkStarDay()) {
            return 0;
        }
        return 1000;
    }

    private Integer calculateTotalDiscount() {
        if (champagne) {
            return dDayDiscount + weekDayDiscount + weekEndDiscount + starDiscount + 25000;
        }
        return dDayDiscount + weekDayDiscount + weekEndDiscount + starDiscount;
    }

    private boolean checkChampagne(OrderAmount orderAmount) {
        return orderAmount.getOrderAmount() >= 120000;
    }

    public Integer getTotalDiscount() {
        return totalDiscount;
    }

    @Override
    public String toString() {
        String discount = toStringifChampagne();
        discount += toStringPromotion();
        discount += toStringTotal();
        return discount;
    }

    private String toStringifChampagne() {
        if (champagne) {
            return "<증정 메뉴>\n샴페인 1개\n";
        }
        return "<증정 메뉴>\n없음\n";
    }

    private String toStringPromotion() {
        String discount = "<혜택 내역>\n";
        if (dDayDiscount != 0) {
            discount += "크리스마스 디데이 할인: -" + decimalFormat.format(dDayDiscount) + '\n';
        }
        if (weekDayDiscount != 0) {
            discount += "평일 할인: -" + decimalFormat.format(weekDayDiscount) + '\n';
        }
        if (weekEndDiscount != 0) {
            discount += "주말 할인: -" + decimalFormat.format(weekEndDiscount) + '\n';
        }
        if (champagne) {
            discount += "증정 이벤트: -" + "25,000" + '\n';
        }
        return discount;
    }

    private String toStringTotal() {
        String total = "<총혜택 금액>\n-";
        total += decimalFormat.format(totalDiscount)+ "원\n";
        total += "<할인 후 예상 결제 금액>\n";
        total += decimalFormat.format(orderAmount - totalDiscount) + "원";
        return total;
    }
}
