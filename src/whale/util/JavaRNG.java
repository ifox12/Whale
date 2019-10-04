package whale.util;

import java.util.List;
import java.util.Random;

public class JavaRNG implements RNG {
    private final int PRECISION = 1000;
    private final Random rng;

    public JavaRNG() {
        rng = new Random();
    }

    @Override
    public int intInRange(int lower, int upper) {
        if (lower > upper) {
            throw new IllegalArgumentException("upper must be greater than lower");
        } else {
            return rng.nextInt(upper - lower) + lower;
        }
    }

    @Override
    public double doubleInRange(double lower, double upper) {
        if (lower > upper) {
            throw new IllegalArgumentException("upper must be greater than lower");
        } else {
            int lowerInt = convertDoubleToIntWithSomePrecisionLoss(lower);
            int upperInt = convertDoubleToIntWithSomePrecisionLoss(upper);
            return fixedWidthDoubleInIntRange(lowerInt, upperInt);
        }
    }

    private int convertDoubleToIntWithSomePrecisionLoss(double input) {
        return (int) (input * PRECISION);
    }

    private double convertToFixedWidthDouble(int input) {
        return ((double) input) / PRECISION;
    }

    private double fixedWidthDoubleInIntRange(int lower, int upper) {
        int resultInt = intInRange(lower, upper);
        return convertToFixedWidthDouble(resultInt);
    }

    @Override
    public boolean returnTrueWithChanceOf(double chance) {
        return (doubleInRange(0, 1) < chance);
    }

    @Override
    public Coordinate chooseOne(List<Coordinate> coordinates) {
        int index = intInRange(0, coordinates.size());
        Coordinate coordinate = coordinates.get(index);
        return coordinate;
    }
}
