package christmas.model;

import static christmas.constant.Format.CRLF;
import static christmas.constant.Format.DELIMETERPEREACHMENU;
import static christmas.constant.Format.DELIMETERPERMENUORDER;
import static christmas.constant.Format.MENU_FORMAT;
import static christmas.constant.Format.SPACE;
import static christmas.constant.Numbers.MENUNAME;
import static christmas.constant.Numbers.MENUNUMBERLIMIT;
import static christmas.constant.Numbers.MENUPRICE;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Menu {
    private final Map<String, Integer> menu;

    public Menu(final String menu) {
        validateMenuFormat(menu);
        this.menu = validateMenuDuplicated(menu);
        validateMenuNumbers(this.menu);
    }

    private void validateMenuFormat(final String menu) {
        if (!Pattern.matches(MENU_FORMAT.getValue(), menu)) {
            throw new IllegalArgumentException();
        }
    }

    private void validateMenuNumbers(Map<String, Integer> menu) {
        if (checkMenuNumberIsLessThanZero(menu)) {
            throw new IllegalArgumentException();
        }
        if (checkMenuNumberIsMoreThanLimit(menu)) {
            throw new IllegalArgumentException();
        }
    }

    private Map<String, Integer> validateMenuDuplicated(String menu) {
        try {
            return parseMenu(menu);
        } catch (IllegalStateException e) {
            throw new IllegalStateException();
        }
    }

    private boolean checkMenuNumberIsMoreThanLimit(Map<String, Integer> menu) {
        return menu.values().stream().mapToInt(Integer::intValue).sum() > MENUNUMBERLIMIT.getValue();
    }

    private boolean checkMenuNumberIsLessThanZero(Map<String, Integer> menu) {
        return menu.values().stream().anyMatch(number -> 0 >= number);
    }

    private Map<String, Integer> parseMenu(String menu) {
        return parseMenuByDash(parseMenuByComma(menu));
    }

    private List<String> parseMenuByComma(String menu) {
        return Arrays.stream(menu.split(DELIMETERPERMENUORDER.getValue()))
                .collect(Collectors.toList());
    }

    private Map<String, Integer> parseMenuByDash(List<String> menu) {
        return menu.stream()
                .map(s -> s.split(DELIMETERPEREACHMENU.getValue()))
                .collect(Collectors.toMap(arr -> arr[MENUNAME.getValue()],
                        arr -> Integer.parseInt(arr[MENUPRICE.getValue()])));
    }

    public Map<String, Integer> getMenu() {
        return menu;
    }

    @Override
    public String toString() {
        String menu = this.menu.entrySet()
                .stream()
                .map(entry -> entry.getKey() + SPACE.getValue() + entry.getValue() + "개")
                .collect(Collectors.joining(CRLF.getValue()));
        return menu;
    }
}
