package christmas.model;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

public enum MenuBoard {
    APPETIZER(Collections.unmodifiableMap(
            Map.of(
                    "양송이스프", 6000,
                    "타파스", 5500,
                    "시저샐러드", 8000
            ))),
    MAIN_COURSE(Collections.unmodifiableMap(
            Map.of(
                    "티본스테이크", 55000,
                    "바비큐립", 54000,
                    "해산물파스타", 35000,
                    "크리스마스파스타", 25000
            ))),
    DESSERT(Collections.unmodifiableMap(
            Map.of(
                    "초코케이크", 15000,
                    "아이스크림", 5000
            ))),
    BEVERAGE(Collections.unmodifiableMap(
            Map.of(
                    "제로콜라", 3000,
                    "레드와인", 60000,
                    "샴페인", 25000
            )));
    private final Map<String, Integer> menuBoard;

    MenuBoard(Map<String, Integer> menuBoard) {
        this.menuBoard = menuBoard;
    }

    public Map<String, Integer> getValue() {
        return menuBoard;
    }
}
