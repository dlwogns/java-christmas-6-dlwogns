package christmas.model;

import static christmas.constant.ErrorMessage.ERROR_HEADER;
import static christmas.constant.ErrorMessage.ERROR_IN_DATE;
import static christmas.constant.Format.REGEX_DIGIT;
import static christmas.constant.Numbers.CHECK_FRIDAY;
import static christmas.constant.Numbers.CHECK_SATURDAY;
import static christmas.constant.Numbers.CHECK_STARDAY;
import static christmas.constant.Numbers.CHRISTMAS;
import static christmas.constant.Numbers.DAYS_PER_WEEK;
import static christmas.constant.Numbers.END_OF_DATE;
import static christmas.constant.Numbers.START_OF_DATE;

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
            throw new IllegalArgumentException(ERROR_HEADER.getValue() + ERROR_IN_DATE.getValue());
        }
    }

    private void validateOrderdateRange(String orderDate) {
        int date = Integer.parseInt(orderDate);
        if (date < START_OF_DATE.getValue() || date > END_OF_DATE.getValue()) {
            throw new IllegalArgumentException(ERROR_HEADER.getValue() + ERROR_IN_DATE.getValue());
        }
    }

    public boolean checkWeekEnd() {
        return orderDate % DAYS_PER_WEEK.getValue() == CHECK_FRIDAY.getValue()
                || orderDate % DAYS_PER_WEEK.getValue() == CHECK_SATURDAY.getValue();
    }

    public boolean checkStarDay() {
        return orderDate % DAYS_PER_WEEK.getValue() == CHECK_STARDAY.getValue() || orderDate.equals(
                CHRISTMAS.getValue());
    }

    public Integer getOrderDate() {
        return orderDate;
    }
}
