import whale.util.Coordinate;

interface IMap {
    boolean isCellEmpty(Coordinate coordinate);

    char[][] getDrawableMap();

    void updateItemsAndInventory();

    void updateTraps();

    String getMessage();
}
