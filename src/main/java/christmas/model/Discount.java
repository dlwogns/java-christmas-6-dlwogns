package christmas.model;

public class Discount {
    private Integer dDayDiscount;
    private Integer weekDayDiscount;
    private Integer weekEndDiscount;
    private Integer starDiscount;
    private Integer totalDiscount;
    public Discount(MenuChecker menuChecker, OrderDate orderDate){
        dDayDiscount = calculatedDayDiscount(orderDate);
        weekDayDiscount = calculateWeekDayDiscount(menuChecker,orderDate);
        weekEndDiscount = calculateWeekEndDiscount(menuChecker,orderDate);
        starDiscount = calculateStarDayDiscount(orderDate);
        totalDiscount = calculateTotalDiscount();
    }
    private Integer calculatedDayDiscount(OrderDate orderDate){
        if(orderDate.getOrderDate() > 25)
            return 0;
        return 1000 + 100 * (orderDate.getOrderDate() - 1);
    }
    private Integer calculateWeekDayDiscount(MenuChecker menuChecker, OrderDate orderDate){
        if(orderDate.checkWeekEnd() == true)
            return 0;
        return menuChecker.getMenuChecker().get(MenuBoard.DESSERT) * 2023;
    }
    private Integer calculateWeekEndDiscount(MenuChecker menuChecker, OrderDate orderDate){
        if(orderDate.checkWeekEnd() == false)
            return 0;
        return menuChecker.getMenuChecker().get(MenuBoard.MAIN_COURSE) * 2023;
    }
    private Integer calculateStarDayDiscount(OrderDate orderDate){
        if(orderDate.checkStarDay() == false)
            return 0;
        return 1000;
    }
    private Integer calculateTotalDiscount(){
        return dDayDiscount+weekDayDiscount+weekEndDiscount+starDiscount;
    }

    @Override
    public String toString() {
        String discount = "<혜택 내역>\n";
        if(dDayDiscount != 0)
            discount += "크리스마스 디데이 할인: -" + dDayDiscount + '\n';
        if(weekDayDiscount != 0)
            discount += "평일 할인: -" + weekDayDiscount + '\n';
        if(weekEndDiscount != 0)
            discount += "주말 할인: -" + weekEndDiscount +'\n';

        return discount;
    }
}
