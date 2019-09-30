import java.util.Random;

public class JavaRNG implements RNG {
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
            return rng.nextDouble() + lower;
        }
    }

    @Override
    public boolean fiftyFiftyChance() {
        return false;
    }

    @Override
    public boolean returnTrueWithChanceOf(double chance) {
        return false;
    }
}
