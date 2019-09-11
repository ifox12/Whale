public class Map implements IMap {

    char[][] map;

    Map() {
        setMap_Default();
    }

    Map(Map another) {
        map = new char[another.map.length][another.map[0].length];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                map[i][j] = another.map[i][j];
            }
        }

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
        return map[y][x] == '.';

    }

    @Override
    public char[][] drawableRepresentation() {
        return new Map(this).map;
    }
}
