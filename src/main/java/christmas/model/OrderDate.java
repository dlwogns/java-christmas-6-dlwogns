package christmas.model;

import java.util.regex.Pattern;

public class OrderDate {
    private final Integer orderDate;
    private final String REGEX_DIGIT = "^[\\d]*$";

    public OrderDate(String orderDate) {
        validate(orderDate);
        this.orderDate = Integer.parseInt(orderDate);
    }
    private void validate(String orderDate) throws IllegalArgumentException{
        validateOrderdateIsNumber(orderDate);
    }
    private void validateOrderdateIsNumber(String orderDate){
        if(!Pattern.matches(REGEX_DIGIT, orderDate)){
            throw new IllegalArgumentException();
        }
    }
}
