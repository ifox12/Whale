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

    private IPlayer player;
    private List<IItem> itemList;
    private List<ITrap> traps;
    private List<List<Character>> terrain;
    private String message = "";

    Map(int rows, int columns) {
        ROWS = rows;
        COLUMNS = columns;
        terrain = generatedTerrain();
        // TODO Data structure for grids

        setPlayer(new Player(new Coordinate(3, 3)));
        setItemList(new LinkedList<>());
        getItemList().add(new Item(new Coordinate(2, 1)));

        RNG rng = new JavaRNG();
        setTraps(new LinkedList<>());
        for (int i = 0; i < 3; i++) {
            Coordinate randomEmptyCell = findRandomEmptyCell(new NoArea());
            Coordinate targetCoordinate = randomEmptyCell;
            TrapType targetType = TrapType.values()[rng.intInRange(0, TrapType.values().length)];
            getTraps().add(makeTrap(targetCoordinate, targetType));
        }
    }

    public void updateItemsAndInventory() {
        for (IItem item : getItemList()) {
            if (getPlayer().getPosition().equals(item.getPosition())) {
                getPlayer().addToInventory(item);
                // TODO Is the remove threadsafe?
                getItemList().remove(item);
                message = "Item picked up.";
            }
        }
    }

    public void updateTraps() {
        Iterator<ITrap> i = getTraps().iterator();
        ITrap trap;
        while (i.hasNext()) {
            trap = i.next();
            if (getPlayer().getPosition().equals(trap.getTrigger().getPosition())) {
                message = "Trap sprung: " + trap.getType();
                // TODO apply damage to entity on specific field, not just player (to have traps hit enemies/other traps etc)
                getPlayer().hit(trap.getType().getDamage());
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
        // TODO don't go down the rabbit hole to set the basePosition of the TriggerArea
        trap.getType().triggerArea.setBasePosition(trap.getPosition());
        Coordinate possibleTriggerLocation = findRandomEmptyCell(type.triggerArea);
        trap.connectTrapTrigger(possibleTriggerLocation);
        return trap;
    }

    void setTerrain(List<List<Character>> terrain) {
        this.terrain = terrain;
    }

    public char[][] getDrawableMap() {
        char[][] result = getDrawableTerrain();

        for (IItem item : getItemList()) {
            result = addToDrawableMap(result, item);
        }
        for (ITrap trap : getTraps()) {
            result = addToDrawableMap(result, trap);
            result = addToDrawableMap(result, trap.getTrigger());
        }

        result = addToDrawableMap(result, getPlayer());

        return result;
    }

    // TODO make the conversion of 2D List to 2D array a little nicer
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

    @Override
    public boolean isCellEmpty(Coordinate coordinate) {
        boolean result = false;
        if (isInsideMapBounds(coordinate) &&
            isEmptyTerrain(coordinate) &&
            isNotPlayerPosition(coordinate) &&
            isNotItemPosition(coordinate) &&
            isNotTrapPosition(coordinate)) {

            result = true;
        }
        return result;
    }

    private boolean isNotTrapPosition(Coordinate coordinate) {
        for (ITrap trap : getTraps()) {
            if (trap.getPosition().equals(coordinate) || trap.getTrigger().getPosition().equals(coordinate)) {
                return false;
            }
        }
        return true;
    }

    private boolean isNotItemPosition(Coordinate coordinate) {
        for (IItem item : getItemList()) {
            if (item.getPosition().equals(coordinate)) {
                return false;
            }
        }
        return true;
    }

    // TODO there's duplication of that somewhere in here
    private boolean isNotPlayerPosition(Coordinate coordinate) {
        return !getPlayer().getPosition().equals(coordinate);
    }

    public boolean isAccessibleByPlayer(Coordinate coordinate) {
        return isInsideMapBounds(coordinate) && isEmptyTerrain(coordinate);
    }

    private boolean isEmptyTerrain(Coordinate coordinate) {
        return terrain.get(coordinate.getRow()).get(coordinate.getColumn()) == '.';
    }

    private boolean isInsideMapBounds(Coordinate coordinate) {
        return coordinate.getRow() >= 0 &&
               coordinate.getRow() < terrain.size() &&
               coordinate.getColumn() >= 0 &&
               coordinate.getColumn() < terrain.get(coordinate.getRow()).size();
    }

    Coordinate findRandomEmptyCell(Area area) {
        try {
            List<Coordinate> coordinates = area.getCells();
            if (coordinates.isEmpty()) {
                populateWithWholeTerrain(coordinates);
            }
            RNG rng = new JavaRNG();
            Coordinate possibleLocation = null;
            do {
                removeLocationFromList(coordinates, possibleLocation);
                if (coordinates.isEmpty()) {
                    throw new Exception("target area seems to be full");
                }
                possibleLocation = rng.chooseOne(coordinates);
            } while (!isCellEmpty(possibleLocation));

            return possibleLocation;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void populateWithWholeTerrain(List<Coordinate> coordinates) {
        for (int row = 0; row < ROWS; row++) {
            for (int column = 0; column < COLUMNS; column++) {
                coordinates.add(new Coordinate(row, column));
            }
        }
    }

    private void removeLocationFromList(List<Coordinate> coordinates, Coordinate possibleLocation) {
        try {
            if (possibleLocation != null && !coordinates.remove(possibleLocation)) {
                throw new Exception("item slated for deletion is not in coordinates list");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    IPlayer getPlayer() {
        return player;
    }

    void setPlayer(IPlayer player) {
        this.player = player;
    }

    List<IItem> getItemList() {
        return itemList;
    }

    void setItemList(List<IItem> itemList) {
        this.itemList = itemList;
    }

    List<ITrap> getTraps() {
        return traps;
    }

    void setTraps(List<ITrap> traps) {
        this.traps = traps;
    }
}
