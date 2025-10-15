package calculator;

import java.util.List;

public final class SumCalculator {

    private SumCalculator(){}

    public static int getSum(List<Integer> values) {
        return values.stream().mapToInt(Integer::intValue).sum();
    }
}
