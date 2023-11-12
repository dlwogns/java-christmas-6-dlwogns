package christmas.model;

import java.util.EnumMap;
import java.util.Map;

public class MenuChecker {
    private final EnumMap<MenuBoard, Integer> menuChecker = new EnumMap<>(MenuBoard.class);
    private final Map<String, Integer> menu;
    public MenuChecker(Map<String,Integer> menu) {
        for(MenuBoard menuBoard : MenuBoard.values()){
            menuChecker.put(menuBoard, 0);
        }
        this.menu = menu;
        checkMenu();
        checkOnlyBeverage();
    }
    private void checkMenu() throws IllegalArgumentException{
        for(String menuName : menu.keySet()){
            MenuBoard menuBoard = checkMenuName(menuName);
            if(menuBoard == null){
                throw new IllegalArgumentException();
            }
            menuChecker.replace(menuBoard, menuChecker.get(menuBoard) + 1);
        }
    }
    private MenuBoard checkMenuName(String menuName){
        for(MenuBoard menuBoard : MenuBoard.values()){
            if(menuBoard.getValue().keySet().contains(menuName)){
                return menuBoard;
            }
        }
        return null;
    }
    private void checkOnlyBeverage(){
        int mainCount = menuChecker.get(MenuBoard.MAIN_COURSE);
        int appetizerCount = menuChecker.get(MenuBoard.APPETIZER);
        int dessertCount = menuChecker.get(MenuBoard.DESSERT);
        if (mainCount == 0 && appetizerCount == 0 && dessertCount == 0){
            throw new IllegalArgumentException();
        }
    }
}
