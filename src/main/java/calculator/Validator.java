package calculator;

import static java.lang.Character.isDigit;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class Validator {

    private Validator() {
    }

    public static void validateValues(List<Integer> values) {
        values.forEach(x -> {
            if (x < 0) {
                throw new IllegalArgumentException("입력은 모두 자연수여야합니다.");
            }
        });
    }

    public static List<String> filterExpression(List<String> splitStringExpression,
        Delimiters delimiters) {
        return splitStringExpression.stream()
            .filter(e -> validateNumberAndDelimiter(e, delimiters.getDelimiters()))
            .collect(Collectors.toCollection(ArrayList::new));
    }

    private static boolean validateNumberAndDelimiter(String trimmed, List<String> delimiters) {
        if (trimmed.chars().anyMatch(
            c -> !isDigit((char) c) && !delimiters.contains(Character.toString((char) c)))) {
            throw new IllegalArgumentException("입력은 기본 구분자(',', ':') 커스텀 구분자 그리고 자연수로 구성된 문자열입니다.");
        }
        return true;
    }

    public static void validateCustomDelimiters(String input) {
        if (input.startsWith("//")) {
            throw new IllegalArgumentException("커스텀 구분자가 누락되었습니다.");
        }
    }
}
