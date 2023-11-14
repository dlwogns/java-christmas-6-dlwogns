package christmas.constant;

public enum Numbers {
    CHECK_STAR(5000),
    CHECK_TREE(10000),
    CHECK_SANTA(20000),
    MENU_NUMBER_LIMIT(20),
    MENU_NAME(0),
    MENU_PRICE(1),
    INITIAL_MENU_COUNT(0),
    START_OF_DATE(1),
    END_OF_DATE(31),
    DAYS_PER_WEEK(7),
    CHECK_FRIDAY(1),
    CHECK_SATURDAY(2),
    CHECK_STARDAY(3),
    CHRISTMAS(25),
    INITIAL_DISCOUNT(0),
    DISCOUNT_PER_DAY(100),
    INITIAL_DDAY_DISCOUNT(1000),
    DISCOUNT_PER_MENU(2023),
    STARDAY_DISCOUNT(1000),
    CHAMPAGNE_PRICE(25000),
    CHECK_CHAMPAGNE(120000),
    EVENT_CHECK_VALUE(10000)
    ;

    private final Integer value;

    Numbers(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
