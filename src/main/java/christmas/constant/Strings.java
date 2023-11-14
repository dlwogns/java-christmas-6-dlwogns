package christmas.constant;

public enum Strings {
    MONEY_UNIT("원");

    private final String value;

    Strings(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
