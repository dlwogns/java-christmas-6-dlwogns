package christmas.model;

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
    private final DecimalFormat decimalFormat = new DecimalFormat("#,###");

    public EventData(MenuChecker menuChecker, OrderDate orderDate, OrderAmount orderAmount) {
        for(Discount discount : Discount.values()){
            eventDiscount.put(discount, 0);
        }
        champagne = checkChampagne(orderAmount);
        calculateDiscount(orderDate,menuChecker);
        this.orderAmount = orderAmount.getOrderAmount();
    }
    private void calculateDiscount(OrderDate orderDate, MenuChecker menuChecker){
        calculatedDayDiscount(orderDate);
        calculateWeekDayDiscount(menuChecker, orderDate);
        calculateWeekEndDiscount(menuChecker, orderDate);
        calculateStarDayDiscount(orderDate);
        calculateTotalDiscount();
    }

    private void calculatedDayDiscount(OrderDate orderDate) {
        if (orderDate.getOrderDate() > 25) {
            return;
        }
        eventDiscount.replace(DDAYDISCOUNT, 1000 + 100 * (orderDate.getOrderDate() - 1));
    }

    private void calculateWeekDayDiscount(MenuChecker menuChecker, OrderDate orderDate) {
        if (orderDate.checkWeekEnd()) {
            return;
        }
        eventDiscount.replace(WEEKDAYDISCOUNT, menuChecker.getMenuChecker().get(MenuBoard.DESSERT) * 2023);
    }

    private void calculateWeekEndDiscount(MenuChecker menuChecker, OrderDate orderDate) {
        if (!orderDate.checkWeekEnd()) {
            return;
        }
        eventDiscount.replace(WEEKENDDISCOUNT, menuChecker.getMenuChecker().get(MenuBoard.MAIN_COURSE) * 2023);
    }

    private void calculateStarDayDiscount(OrderDate orderDate) {
        if (!orderDate.checkStarDay()) {
            return;
        }
        eventDiscount.replace(STARDISCOUNT, 1000);
    }

    private void calculateTotalDiscount() {
        Integer totalDiscount = eventDiscount.values()
                .stream().mapToInt(Integer::intValue).sum();
        eventDiscount.replace(TOTALDISCOUNT, totalDiscount);
        if (champagne) {
            eventDiscount.replace(TOTALDISCOUNT, totalDiscount+25000);
        }
    }

    private boolean checkChampagne(OrderAmount orderAmount) {
        return orderAmount.getOrderAmount() >= 120000;
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
        if (eventDiscount.get(DDAYDISCOUNT) != 0)
            discount += "크리스마스 디데이 할인: -" + decimalFormat.format(eventDiscount.get(DDAYDISCOUNT)) + '\n';
        if (eventDiscount.get(WEEKDAYDISCOUNT) != 0)
            discount += "평일 할인: -" + decimalFormat.format(eventDiscount.get(WEEKDAYDISCOUNT)) + '\n';
        if (eventDiscount.get(WEEKENDDISCOUNT) != 0)
            discount += "주말 할인: -" + decimalFormat.format(eventDiscount.get(WEEKENDDISCOUNT)) + '\n';
        if (champagne)
            discount += "증정 이벤트: -" + "25,000" + '\n';
        return discount;
    }

    private String toStringTotal() {
        String total = "<총혜택 금액>\n-";
        total += decimalFormat.format(eventDiscount.get(TOTALDISCOUNT))+ "원\n";
        total += "<할인 후 예상 결제 금액>\n";
        total += decimalFormat.format(orderAmount - eventDiscount.get(TOTALDISCOUNT)) + "원";
        return total;
    }
}
