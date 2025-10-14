package calculator;

import static java.lang.Character.isDigit;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.regex.Pattern;

public class Application {

    final static String CUSTOM_START = "//";
    final static String CUSTOM_END = "\\n";
    final static String FIRST_DELI = ",";
    final static String SECOND_DELI = ":";
    final static String SPACE = " ";

    public static void main(String[] args) {
        // TODO: 프로그램 구현
        System.out.println("덧셈할 문자열을 입력해 주세요.");
        var input = Console.readLine();
        var customDeli = getCustomDeli(input);
        String trimmed = input.substring(0, input.indexOf(CUSTOM_START));
        trimmed += input.substring(input.indexOf(CUSTOM_END) + CUSTOM_END.length());
        String[] delimieters = {FIRST_DELI, SECOND_DELI, customDeli};
        checkInputString(trimmed, delimieters);
        System.out.println("결과 : " + getResult(trimmed, customDeli));
    }

    private static void checkInputString(String trimmed, String[] delimieters) {
        for (int i = 0; i < trimmed.length(); i++) {
            if (isNotNumberOrDelimiters(trimmed, delimieters, i)) {
                throw new IllegalArgumentException("입력은 구분자와 양수로 구성된 문자열이어야 합니다.");
            }
        }
    }

    private static boolean isNotNumberOrDelimiters(String trimmed, String[] delimieters, int i) {
        return !isDigit(trimmed.charAt(i)) && !Arrays.asList(delimieters)
            .contains(Character.toString(trimmed.charAt(i)));
    }

    private static String getCustomDeli(String input) {
        String customDeli = "";
        if (input.contains(CUSTOM_START) && input.contains(CUSTOM_END)) {
            var startPos = input.indexOf(CUSTOM_START);
            var endPos = input.indexOf(CUSTOM_END);
            customDeli = input.substring(startPos + CUSTOM_START.length(), endPos);
        }
        return customDeli;
    }

    private static int getResult(String input, String customDeli) {
        int result = 0;
        if (hasDelimeters(input, customDeli)) {
            String[] delimieters = {FIRST_DELI, SECOND_DELI, customDeli};
            var regex = getStringBuilder(delimieters).toString();
            var split = input.split(regex);
            return getIntegerSum(split, delimieters, result);
        }
        return Integer.parseInt(input);
    }

    private static StringBuilder getStringBuilder(String[] delis) {
        StringBuilder r = new StringBuilder("([");
        Arrays.stream(delis).forEach(d -> r.append(Pattern.quote(d)));
        r.append("])");
        return r;
    }

    private static boolean hasDelimeters(String input, String customDeli) {
        return input.contains(FIRST_DELI) || input.contains(SECOND_DELI) || (
            !customDeli.isEmpty() && input.contains(customDeli));
    }

    private static int getIntegerSum(String[] split, String[] delis, int result) {
        result += Arrays.stream(split)
            .filter(s -> !Arrays.asList(delis).contains(s) && !s.isEmpty())
            .mapToInt(Integer::parseInt).sum();
        return result;
    }
}
