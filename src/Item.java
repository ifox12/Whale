import whale.util.Coordinate;

public class Item implements IItem {

    final char symbol = '$';
    Coordinate position;

    Item(Coordinate position) {
        this.position = position;
    }

    @Override
    public Coordinate getPosition() {
        return position;
    }

    @Override
    public char getSymbol() {
        return symbol;
    }
}
