package calculator;

import java.util.ArrayList;
import java.util.List;

public class Delimiters {


    private static final List<String> BASIC_DELIMITERS = List.of(",", ":");

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
