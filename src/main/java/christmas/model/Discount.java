package christmas.model;


public enum Discount {
    DDAYDISCOUNT("DDAYDISCOUNT"),
    WEEKDAYDISCOUNT("WEEKDAYDISCOUNT"),
    WEEKENDDISCOUNT(" WEEKENDDISCOUNT"),
    STARDISCOUNT("STARDISCOUNT"),
    TOTALDISCOUNT("TOTALDISCOUNT");
    private final String discount;

    Discount(String discount) {
        this.discount = discount;
    }

    public String getValue() {
        return discount;
    }
}
