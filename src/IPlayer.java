interface IPlayer extends Placeable {
    void moveTo(int row, int column);
    void addToInventory(IItem item);
    boolean inventoryContains(Placeable item);

    void hit(int damage);

    void isDead();
}
