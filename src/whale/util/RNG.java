package whale.util;

public interface RNG {
    int intInRange(int lower, int upper);

    double doubleInRange(double lower, double upper);

    boolean returnTrueWithChanceOf(double chance);

    //TODO something like: choose random value from array(generic array)
}
