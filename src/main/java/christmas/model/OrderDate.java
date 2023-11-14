package christmas.model;

import static christmas.constant.ErrorMessage.ERRORHEADER;
import static christmas.constant.ErrorMessage.ERRORINDATE;
import static christmas.constant.Format.REGEX_DIGIT;
import static christmas.constant.Numbers.CHECKFRIDAY;
import static christmas.constant.Numbers.CHECKSATURDAY;
import static christmas.constant.Numbers.CHECKSTARDAY;
import static christmas.constant.Numbers.CHRISTMAS;
import static christmas.constant.Numbers.DAYSPERWEEK;
import static christmas.constant.Numbers.ENDOFDATE;
import static christmas.constant.Numbers.STARTOFDATE;

import java.util.regex.Pattern;

public class OrderDate {
    private final Integer orderDate;

    public OrderDate(String orderDate) {
        validate(orderDate);
        this.orderDate = Integer.parseInt(orderDate);
    }

    private void validate(String orderDate) throws IllegalArgumentException {
        validateOrderdateIsNumber(orderDate);
        validateOrderdateRange(orderDate);
    }

    private void validateOrderdateIsNumber(String orderDate) {
        if (!Pattern.matches(REGEX_DIGIT.getValue(), orderDate)) {
            throw new IllegalArgumentException(ERRORHEADER.getValue() + ERRORINDATE.getValue());
        }
    }

    private void validateOrderdateRange(String orderDate) {
        int date = Integer.parseInt(orderDate);
        if (date < STARTOFDATE.getValue() || date > ENDOFDATE.getValue()) {
            throw new IllegalArgumentException(ERRORHEADER.getValue() + ERRORINDATE.getValue());
        }
    }

    public boolean checkWeekEnd() {
        return orderDate % DAYSPERWEEK.getValue() == CHECKFRIDAY.getValue()
                || orderDate % DAYSPERWEEK.getValue() == CHECKSATURDAY.getValue();
    }

    public boolean checkStarDay() {
        return orderDate % DAYSPERWEEK.getValue() == CHECKSTARDAY.getValue() || orderDate.equals(CHRISTMAS.getValue());
    }

    public Integer getOrderDate() {
        return orderDate;
    }
}
