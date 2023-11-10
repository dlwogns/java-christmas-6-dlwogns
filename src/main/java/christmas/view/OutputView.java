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
}
