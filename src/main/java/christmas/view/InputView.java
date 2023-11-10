package christmas.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    private static InputView instance;
    public static InputView getInstance(){
        if(instance == null)
            return instance = new InputView();
        return instance;
    }

    public String readDate(){
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        String input = Console.readLine();
        return input;
    }
}