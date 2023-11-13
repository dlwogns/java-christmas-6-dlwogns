package christmas.model;

import java.util.regex.Pattern;

public class OrderDate {
    private final Integer orderDate;
    private final String REGEX_DIGIT = "^[\\d]*$";

    public OrderDate(String orderDate) {
        validate(orderDate);
        this.orderDate = Integer.parseInt(orderDate);
    }

    private void validate(String orderDate) throws IllegalArgumentException {
        validateOrderdateIsNumber(orderDate);
        validateOrderdateRange(orderDate);
    }

    private void validateOrderdateIsNumber(String orderDate) {
        if (!Pattern.matches(REGEX_DIGIT, orderDate)) {
            throw new IllegalArgumentException();
        }
    }

    private void validateOrderdateRange(String orderDate) {
        int date = Integer.parseInt(orderDate);
        if (date < 1 || date > 31) {
            throw new IllegalArgumentException();
        }
    }

    public boolean checkWeekEnd() {
        if (orderDate % 7 == 1 || orderDate % 7 == 2) {
            return true;
        }
        return false;
    }

    public boolean checkStarDay() {
        if (orderDate % 7 == 3 || orderDate == 25) {
            return true;
        }
        return false;
    }

    public Integer getOrderDate() {
        return orderDate;
    }
}
