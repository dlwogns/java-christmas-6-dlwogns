package christmas.controller;

import christmas.model.Menu;
import christmas.model.MenuChecker;
import christmas.model.OrderDate;
import christmas.view.InputView;
import christmas.view.OutputView;

public class PromotionController {
    private static final InputView inputView = InputView.getInstance();
    private static final OutputView outputView = OutputView.getInstance();

    public void play(){
        outputView.printHelloMessage();
        getMenu();
    }
    private OrderDate getDate(){
        try{
            OrderDate orderDate = new OrderDate(inputView.readDate());
            return orderDate;
        }catch (IllegalArgumentException e){
            return getDate();
        }
    }

    private Menu getMenu(){
        MenuChecker menuChecker = new MenuChecker();
        try {
            Menu menu = new Menu(inputView.readMenu());
            menuChecker.checkMenu(menu.getMenu());
            menuChecker.checkOnlyBeverage();
            return menu;
        }catch (IllegalArgumentException e){
            return getMenu();
        }catch (IllegalStateException e){
            return getMenu();
        }

    }
}
