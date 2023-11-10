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
        return new OrderDate(inputView.readDate());
    }

    private Menu getMenu(){
        return new Menu(inputView.readMenu());
    }
}
