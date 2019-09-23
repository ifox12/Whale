import java.util.LinkedList;

class Player implements IPlayer {
    private final char symbol = '@';

    private Coordinate position;
    private LinkedList<IItem> inventory;

    Player(Coordinate position) {
        this.position = position;
        inventory = new LinkedList<>();
    }

    public void moveTo(int row, int column) {
        changePosition(row, column);
    }

    private void changePosition(int row, int column) {
        this.position = new Coordinate(row, column);
    }

    @Override
    public Coordinate getPosition() {
        return position;
    }

    @Override
    public char getSymbol() {
        return symbol;
    }

    public void addToInventory(IItem item) {
        if (position.equals(item.getPosition())) {

            inventory.add(item);
        }
    }

    boolean inventoryContains(Placeable item) {
        return inventory.contains((IItem) item);
    }
}
