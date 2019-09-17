import java.util.LinkedList;

class Player implements Placeable {
    private final char symbol = '@';

    private IMap map;
    private Coordinate position;
    private LinkedList<IItem> inventory;

    Player(Coordinate position, IMap map) {
        this.map = map;
        this.position = position;
        inventory = new LinkedList<>();
    }

    boolean moveLeftFor(int steps) {
        return moveTo(position.row(), position.column() - steps);
    }

    boolean moveRightFor(int steps) {
        return moveTo(position.row(), position.column() + steps);
    }

    boolean moveUpFor(int steps) {
        return moveTo(position.row() - steps, position.column());
    }

    boolean moveDownFor(int steps) {
        return moveTo(position.row() + steps, position.column());
    }

    boolean moveTo(int row, int column) {
        if (map.isCellEmpty(row, column)) {
            changePosition(row, column);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Coordinate getPosition() {
        return position;
    }

    @Override
    public char getSymbol() {
        return symbol;
    }

    void addToInventory(IItem item) {
        if (position.equals(item.getPosition())) {

            inventory.add(item);
        }
    }

    boolean inventoryContains(Placeable item) {
        return inventory.contains((IItem) item);
    }

    private void changePosition(int row, int column) {
        this.position = new Coordinate(row, column);
    }
}
