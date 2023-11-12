package christmas.view;

public class OutputView {
    private static OutputView instance;
    public static  OutputView getInstance(){
        if(instance == null)
            return instance = new OutputView();
        return instance;
    }

    public void printHelloMessage(){
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }
    public void printEventMessage(){
        System.out.println("12월 26일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
    }
}
