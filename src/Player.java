import whale.util.Coordinate;

import java.util.LinkedList;

class Player implements IPlayer {
    private char symbol = '@';

    private Coordinate position;
    private LinkedList<IItem> inventory;

    private int health;

    Player(Coordinate position) {
        this.position = position;
        inventory = new LinkedList<>();
        health = 10;
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

    public boolean inventoryContains(Placeable item) {
        return inventory.contains((IItem) item);
    }

    public void hit(int damage) {
        health -= damage;
    }

    public void is_dead() {
        if (health <= 0) {
            symbol = '+';
        }
    }
}
