public class Map implements IMap {

     char[][] map;
     int ROWS;
     int COLUMNS;

    Map() {
        map = generatedMap(6,6);
    }

    public char[][] getDrawableMap() {
        char[][] result = map.clone();
        for (int row1 = 0; row1 < map.length; row1++) {
            result[row1] = map[row1].clone();
        }
        return result;
    }

    char[][] generatedMap(final int rows, final int columns) {
        this.ROWS = rows;
        this.COLUMNS = columns;
        char[][] result = new char[ROWS][COLUMNS];
        for (int row = 0; row < ROWS; row++) {
            for (int column = 0; column < COLUMNS; column++) {
                if (shouldBeWall(row, column)) {
                    result[row][column] = '#';
                } else {
                    result[row][column] = '.';
                }
            }
        }
        return result;
    }

    private boolean shouldBeWall(int row, int column) {
        boolean result = false;
        if (row == 0 || row == ROWS - 1) {
            result = true;
        }
        if (column == 0 || column == COLUMNS - 1) {
            result = true;
        }
        return result;
    }

    public void setMap(char[][] mapData) {
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
