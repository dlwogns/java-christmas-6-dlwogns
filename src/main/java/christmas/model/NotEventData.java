package christmas.model;

public class NotEventData extends EventData{
    public NotEventData(MenuChecker menuChecker, OrderDate orderDate, OrderAmount orderAmount) {
        super(menuChecker, orderDate, orderAmount);
    }
    @Override
    public String toString() {
        return "<증정 메뉴>\n없음\n<혜택 내역>\n없음\n";
    }
}
