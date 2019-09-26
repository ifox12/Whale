import java.util.LinkedList;

public class Map implements IMap {

     char[][] map;

    Map() {
        setMap_Default();
    }

    char[][] getDrawableMap() {
        char[][] result = map.clone();
        for (int row1 = 0; row1 < map.length; row1++) {
            result[row1] = map[row1].clone();
        }
        return result;
    }

    private void setMap_Default() {
        map = new char[][]{
                {'#', '#', '#', '#', '#', '#'},
                {'#', '.', '.', '.', '.', '#'},
                {'#', '.', '.', '.', '.', '#'},
                {'#', '.', '.', '.', '.', '#'},
                {'#', '.', '.', '.', '.', '#'},
                {'#', '#', '#', '#', '#', '#'},
        };
    }

    void setMap(char[][] mapData) {
        this.map = mapData;
    }

    @Override
    public boolean isCellEmpty(int row, int column) {
        if (row < map.length && column < map[row].length) {
            return map[row][column] == '.';
        } else {
            return false;
        }
    }

}
