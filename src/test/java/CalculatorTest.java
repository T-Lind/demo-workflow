import com.github.tlind.Calculator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest {
    @Test
    public void testAdd() {
        Calculator calculator = new Calculator();
        /*
         * Unit tests are super handy, they can check basic elements of the functionality of your code.
         * Ex. sketchy or complex logic would be good to run through a unit test, because it's error prone
         * This test will fail, because 2 + 3 is not 6.
         * You'll see an error in "Build with Gradle" like "Task :test FAILED"
         */
//        assertEquals(6, calculator.add(2, 3));

        /*
         * This test will pass, because 2 + 3 is 5.
         * You'll see a success message in "Build with Gradle" like "Task :test SUCCESS"
         */
        assertEquals(5, calculator.add(2, 3));
    }
}
