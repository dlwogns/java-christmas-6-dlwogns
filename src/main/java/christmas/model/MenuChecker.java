package christmas.model;

import static christmas.constant.ErrorMessage.ERROR_HEADER;
import static christmas.constant.ErrorMessage.ERROR_IN_MENU;
import static christmas.constant.ErrorMessage.ERROR_IN_MENU_ONLY_BEVERAGE;
import static christmas.constant.Numbers.INITIAL_MENU_COUNT;

import java.util.EnumMap;
import java.util.Map;

public class MenuChecker {
    private final EnumMap<MenuBoard, Integer> menuChecker = new EnumMap<>(MenuBoard.class);

    public MenuChecker() {
        for (MenuBoard menuBoard : MenuBoard.values()) {
            menuChecker.put(menuBoard, INITIAL_MENU_COUNT.getValue());
        }
    }

    public void checkMenu(Map<String, Integer> menu) throws IllegalArgumentException {
        for (String menuName : menu.keySet()) {
            MenuBoard menuBoard = checkMenuName(menuName);
            if (menuBoard == null) {
                throw new IllegalArgumentException(ERROR_HEADER.getValue()+ ERROR_IN_MENU.getValue());
            }
            menuChecker.replace(menuBoard, menuChecker.get(menuBoard) + menu.get(menuName));
        }
    }

    private MenuBoard checkMenuName(String menuName) {
        for (MenuBoard menuBoard : MenuBoard.values()) {
            if (menuBoard.getValue().containsKey(menuName)) {
                return menuBoard;
            }
        }
        return null;
    }

    public void checkOnlyBeverage() {
        int mainCount = menuChecker.get(MenuBoard.MAIN_COURSE);
        int appetizerCount = menuChecker.get(MenuBoard.APPETIZER);
        int dessertCount = menuChecker.get(MenuBoard.DESSERT);
        if (mainCount == INITIAL_MENU_COUNT.getValue()
                && appetizerCount == INITIAL_MENU_COUNT.getValue()
                && dessertCount == INITIAL_MENU_COUNT.getValue()) {
            throw new IllegalArgumentException(ERROR_HEADER.getValue() + ERROR_IN_MENU_ONLY_BEVERAGE.getValue());
        }
    }

    public EnumMap<MenuBoard, Integer> getMenuChecker() {
        return menuChecker;
    }
}
