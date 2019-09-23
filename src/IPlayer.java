interface IPlayer extends Placeable {
    void moveTo(int row, int column);
    void addToInventory(IItem item);
}
