public interface RNG {
    int intInRange(int lower, int upper);

    double doubleInRangeInclusive(double lower, double upper);

    double doubleInRangeInclusive(int lower, int upper);

    boolean fiftyFiftyChance();

    boolean returnTrueWithChanceOf(double chance);
}
