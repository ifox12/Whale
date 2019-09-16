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
    public boolean isCellEmpty(int row, int column) {
        return map[row][column] == '.';

    }

    @Override
    public char[][] drawableRepresentation() {
        return copy2DCharArray(map);
    }

    private char[][] copy2DCharArray(char[][] input) {
        char[][] result = input.clone();
        for (int row = 0; row < input.length; row++) {
            result[row] = input[row].clone();
        }
        return result;
    }
}
