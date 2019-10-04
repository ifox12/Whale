package whale.util;

import java.util.List;

public interface RNG {
    int intInRange(int lower, int upper);

    double doubleInRange(double lower, double upper);

    boolean returnTrueWithChanceOf(double chance);

    Coordinate chooseOne(List<Coordinate> coordinates);

    //TODO something like: choose random value from array(generic array)
}
