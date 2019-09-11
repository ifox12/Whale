public class Map implements IMap {

    char[][] map;

    Map() {
        setMap_Default();
    }

    void setMap(char[][] mapData) {
        this.map = mapData;
    }

    void setMap_Default() {
        map = new char[][] {
                {'#', '#', '#', '#', '#', '#'},
                {'#', '.', '.', '.', '.', '#'},
                {'#', '.', '.', '.', '.', '#'},
                {'#', '.', '.', '.', '.', '#'},
                {'#', '.', '.', '.', '.', '#'},
                {'#', '#', '#', '#', '#', '#'},
        };
    }

    @Override
    public boolean isCellEmpty(int x, int y) {
        return map[y][x] == '.' ? true : false;

    }

    @Override
    public char[][] drawableRepresentation() {
        return map;
    }
}
