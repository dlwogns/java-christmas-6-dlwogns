package christmas.model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Menu {
    private final Map<String,Integer> menu;
    private final String MENU_FORMAT = "^([가-힣]+-\\d+)(,([가-힣]+-\\d+))*";

    public Menu(final String menu) {
        validateMenuFormat(menu);
        this.menu = parseMenu(menu);
        validateMenuNumbers(this.menu);
    }
    private void validateMenuFormat(final String menu){
        if(!Pattern.matches(MENU_FORMAT, menu)){
            throw new IllegalArgumentException();
        }
    }
    private void validateMenuNumbers(Map<String,Integer> menu){
        if(checkMenuNumberIsLessThanZero(menu)){
            throw new IllegalArgumentException();
        }
    }
    private boolean checkMenuNumberIsLessThanZero(Map<String, Integer> menu){
        return menu.values().stream().filter(number -> {return 0 >= number;}).count() > 0;
    }

    private Map<String,Integer> parseMenu(String menu){
        return parseMenuByDash(parseMenuByComma(menu));
    }

    private List<String> parseMenuByComma(String menu){
        return Arrays.stream(menu.split(","))
                .collect(Collectors.toList());
    }
    private Map<String, Integer> parseMenuByDash(List<String> menu){
        return menu.stream()
                .map(s -> s.split("-"))
                .collect(Collectors.toMap(arr->arr[0], arr -> Integer.parseInt(arr[1])));
    }

    @Override
    public String toString() {
        return menu.toString();
    }
}
