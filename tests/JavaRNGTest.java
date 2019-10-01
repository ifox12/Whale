import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JavaRNGTest {

    @RepeatedTest(10)
    void intInRange_Range0To5_0LessOrEqualThanResultLessThan5() {
        JavaRNG testRng = new JavaRNG();

        int result = testRng.intInRange(0, 5);

        assertTrue(result >= 0);
        assertTrue(result < 5);
    }

    @RepeatedTest(10)
    void intInRange_Range0To1_ResultIs0() {
        JavaRNG testRng = new JavaRNG();

        int result = testRng.intInRange(0, 1);

        assertEquals(0, result);
    }

    @Test
    void intInRange_Range0To0_IllegalArgumentException() {
        JavaRNG testRng = new JavaRNG();

        assertThrows(IllegalArgumentException.class, () -> {
            testRng.intInRange(0, 0);
        });

    }

    @RepeatedTest(10)
    void intInRange_Range1To5_Between1And4() {
        JavaRNG testRng = new JavaRNG();

        int result = testRng.intInRange(1, 5);

        assertTrue(result >= 1);
        assertTrue(result < 5);
    }

    @Test
    void intInRange_Range5To1_IllegalArgumentException() {
        JavaRNG testRng = new JavaRNG();

        assertThrows(IllegalArgumentException.class, () -> {
            testRng.intInRange(5, 1);
        });
    }

    @RepeatedTest(10)
    void doubleInRange_4To5_Between1And5() {
        JavaRNG testRng = new JavaRNG();

        double result = testRng.doubleInRange(4, 5);

        assertTrue(result >= 4);
        assertTrue(result <= 5);
    }

    @RepeatedTest(10)
    void doubleInRange_0To1_Between0And1() {
        JavaRNG testRng = new JavaRNG();

        double result = testRng.doubleInRange(0, 1);

        assertTrue(result >= 0);
        assertTrue(result <= 1);
    }

    // TODO not working yet
    @RepeatedTest(10)
    void doubleInRange_5To10_Between5And10() {
        JavaRNG testRng = new JavaRNG();

        double result = testRng.doubleInRange(5, 10);

        assertTrue(result >= 5);
        assertTrue(result <= 10);
    }

    @RepeatedTest(10)
    void doubleInRange_0dTo1d_Between0And1() {
        JavaRNG testRng = new JavaRNG();

        double result = testRng.doubleInRange(0d, 1d);

        assertTrue(result >= 0);
        assertTrue(result <= 1);
    }


    @RepeatedTest(10)
    void returnTrueWithChanceOf_100Percent_ReturnsTrue() {
        JavaRNG testRng = new JavaRNG();

        boolean result = testRng.returnTrueWithChanceOf(1.0);

        assertTrue(result);
    }

    @RepeatedTest(10)
    void returnTrueWithChanceOf_0Percent_ReturnsFalse() {
        JavaRNG testRng = new JavaRNG();

        boolean result = testRng.returnTrueWithChanceOf(0.0);


        assertFalse(result);
    }

    @RepeatedTest(10)
    void returnTrueWithChanceOf_50Percent_Roughly50PercentTrues_SometimesFailsDueToExtremeOutliers() {
        JavaRNG testRng = new JavaRNG();

        int trueCounter = 0;
        for (int i = 0; i < 100; i++) {
            if (testRng.returnTrueWithChanceOf(0.5)) {
                trueCounter++;
            }
        }

        assertTrue(trueCounter >= 32);
        assertTrue(trueCounter <= 68);
    }
}