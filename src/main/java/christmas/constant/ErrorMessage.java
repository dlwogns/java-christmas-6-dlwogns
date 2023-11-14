package christmas.constant;

public enum ErrorMessage {
    ERRORHEADER("[ERROR] "),
    ERRORINDATE("유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    ERRORINMENU("유효하지 않은 주문입니다. 다시 입력해 주세요."),
    ERRORINMENUCOUNT("메뉴의 개수는 1 이상의 숫자만 입력되도록 해주세요."),
    ERRORINMENUONLYBEVERAGE("음료만 주문하실 수 없습니다. 다시 입력해 주세요.")
    ;
    private final String value;

    ErrorMessage(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
