package christmas.model;

public class NotEventData extends EventData{
    public NotEventData(MenuChecker menuChecker, OrderDate orderDate, OrderAmount orderAmount) {
        super(menuChecker, orderDate, orderAmount);
    }
    @Override
    public String toString() {
        return "<증정 메뉴>\n없음\n<혜택 내역>\n없음\n<총혜택 금액>\n0원\n<할인 후 예상 결제 금액>\n"+ super.orderAmount.toString() +"원";
    }
}
