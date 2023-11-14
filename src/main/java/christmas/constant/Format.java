package christmas.constant;

public enum Format {
    MENU_FORMAT("^([가-힣]+-\\d+)(,([가-힣]+-\\d+))*"),
    DELIMETERPERMENUORDER(","),
    DELIMETERPEREACHMENU("-"),
    SPACE(" "),
    CRLF("\n"),
    DECIMALFORMAT("#,###"),
    REGEX_DIGIT("^[\\d]*$");

    private final String value;

    Format(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
