public interface RNG {
    int intInRange(int lower, int upper);

    double doubleInRange(double lower, double upper);

    boolean returnTrueWithChanceOf(double chance);
}
