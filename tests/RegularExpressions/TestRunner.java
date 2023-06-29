package RegularExpressions;

import org.junit.*;
import java.util.Enumeration;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestRunner {
    private static final String KEY_REG_EXP = "index(.*)";
    private static final String NUM_REG_EXP = "[1-9]\\d*";
    private static final int TAIL_INDEX = 1;
    private static final String VALUE = "value";
    private static final double DELTA = 0.000001;
    private static final String FILE_SOURCE = "in";

    private static Result getResult(String fileName) throws MissingResourceException {
        ResourceBundle rb = ResourceBundle.getBundle(fileName);
        Enumeration<String> keys = rb.getKeys();

        Pattern indexPattern = Pattern.compile(KEY_REG_EXP);
        Pattern numPattern = Pattern.compile(NUM_REG_EXP);

        int errors = 0;
        double sum = 0.0;

        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            Matcher keyMatcher = indexPattern.matcher(key);

            if (keyMatcher.matches()) {
                String keyNumber = keyMatcher.group(TAIL_INDEX);
                String indexValue = rb.getString(key).trim();

                Matcher numberMatcher = numPattern.matcher(keyNumber);
                Matcher valueMatcher = numPattern.matcher(indexValue);

                if (numberMatcher.matches() && valueMatcher.matches()) {
                    String value = VALUE + keyNumber + indexValue;

                    try {
                        sum += Double.parseDouble(rb.getString(value));
                    } catch (NumberFormatException | MissingResourceException e) {
                        errors++;
                    }
                } else {
                    errors++;
                }
            }
        }

        return new Result(errors, sum);
    }

    private static class Result {
        private final int errorLines;
        private final double sum;

        public Result(int errorLines, double sum) {
            this.errorLines = errorLines;
            this.sum = sum;
        }

        @Override
        public String toString() {
            return "errors = " + errorLines + "\nsum = " + sum;
        }
    }

    @Test
    public void testMainScenario() {
        Result[] results = {
                new Result(3, 8.24),
                new Result(9, 30.242),
                new Result(0, 1.9)
        };

        int index = 1;

        for (Result result : results) {
            Result current = getResult(FILE_SOURCE + index);
            Assert.assertEquals(result.errorLines, current.errorLines);
            Assert.assertEquals(result.sum, current.sum, DELTA);
            index++;
        }
    }

    @Test(expected = MissingResourceException.class)
    public void testWrongFileName() throws MissingResourceException {
        getResult("in4");
    }
}
