public class Item implements Placeable {

    final char symbol = '$';
    Coordinate position;

    Item(int row, int column) {
        position = new Coordinate(row, column);
    }

    @Override
    public Coordinate getPosition() {
        return position;
    }

    @Override
    public int getRow() {
        return position.row;
    }

    @Override
    public int getColumn() {
        return position.column;
    }

    @Override
    public char getSymbol() {
        return symbol;
    }
}
