package christmas.model;

public class Discount {
    private Integer dDayDiscount;
    private Integer weekDayDiscount;
    private Integer weekEndDiscount;
    private Integer starDiscount;
    public Discount(MenuChecker menuChecker, OrderDate orderDate){
        dDayDiscount = calculatedDayDiscount(orderDate);
        weekDayDiscount = calculateWeekDayDiscount(menuChecker,orderDate);
        weekEndDiscount = calculateWeekEndDiscount(menuChecker,orderDate);
        starDiscount = calculateStarDayDiscount(orderDate);
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
}
