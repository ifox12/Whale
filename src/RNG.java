public interface RNG {
    int intInRange(int lower, int upper);

    double doubleInRange(double lower, double upper);

    boolean fiftyFiftyChance();

    boolean returnTrueWithChanceOf(double chance);
}
