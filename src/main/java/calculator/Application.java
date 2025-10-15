package calculator;

import camp.nextstep.edu.missionutils.Console;

public class Application {

    private final static String INPUT_DIALOGUE = "덧셈할 문자열을 입력해 주세요.";
    private final static String OUTPUT_DIALOGUE = "결과 : ";

    public static void main(String[] args) {
        System.out.println(INPUT_DIALOGUE);
        var input = Console.readLine();
        if (input == null || input.isEmpty()) {
            System.out.println(OUTPUT_DIALOGUE + 0);
            return;
        }
        var parsedStringExpressions = Parser.parse(input);
        var values = Converter.convertStringToInteger(parsedStringExpressions);
        Validator.validateValues(values);
        System.out.println(OUTPUT_DIALOGUE + SumCalculator.getSum(values));
    }

}
