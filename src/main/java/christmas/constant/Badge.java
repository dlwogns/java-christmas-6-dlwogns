package christmas.constant;

public enum Badge {
    STAR("별"),
    TREE("트리"),
    SANTA("산타"),
    NONE("없음");
    private final String badge;

    Badge(String badge) {
        this.badge = badge;
    }

    public String getValue() {
        return badge;
    }
}
