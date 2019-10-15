import whale.util.JavaRNG;
import whale.util.RNG;

enum TrapType {
    Spike,
    SpikedBoard,
    Dart;

    static TrapType returnRandom() {
        RNG rng = new JavaRNG();
        return TrapType.values()[rng.intInRange(0, TrapType.values().length)];
    }
}
