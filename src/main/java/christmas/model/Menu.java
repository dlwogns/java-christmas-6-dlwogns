package christmas.model;

import java.util.Map;
import java.util.regex.Pattern;

public class Menu {
    private final String menu;
    private final String MENU_FORMAT = "^[가-힣]*-\\d$";

    public Menu(final String menu) {
        validate(menu);
        this.menu = menu;
    }
    private void validate(final String menu){
        validateMenuFormat(menu);
    }
    private void validateMenuFormat(final String menu){
        if(!Pattern.matches(MENU_FORMAT, menu)){
            throw new IllegalArgumentException();
        }
    }

}
