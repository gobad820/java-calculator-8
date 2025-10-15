package calculator;

import static calculator.Validator.filterExpression;
import static calculator.Validator.validateCustomDelimiters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public final class Parser {

    private final static String CUSTOM_DELIMITER_REGEX = "^//(.)\\\\n(.*)$";

    private Parser() {
    }

    public static List<String> parse(String input) {
        Delimiters delimiters = new Delimiters();
        var parsedExpression = getParsedExpression(input, delimiters);
        return filterExpression(splitExpression(parsedExpression, delimiters), delimiters);

    }

    public static String getParsedExpression(String expression, Delimiters delimiters) {
        Pattern pattern = Pattern.compile(CUSTOM_DELIMITER_REGEX);
        var matcher = pattern.matcher(expression);
        if (matcher.matches()) {
            var customDelimiter = matcher.group(1);
            delimiters.addCustomDelimiters(customDelimiter);
            return matcher.group(2);
        }
        validateCustomDelimiters(expression);
        return expression;
    }

    public static List<String> splitExpression(String expression, Delimiters delimiters) {
        var quotedDelimiters = delimiters.getDelimiters().stream().map(Pattern::quote)
            .collect(Collectors.toCollection(ArrayList::new));
        var regex = String.join("|", quotedDelimiters);
        return Arrays.stream(expression.split(regex)).filter(x -> !x.isEmpty())
            .collect(Collectors.toList());
    }
}
