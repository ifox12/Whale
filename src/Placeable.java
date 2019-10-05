import whale.util.Coordinate;

public interface Placeable {
    Coordinate getPosition();

    char getSymbol();

    default boolean comparePosition(Placeable other) {
        return this.getPosition().equals(other.getPosition());
    }

    default boolean comparePosition(Coordinate otherPosition) {
        return this.getPosition().equals(otherPosition);
    }
}
