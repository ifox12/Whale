import java.util.LinkedList;

public class Map implements IMap {

    private char[][] map;
    private char[][] drawableRepresentation;

    Map() {
        setMap_Default();
    }

    char[][] drawableRepresentation(IPlayer player, LinkedList<IItem> itemList) {
        drawableRepresentation = convertMapToDrawableRepresentation(map);
        for (IItem item : itemList) {
            addToDrawableRepresentation(item);
        }
        addToDrawableRepresentation(player);
        return drawableRepresentation;
    }

    private void addToDrawableRepresentation(Placeable placeable) {
        int row = placeable.getPosition().row();
        int column = placeable.getPosition().column();
        drawableRepresentation[row][column] = placeable.getSymbol();
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

    private char[][] convertMapToDrawableRepresentation(char[][] input) {
        char[][] result = input.clone();
        for (int row = 0; row < input.length; row++) {
            result[row] = input[row].clone();
        }
        return result;
    }
}
