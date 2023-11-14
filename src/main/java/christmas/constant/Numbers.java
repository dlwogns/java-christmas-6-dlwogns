package christmas.constant;

public enum Numbers {
    CHECKSTAR(5000),
    CHECKTREE(10000),
    CHECKSANTA(20000),
    MENUNUMBERLIMIT(20),
    MENUNAME(0),
    MENUPRICE(1),
    INITIALMENUCOUNT(0),
    STARTOFDATE(1),
    ENDOFDATE(31),
    DAYSPERWEEK(7),
    CHECKFRIDAY(1),
    CHECKSATURDAY(2),
    CHECKSTARDAY(3),
    CHRISTMAS(25),
    INITIALDISCOUNT(0),
    DISCOUNTPERDAY(100),
    INITIALDDAYDISCOUNT(1000),
    DISCOUNTPERMENU(2023),
    STARDAYDISCOUNT(1000),
    CHAMPAGNEPRICE(25000),
    CHECKCHAMPAGNE(120000);

    private final Integer value;

    Numbers(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
