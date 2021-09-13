package be.brussel.junit5_mathutils_javabrains;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("When running MathUtils")
class MathUtilsTest {

    MathUtils mathUtils;
    TestInfo testInfo;
    TestReporter testReporter;

    @BeforeEach
    void initialize(TestInfo testInfo, TestReporter testReporter){
        mathUtils = new MathUtils();
        this.testInfo = testInfo;
        this.testReporter = testReporter;

        //testReporter.publishEntry("Running " + testInfo.getTestMethod() + " with tag " + testInfo.getTags());
    }

    @BeforeAll
    static void beforeAll(){
        System.out.println("Print this message before the tests");
    }


    @Nested
    @DisplayName("add method")
    @Tag("General")
    class AddTest{

        @Test
        @DisplayName("you can make the sum of two positive numbers")
        void addPositiveTest() {
            int expected = 2;
            int actual = mathUtils.add(1,1);
            assertEquals(expected, actual, () ->"it should return the right sum: "+ expected +" instead of "+ actual);
        }

        @Test
        @DisplayName("you can make the sum of one positive with one negative number")
        void addNegativeTest() {
            int expected = -2;
            int actual = mathUtils.add(-3,1);
            assertEquals(expected, actual, "it should return the right sum");
        }
    }


    @RepeatedTest(3)
    @DisplayName("divide method, you can make the division of two numbers")
    @Tag("General")
    void divideTest(){
        assertThrows(ArithmeticException.class, () -> mathUtils.divide(3,0), "a division by zero should throw an ArithmeticException");
        //assumeTrue(false);
    }

    @Test
    @DisplayName("multiply method, you can multiply two numbers")
    @Tag("General")
    void multiplyTest(){
        System.out.println("Running " + testInfo.getTestMethod() + " with tag " + testInfo.getTags());
        testReporter.publishEntry("Running " + testInfo.getTestMethod() + " with tag " + testInfo.getTags());
        assertAll(
                () -> assertEquals(4, mathUtils.multiply(2,2)),
                () -> assertEquals(0, mathUtils.multiply(2,0)),
                () -> assertEquals(-2, mathUtils.multiply(2,-1))
        );
    }

    @Test
    @DisplayName("computeCircleRadius method, you can calculate the radius of a circle")
    @Tag("Circle")
    void computeCircleRadiusTest(){
        assertEquals(314.1592653589793, mathUtils.computeCircleArea(10), "it should return the right circle area");
    }

}