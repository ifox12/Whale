public interface RNG {
    int intInRange(int lower, int upper);

    double doubleInRange(double lower, double upper);

    double doubleInRange(int lower, int upper);

    boolean returnTrueWithChanceOf(double chance);
}
