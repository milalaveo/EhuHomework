package Strings;

import org.junit.*;

import java.io.FileReader;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class TestRunner {
    private static final String RESULT_HEAD = "result(";
    private static final String RESULT_TAIL = ") = ";
    private static final String PLUS = " + ";
    private static final String MINUS = " - ";
    private static final int PLUS_LENGTH = PLUS.length();

    private static int getResult(String csvName, StringBuilder strResult) throws FileNotFoundException {
        int errorLines = 0;
        double sum = 0.0;
        try (Scanner scanner = new Scanner(new FileReader(csvName))) {
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] parts = line.split(";");
                int index;
                try {
                    index = Integer.parseInt(parts[0]);
                    double value = Double.parseDouble(parts[index]);
                    sum += value;
                    strResult.append(value < 0 ? MINUS : PLUS).append(Math.abs(value));
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    errorLines++;
                }
            }
            if (strResult.length() > 0 && strResult.charAt(1) == '+') {
                strResult.delete(0, PLUS_LENGTH);
            }
            if (strResult.length() > 0 && strResult.charAt(1) == '-') {
                strResult.delete(0, 1).delete(1, 2);
            }
            strResult.insert(0, RESULT_HEAD).append(RESULT_TAIL).append(sum);
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("File not found");
        }
        return errorLines;
    }

    @Test
    public void TestFile1() throws FileNotFoundException {
        StringBuilder result = new StringBuilder();
        int errorLines = getResult("tests/Strings/file1.csv", result);
        String expectedIn1 = RESULT_HEAD + "5.2" + MINUS + "3.14" + PLUS + "0.0" + RESULT_TAIL + "2.06";

        Assert.assertEquals(3, errorLines);
        Assert.assertEquals(expectedIn1, result.toString());
    }

    @Test
    public void TestFile2() throws FileNotFoundException {
        StringBuilder result = new StringBuilder();
        int errorLines = getResult("tests/Strings/file2.csv", result);
        String expectedIn2 = RESULT_HEAD + "-3.1" + MINUS + "1.0" + RESULT_TAIL + "-4.1";

        Assert.assertEquals(0, errorLines);
        Assert.assertEquals(expectedIn2, result.toString());
    }

    @Test
    public void TestFile3() throws FileNotFoundException {
        StringBuilder result = new StringBuilder();
        int errorLines = getResult("tests/Strings/file3.csv", result);
        String expectedIn3 = RESULT_HEAD + "0.75" + RESULT_TAIL + "0.75";

        Assert.assertEquals(0, errorLines);
        Assert.assertEquals(expectedIn3, result.toString());
    }

    @Test
    public void TestFile4() throws FileNotFoundException {
        StringBuilder result = new StringBuilder();
        int errorLines = getResult("tests/Strings/file4.csv", result);
        String expectedIn4 = RESULT_HEAD + "0.0" + RESULT_TAIL + "0.0";

        Assert.assertEquals(0, errorLines);
        Assert.assertEquals(expectedIn4, result.toString());
    }

    @Test
    public void TestFile5() throws FileNotFoundException {
        StringBuilder result = new StringBuilder();
        int errorLines = getResult("tests/Strings/file5.csv", result);
        String expectedIn5 = RESULT_HEAD + RESULT_TAIL + "0.0";

        Assert.assertEquals(1, errorLines);
        Assert.assertEquals(expectedIn5, result.toString());
    }

    @Test(expected = FileNotFoundException.class)
    public void testWrongCsvName() throws FileNotFoundException {
        StringBuilder result = new StringBuilder();
        getResult("wrongFileName.csv", result);
    }
}
