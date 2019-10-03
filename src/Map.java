import whale.util.Coordinate;
import whale.util.JavaRNG;
import whale.util.RNG;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Map implements IMap {

    private final int ROWS;
    private final int COLUMNS;

    public IPlayer player;
    List<IItem> itemList;
    List<ITrap> traps;
    private List<List<Character>> terrain;
    private String message = "";

    Map(int rows, int columns) {
        ROWS = rows;
        COLUMNS = columns;
        terrain = generatedTerrain();
        // TODO Data structure for grids

        player = new Player(new Coordinate(3, 3));
        itemList = new LinkedList<>();
        itemList.add(new Item(new Coordinate(2, 1)));

        RNG rng = new JavaRNG();
        traps = new LinkedList<>();
        for (int i = 0; i < 3; i++) {
            Coordinate tmp = new Coordinate(rng.intInRange(0, ROWS), rng.intInRange(0, COLUMNS));
            while (!isCellEmpty(tmp)) {
                tmp = new Coordinate(rng.intInRange(0, ROWS), rng.intInRange(0, COLUMNS));
            }
            TrapType ttype = TrapType.values()[rng.intInRange(0, TrapType.values().length)];
            traps.add(makeTrap(tmp, ttype));
        }
    }

    public void updateItemsAndInventory() {
        for (IItem item : itemList) {
            if (player.getPosition().equals(item.getPosition())) {
                player.addToInventory(item);
                // TODO Is the remove threadsafe?
                itemList.remove(item);
                message = "Item picked up.";
            }
        }
    }

    public void updateTraps() {
        Iterator<ITrap> i = traps.iterator();
        ITrap trap;
        while (i.hasNext()) {
            trap = i.next();
            if (player.getPosition().equals(trap.getTrigger().getPosition())) {
                message = "Trap sprung: " + trap.getType();
                // TODO apply damage to entity on specific field, not just player (to have traps hit enemies/other traps etc)
                player.hit(trap.getType().getDamage());
                i.remove();
            }
        }
    }

    @Override
    public String getMessage() {
        return message;
    }

    // TODO put that factory inside Trap and let it do it's own RNG
    private ITrap makeTrap(Coordinate position, TrapType type) {
        ITrap trap = new Trap(position, type);
        Coordinate possibleTriggerLocation = trap.trapTriggerLocationPossibility();
        while (!isCellEmpty(possibleTriggerLocation)) {
            possibleTriggerLocation = trap.trapTriggerLocationPossibility();
        }
        trap.connectTrapTrigger(possibleTriggerLocation);
        return trap;
    }

    public void setTerrain(List<List<Character>> terrain) {
        this.terrain = terrain;
    }

    // TODO make the conversion of 2D List to 2D array a little nicer
    public char[][] getDrawableMap() {
        char[][] result = getDrawableTerrain();

        for (IItem item : itemList) {
            result = addToDrawableMap(result, item);
        }
        for (ITrap trap : traps) {
            result = addToDrawableMap(result, trap);
            result = addToDrawableMap(result, trap.getTrigger());
        }

        result = addToDrawableMap(result, player);

        return result;
    }

    char[][] getDrawableTerrain() {
        char[][] result = new char[ROWS][COLUMNS];
        for (int row = 0; row < ROWS; row++) {
            for (int column = 0; column < COLUMNS; column++) {
                result[row][column] = terrain.get(row).get(column);
            }
        }
        return result;
    }

    private char[][] addToDrawableMap(final char[][] map, final Placeable placeable) {
        char[][] result = map.clone();
        int row = placeable.getPosition().row();
        int column = placeable.getPosition().column();
        result[row][column] = placeable.getSymbol();
        return result;
    }

    List<List<Character>> generatedTerrain() {
        List<List<Character>> result = new ArrayList<>();
        for (int row = 0; row < ROWS; row++) {
            result.add(new ArrayList<>());
            for (int column = 0; column < COLUMNS; column++) {
                if (shouldBeWall(new Coordinate(row, column))) {
                    result.get(row).add('#');
                } else {
                    result.get(row).add('.');
                }
            }
        }
        return result;
    }

    private boolean shouldBeWall(Coordinate coordinate) {
        boolean result = false;
        if (coordinate.getRow() == 0 || coordinate.getRow() == ROWS - 1) {
            result = true;
        }
        if (coordinate.getColumn() == 0 || coordinate.getColumn() == COLUMNS - 1) {
            result = true;
        }
        return result;
    }

    // TODO replace with Coordinate
    @Override
    public boolean isCellEmpty(Coordinate coordinate) {
        if (isInsideMapBounds(new Coordinate(coordinate.getRow(), coordinate.getColumn()))) {
            return terrain.get(coordinate.getRow()).get(coordinate.getColumn()) == '.';
        }
        return false;
    }

    private boolean isInsideMapBounds(Coordinate coordinate) {
        return coordinate.getRow() >= 0 &&
               coordinate.getRow() < terrain.size() &&
               coordinate.getColumn() >= 0 &&
               coordinate.getColumn() < terrain.get(coordinate.getRow()).size();
    }
}
