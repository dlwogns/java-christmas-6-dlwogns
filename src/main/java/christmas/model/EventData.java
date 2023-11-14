package christmas.model;

import static christmas.constant.Format.CRLF;
import static christmas.constant.Format.DECIMAL_FORMAT;
import static christmas.constant.Numbers.CHAMPAGNE_PRICE;
import static christmas.constant.Numbers.CHECK_CHAMPAGNE;
import static christmas.constant.Numbers.CHRISTMAS;
import static christmas.constant.Numbers.DISCOUNT_PER_DAY;
import static christmas.constant.Numbers.DISCOUNT_PER_MENU;
import static christmas.constant.Numbers.INITIAL_DDAY_DISCOUNT;
import static christmas.constant.Numbers.INITIAL_DISCOUNT;
import static christmas.constant.Numbers.STARDAY_DISCOUNT;
import static christmas.constant.Strings.MONEY_UNIT;
import static christmas.model.Discount.DDAYDISCOUNT;
import static christmas.model.Discount.STARDISCOUNT;
import static christmas.model.Discount.TOTALDISCOUNT;
import static christmas.model.Discount.WEEKDAYDISCOUNT;
import static christmas.model.Discount.WEEKENDDISCOUNT;

import java.text.DecimalFormat;
import java.util.EnumMap;
import java.util.Objects;

public class EventData {
    private final EnumMap<Discount, Integer> eventDiscount = new EnumMap<>(Discount.class);
    private final boolean champagne;
    protected final Integer orderAmount;
    private final DecimalFormat decimalFormat = new DecimalFormat(DECIMAL_FORMAT.getValue());

    public EventData(MenuChecker menuChecker, OrderDate orderDate, OrderAmount orderAmount) {
        for (Discount discount : Discount.values()) {
            eventDiscount.put(discount, INITIAL_DISCOUNT.getValue());
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
                INITIAL_DDAY_DISCOUNT.getValue() + DISCOUNT_PER_DAY.getValue() * (orderDate.getOrderDate() - 1));
    }

    private void calculateWeekDayDiscount(MenuChecker menuChecker, OrderDate orderDate) {
        if (orderDate.checkWeekEnd()) {
            return;
        }
        eventDiscount.replace(WEEKDAYDISCOUNT,
                menuChecker.getMenuChecker().get(MenuBoard.DESSERT) * DISCOUNT_PER_MENU.getValue());
    }

    private void calculateWeekEndDiscount(MenuChecker menuChecker, OrderDate orderDate) {
        if (!orderDate.checkWeekEnd()) {
            return;
        }
        eventDiscount.replace(WEEKENDDISCOUNT,
                menuChecker.getMenuChecker().get(MenuBoard.MAIN_COURSE) * DISCOUNT_PER_MENU.getValue());
    }

    private void calculateStarDayDiscount(OrderDate orderDate) {
        if (!orderDate.checkStarDay()) {
            return;
        }
        eventDiscount.replace(STARDISCOUNT, STARDAY_DISCOUNT.getValue());
    }

    private void calculateTotalDiscount() {
        Integer totalDiscount = eventDiscount.values()
                .stream().mapToInt(Integer::intValue).sum();
        eventDiscount.replace(TOTALDISCOUNT, totalDiscount);
        if (champagne) {
            eventDiscount.replace(TOTALDISCOUNT, totalDiscount + CHAMPAGNE_PRICE.getValue());
        }
    }

    private boolean checkChampagne(OrderAmount orderAmount) {
        return orderAmount.getOrderAmount() >= CHECK_CHAMPAGNE.getValue();
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
        discount = toStringDdayDiscount(discount);
        discount = toStringWeekDayDiscount(discount);
        discount = toStringWeekEndDiscount(discount);
        discount = toStringStarDiscount(discount);
        discount = toStringChampagne(discount);
        return discount;
    }

    private String toStringChampagne(String discount) {
        if (champagne) {
            discount += "증정 이벤트: -" + decimalFormat.format(CHAMPAGNE_PRICE.getValue()) + CRLF.getValue();
        }
        return discount;
    }

    private String toStringStarDiscount(String discount) {
        if (!Objects.equals(eventDiscount.get(STARDISCOUNT), INITIAL_DISCOUNT.getValue())) {
            discount += "특별 할인: -" + decimalFormat.format(eventDiscount.get(STARDISCOUNT)) + CRLF.getValue();
        }
        return discount;
    }

    private String toStringWeekEndDiscount(String discount) {
        if (!Objects.equals(eventDiscount.get(WEEKENDDISCOUNT), INITIAL_DISCOUNT.getValue())) {
            discount += "주말 할인: -" + decimalFormat.format(eventDiscount.get(WEEKENDDISCOUNT)) + CRLF.getValue();
        }
        return discount;
    }

    private String toStringWeekDayDiscount(String discount) {
        if (!Objects.equals(eventDiscount.get(WEEKDAYDISCOUNT), INITIAL_DISCOUNT.getValue())) {
            discount += "평일 할인: -" + decimalFormat.format(eventDiscount.get(WEEKDAYDISCOUNT)) + CRLF.getValue();
        }
        return discount;
    }

    private String toStringDdayDiscount(String discount) {
        if (!Objects.equals(eventDiscount.get(DDAYDISCOUNT), INITIAL_DISCOUNT.getValue())) {
            discount += "크리스마스 디데이 할인: -" + decimalFormat.format(eventDiscount.get(DDAYDISCOUNT)) + CRLF.getValue();
        }
        return discount;
    }

    private String toStringTotal() {
        String total = "<총혜택 금액>\n-";
        total += decimalFormat.format(eventDiscount.get(TOTALDISCOUNT)) + MONEY_UNIT.getValue() + CRLF.getValue();
        total += "<할인 후 예상 결제 금액>\n";
        total += decimalFormat.format(orderAmount - eventDiscount.get(TOTALDISCOUNT)) + MONEY_UNIT.getValue();
        return total;
    }
}
