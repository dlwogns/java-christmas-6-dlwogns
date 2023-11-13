package christmas.model;

import static christmas.constant.Format.CRLF;
import static christmas.constant.Format.DECIMALFORMAT;
import static christmas.constant.Numbers.CHAMPAGNEPRICE;
import static christmas.constant.Numbers.CHECKCHAMPAGNE;
import static christmas.constant.Numbers.CHRISTMAS;
import static christmas.constant.Numbers.DISCOUNTPERDAY;
import static christmas.constant.Numbers.DISCOUNTPERMENU;
import static christmas.constant.Numbers.INITIALDDAYDISCOUNT;
import static christmas.constant.Numbers.INITIALDISCOUNT;
import static christmas.constant.Numbers.STARDAYDISCOUNT;
import static christmas.constant.Strings.MONEYUNIT;
import static christmas.model.Discount.DDAYDISCOUNT;
import static christmas.model.Discount.STARDISCOUNT;
import static christmas.model.Discount.TOTALDISCOUNT;
import static christmas.model.Discount.WEEKDAYDISCOUNT;
import static christmas.model.Discount.WEEKENDDISCOUNT;

import java.text.DecimalFormat;
import java.util.EnumMap;

public class EventData {
    private final EnumMap<Discount, Integer> eventDiscount = new EnumMap<>(Discount.class);
    private final boolean champagne;
    protected final Integer orderAmount;
    private final DecimalFormat decimalFormat = new DecimalFormat(DECIMALFORMAT.getValue());

    public EventData(MenuChecker menuChecker, OrderDate orderDate, OrderAmount orderAmount) {
        for (Discount discount : Discount.values()) {
            eventDiscount.put(discount, INITIALDISCOUNT.getValue());
        }
        champagne = checkChampagne(orderAmount);
        calculateDiscount(orderDate, menuChecker);
        this.orderAmount = orderAmount.getOrderAmount();
    }

    private void calculateDiscount(OrderDate orderDate, MenuChecker menuChecker) {
        calculatedDayDiscount(orderDate);
        calculateWeekDayDiscount(menuChecker, orderDate);
        calculateWeekEndDiscount(menuChecker, orderDate);
        calculateStarDayDiscount(orderDate);
        calculateTotalDiscount();
    }

    private void calculatedDayDiscount(OrderDate orderDate) {
        if (orderDate.getOrderDate() > CHRISTMAS.getValue()) {
            return;
        }
        eventDiscount.replace(DDAYDISCOUNT,
                INITIALDDAYDISCOUNT.getValue() + DISCOUNTPERDAY.getValue() * (orderDate.getOrderDate() - 1));
    }

    private void calculateWeekDayDiscount(MenuChecker menuChecker, OrderDate orderDate) {
        if (orderDate.checkWeekEnd()) {
            return;
        }
        eventDiscount.replace(WEEKDAYDISCOUNT,
                menuChecker.getMenuChecker().get(MenuBoard.DESSERT) * DISCOUNTPERMENU.getValue());
    }

    private void calculateWeekEndDiscount(MenuChecker menuChecker, OrderDate orderDate) {
        if (!orderDate.checkWeekEnd()) {
            return;
        }
        eventDiscount.replace(WEEKENDDISCOUNT,
                menuChecker.getMenuChecker().get(MenuBoard.MAIN_COURSE) * DISCOUNTPERMENU.getValue());
    }

    private void calculateStarDayDiscount(OrderDate orderDate) {
        if (!orderDate.checkStarDay()) {
            return;
        }
        eventDiscount.replace(STARDISCOUNT, STARDAYDISCOUNT.getValue());
    }

    private void calculateTotalDiscount() {
        Integer totalDiscount = eventDiscount.values()
                .stream().mapToInt(Integer::intValue).sum();
        eventDiscount.replace(TOTALDISCOUNT, totalDiscount);
        if (champagne) {
            eventDiscount.replace(TOTALDISCOUNT, totalDiscount + CHAMPAGNEPRICE.getValue());
        }
    }

    private boolean checkChampagne(OrderAmount orderAmount) {
        return orderAmount.getOrderAmount() >= CHECKCHAMPAGNE.getValue();
    }

    public Integer getTotalDiscount() {
        return eventDiscount.get(TOTALDISCOUNT);
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
        if (eventDiscount.get(DDAYDISCOUNT) != INITIALDISCOUNT.getValue()) {
            discount += "크리스마스 디데이 할인: -" + decimalFormat.format(eventDiscount.get(DDAYDISCOUNT)) + CRLF.getValue();
        }
        if (eventDiscount.get(WEEKDAYDISCOUNT) != INITIALDISCOUNT.getValue()) {
            discount += "평일 할인: -" + decimalFormat.format(eventDiscount.get(WEEKDAYDISCOUNT)) + CRLF.getValue();
        }
        if (eventDiscount.get(WEEKENDDISCOUNT) != INITIALDISCOUNT.getValue()) {
            discount += "주말 할인: -" + decimalFormat.format(eventDiscount.get(WEEKENDDISCOUNT)) + CRLF.getValue();
        }
        if (champagne) {
            discount += "증정 이벤트: -" + CHAMPAGNEPRICE.getValue() + CRLF.getValue();
        }
        return discount;
    }

    private String toStringTotal() {
        String total = "<총혜택 금액>\n-";
        total += decimalFormat.format(eventDiscount.get(TOTALDISCOUNT)) + MONEYUNIT.getValue() + CRLF.getValue();
        total += "<할인 후 예상 결제 금액>\n";
        total += decimalFormat.format(orderAmount - eventDiscount.get(TOTALDISCOUNT)) + MONEYUNIT.getValue();
        return total;
    }
}
