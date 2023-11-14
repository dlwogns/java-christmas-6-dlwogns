package christmas.model;

import static christmas.constant.ErrorMessage.ERROR_HEADER;
import static christmas.constant.ErrorMessage.ERROR_IN_MENU;
import static christmas.constant.ErrorMessage.ERROR_IN_MENU_COUNT;
import static christmas.constant.Format.CRLF;
import static christmas.constant.Format.DELIMETER_PER_EACH_MENU;
import static christmas.constant.Format.DELIMETER_PER_MENUORDER;
import static christmas.constant.Format.MENU_FORMAT;
import static christmas.constant.Format.SPACE;
import static christmas.constant.Numbers.MENU_NAME;
import static christmas.constant.Numbers.MENU_NUMBER_LIMIT;
import static christmas.constant.Numbers.MENU_PRICE;

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
            throw new IllegalArgumentException(ERROR_HEADER.getValue() + ERROR_IN_MENU.getValue());
        }
    }

    private void validateMenuNumbers(Map<String, Integer> menu) {
        if (checkMenuNumberIsLessThanZero(menu)) {
            throw new IllegalArgumentException(ERROR_HEADER.getValue() + ERROR_IN_MENU_COUNT.getValue());
        }
        if (checkMenuNumberIsMoreThanLimit(menu)) {
            throw new IllegalArgumentException(ERROR_HEADER.getValue() + ERROR_IN_MENU.getValue());
        }
    }

    private Map<String, Integer> validateMenuDuplicated(String menu) {
        try {
            return parseMenu(menu);
        } catch (IllegalStateException e) {
            throw new IllegalStateException(ERROR_HEADER.getValue() + ERROR_IN_MENU.getValue());
        }
    }

    private boolean checkMenuNumberIsMoreThanLimit(Map<String, Integer> menu) {
        return menu.values().stream().mapToInt(Integer::intValue).sum() > MENU_NUMBER_LIMIT.getValue();
    }

    private boolean checkMenuNumberIsLessThanZero(Map<String, Integer> menu) {
        return menu.values().stream().anyMatch(number -> 0 >= number);
    }

    private Map<String, Integer> parseMenu(String menu) {
        return parseMenuByDash(parseMenuByComma(menu));
    }

    private List<String> parseMenuByComma(String menu) {
        return Arrays.stream(menu.split(DELIMETER_PER_MENUORDER.getValue()))
                .collect(Collectors.toList());
    }

    private Map<String, Integer> parseMenuByDash(List<String> menu) {
        return menu.stream()
                .map(s -> s.split(DELIMETER_PER_EACH_MENU.getValue()))
                .collect(Collectors.toMap(arr -> arr[MENU_NAME.getValue()],
                        arr -> Integer.parseInt(arr[MENU_PRICE.getValue()])));
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
