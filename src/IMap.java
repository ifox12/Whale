import whale.util.Coordinate;

interface IMap {
    boolean isCellEmpty(int x, int y);

    void setMap(char[][] mapData);

    char[][] getDrawableMap();

    boolean isCellEmpty(Coordinate position);
}
