package christmas.constant;

public enum Strings {
    MONEYUNIT("원")
    ;

    private final String value;

    Strings(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
