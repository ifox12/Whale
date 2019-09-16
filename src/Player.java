class Player implements Placeable {
    private IMap map;

    Coordinate position;
    final char symbol = '@';

    Player(Coordinate position, IMap map) {
        this.map = map;
        this.position = position;
    }

    boolean moveTo(int row, int column) {
        if (map.isCellEmpty(row, column)) {
            changePosition(row, column);
            return true;
        } else {
            return false;
        }
    }

    private void changePosition(int row, int column) {
        this.position = new Coordinate(row, column);
    }

    boolean moveLeftFor(int steps) {
        return moveTo(position.row, position.column - steps);
    }

    boolean moveRightFor(int steps) {
        return moveTo(position.row, position.column + steps);
    }

    boolean moveUpFor(int steps) {
        return moveTo(position.row - steps, position.column);
    }

    boolean moveDownFor(int steps) {
        return moveTo(position.row + steps, position.column);
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
