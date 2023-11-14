package christmas.constant;

public enum Format {
    MENU_FORMAT("^([가-힣]+-\\d+)(,([가-힣]+-\\d+))*"),
    DELIMETER_PER_MENUORDER(","),
    DELIMETER_PER_EACH_MENU("-"),
    SPACE(" "),
    CRLF("\n"),
    DECIMAL_FORMAT("#,###"),
    REGEX_DIGIT("^[\\d]*$");

    private final String value;

    Format(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
