package christmas.controller;

import christmas.model.Menu;
import christmas.model.OrderDate;
import christmas.view.InputView;
import christmas.view.OutputView;

public class PromotionController {
    private static final InputView inputView = InputView.getInstance();
    private static final OutputView outputView = OutputView.getInstance();

    public void play(){
        outputView.printHelloMessage();

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
        try {
            Menu menu = new Menu(inputView.readMenu());
            return menu;
        }catch (IllegalArgumentException e){
            return getMenu();
        }
    }
}
