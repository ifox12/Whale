public class Map implements IMap {

    private char[][] map;

    Map() {
        setMap_Default();
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
