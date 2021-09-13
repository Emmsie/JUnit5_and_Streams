package be.brussel.tdd_kata_streams_stringcalculator_runthrough_emmsie;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("When StringCalculator runs ")
class StringCalculatorTest {

    StringCalculator stringCalculator;

    @BeforeEach
    void init(){
        stringCalculator = new StringCalculator();
    }

    @Test
    @DisplayName("the add method with empty String param ")
    void addEmptyStringTest() {
        try {
            assertEquals(0, stringCalculator.add(""), "should return 0");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("the add method with String param that contains one number")
    void addOneNumberTest() {
        try {
            assertEquals(3, stringCalculator.add("3"), "should return the number");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("the add method with String param that contains two numbers separated by commas")
    void addTwoNumbersTest() {
        try {
            assertEquals(3, stringCalculator.add("1,2"), "should return the sum of the numbers");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("the add method with String param that contains numbers separated by commas")
    void addUnknownAmountOfNumbersTest() {
        assertAll(
                () ->  assertEquals(3, stringCalculator.add("1,2")),
                () ->  assertEquals(6, stringCalculator.add("1,2,3")),
                () ->  assertEquals(10, stringCalculator.add("1,2,3,4"))
        );
    }

    @Test
    @DisplayName("the add method with String param that contains numbers separated by commas or new lines")
    void addUnknownAmountOfNumbers2Test() {
        assertAll(
                () ->  assertEquals(3, stringCalculator.add("1\n2")),
                () ->  assertEquals(6, stringCalculator.add("1,2\n3")),
                () ->  assertEquals(10, stringCalculator.add("1\n2,3\n4"))
        );
    }

    @Test
    @DisplayName("the add method with String param that contains numbers separated by a delimiter, commas or new lines")
    void addNumbersWithDifferentDelimitersTest() {
        assertAll(
                () ->  assertEquals(3, stringCalculator.add("//;\n1;2")),
                () ->  assertEquals(9, stringCalculator.add("//!\n1!2!1,2\n3")),
                () ->  assertEquals(9, stringCalculator.add("//n\n1n2n1,2\n3"))
        );
    }

    @Test
    @DisplayName("the add method with String param that contains a negative number")
    void addNegativeTest() {
        assertThrows(Exception.class, () -> stringCalculator.add("-1"), "negatives not allowed: -1 ");
        assertThrows(Exception.class, () -> stringCalculator.add("-1,-2"), "negatives not allowed: -1 -2 ");
    }

    @Test
    @DisplayName("the add method with String param that contains numbers bigger then 1000")
    void addNumberBiggerThan1000Test() {
        try {
            assertEquals(2, stringCalculator.add("1001,2"), "should return the sum of the numbers, omitting any number bigger then 1000 from the calculation");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("the getAllCount method")
    void getAllCountTest() {
        assertAll(
                () ->  assertEquals(3, stringCalculator.add("1\n2")),
                () ->  assertEquals(6, stringCalculator.add("1,2\n3")),
                () ->  assertEquals(10, stringCalculator.add("1\n2,3\n4")),
                () ->  assertEquals(3, stringCalculator.getCalledCount())
        );
    }
}