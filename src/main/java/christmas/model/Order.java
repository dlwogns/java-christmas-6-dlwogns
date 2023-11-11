package christmas.model;

import java.util.Map;

public class Order {
    private final Map<OrderDate, Menu> order;
    public Order(Map<OrderDate, Menu> order) {
        this.order = order;
    }
}
