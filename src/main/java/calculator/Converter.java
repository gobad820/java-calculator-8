package calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class Converter {

    private Converter() {
    }

    public static List<Integer> convertStringToInteger(List<String> numbers) {
        return numbers.stream().mapToInt(Integer::parseInt).boxed()
            .collect(Collectors.toCollection(ArrayList::new));
    }

}
