package calculator;

import java.util.ArrayList;
import java.util.List;

public class Delimiters {

    private final static String FIRST_DELIMITER = ",";
    private final static String SECOND_DELIMITER = ":";

    private static final List<String> BASIC_DELIMITERS = List.of(FIRST_DELIMITER, SECOND_DELIMITER);

    private final List<String> delimiters;

    public Delimiters() {
        this.delimiters = new ArrayList<>(BASIC_DELIMITERS);
    }

    public void addCustomDelimiters(String delimiter) {
        delimiters.add(delimiter);
    }

    public List<String> getDelimiters() {
        return new ArrayList<>(delimiters);
    }

}
