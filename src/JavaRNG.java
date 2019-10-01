import java.util.Random;

public class JavaRNG implements RNG {
    final private int PRECISION = 1000;
    Random rng;

    JavaRNG() {
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
            int lowerInt = (int) (lower * PRECISION);
            int upperInt = (int) (upper * PRECISION);
            int resultInt = rng.nextInt((upperInt - lowerInt)) + lowerInt;
            return (double) resultInt / PRECISION;
        }
    }

    @Override
    public double doubleInRange(int lower, int upper) {
        if (lower > upper) {
            throw new IllegalArgumentException("upper must be greater than lower");
        } else {
            int resultInt = rng.nextInt((upper - lower) * PRECISION) + lower * PRECISION;
            System.out.println((double) resultInt / PRECISION);

            return (double) resultInt / PRECISION;
        }
    }

    @Override
    public boolean fiftyFiftyChance() {
        return returnTrueWithChanceOf(0.5);
    }

    @Override
    public boolean returnTrueWithChanceOf(double chance) {
        return (doubleInRange(0, 1) < chance);
    }
}
