package christmas.model;

import static christmas.constant.ErrorMessage.ERROR_HEADER;
import static christmas.constant.ErrorMessage.ERROR_IN_MENU;
import static christmas.constant.ErrorMessage.ERROR_IN_MENU_ONLY_BEVERAGE;
import static christmas.constant.Numbers.INITIAL_MENU_COUNT;

import java.util.EnumMap;
import java.util.Map;

public class MenuChecker {
    private final EnumMap<MenuBoard, Integer> menuChecker = new EnumMap<>(MenuBoard.class);

    public MenuChecker(Menu menu) throws IllegalArgumentException{
        for (MenuBoard menuBoard : MenuBoard.values()) {
            menuChecker.put(menuBoard, INITIAL_MENU_COUNT.getValue());
        }
        checkMenu(menu.getMenu());
        checkOnlyBeverage();
    }

    private void checkMenu(Map<String, Integer> menu) throws IllegalArgumentException {
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

    private void checkOnlyBeverage() throws IllegalArgumentException{
        if(checkMenuCount(MenuBoard.MAIN_COURSE) && checkMenuCount(MenuBoard.DESSERT) && checkMenuCount(MenuBoard.APPETIZER)){
            throw new IllegalArgumentException(ERROR_HEADER.getValue() + ERROR_IN_MENU_ONLY_BEVERAGE.getValue());
        }
    }
    private boolean checkMenuCount(MenuBoard menuType){
        return menuChecker.get(menuType).equals(INITIAL_MENU_COUNT.getValue());
    }


    public EnumMap<MenuBoard, Integer> getMenuChecker() {
        return menuChecker;
    }
}
