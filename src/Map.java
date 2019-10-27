import whale.map.MapCell;
import whale.util.Coordinate;
import whale.util.JavaRNG;
import whale.util.RNG;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Map implements IMap {

    final int ROWS;
    final int COLUMNS;
    private final RNG rng = new JavaRNG();
    private IPlayer player;
    private List<IItem> itemList;
    private List<ITrap> traps;
    private String message = "";
    private List<List<MapCell>> terrain;

    // TODO unify the code to find an empty map field to place things on
    // TODO have the map store the location of things (or the things as a placeable layer)
    Map(int rows, int columns) {
        ROWS = rows;
        COLUMNS = columns;
        setTerrain(procgenTerrain());

        setPlayer(new Player(randomPlayerPosition()));
        setItemList(new LinkedList<>());


        setTraps(new LinkedList<>());
        for (int i = 0; i < 3; i++) {
            getTraps().add(TrapFactory.makeTrap(this, TrapType.returnRandom()));
        }

        // TODO make placement of different Placeables independent of placing order (see isCellEmpty() and related)
        getItemList().add(new Item(findRandomEmptyCell(new NoArea(ROWS, COLUMNS))));
    }

    private Coordinate randomPlayerPosition() {
        List<Coordinate> list = new ArrayList<>();

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                Coordinate currentCell = new Coordinate(i, j);
                if (isAccessibleByPlayer(currentCell)) {
                    list.add(currentCell);
                }
            }
        }
        return rng.chooseOne(list);
    }

    public void updateItemsAndInventory() {
        for (IItem item : getItemList()) {
            if (getPlayer().comparePosition(item)) {
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
            if (getPlayer().comparePosition(trap.getTrigger())) {
                message = "Trap sprung: " + trap.getClass();
                // TODO apply damage to entity on specific field, not just player (to have traps hit enemies/other traps etc)
                getPlayer().hit(trap.getDamage());
                i.remove();
            }
        }
    }

    @Override
    public String getMessage() {
        return message;
    }

    List<List<MapCell>> generatedTerrain() {
        List<List<MapCell>> result = new ArrayList<>();
        for (int row = 0; row < ROWS; row++) {
            result.add(new ArrayList<>());
            for (int column = 0; column < COLUMNS; column++) {
                if (isOuterWall(new Coordinate(row, column))) {
                    result.get(row).add(new MapCell('#'));
                } else {
                    result.get(row).add(new MapCell('.'));
                }
            }
        }
        return result;
    }

    // TODO make the conversion of 2D List to 2D array a little nicer
    char[][] getDrawableTerrain() {
        char[][] result = new char[ROWS][COLUMNS];
        for (int row = 0; row < ROWS; row++) {
            for (int column = 0; column < COLUMNS; column++) {
                result[row][column] = getTerrain().get(row).get(column).symbol;
            }
        }
        return result;
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

    private List<List<MapCell>> getTerrain() {
        return terrain;
    }

    void setTerrain(List<List<MapCell>> terrain) {
        this.terrain = terrain;
    }

    private char[][] addToDrawableMap(final char[][] map, final Placeable placeable) {
        char[][] result = map.clone();
        int row = placeable.getPosition().row();
        int column = placeable.getPosition().column();
        result[row][column] = placeable.getSymbol();
        return result;
    }

    // TODO algorithm for placing rooms
    //  set minimum size for room and minimum spacing between
    //  place rooms on map randomly, till there's not enough space left for a new one
    //  optimal placing performance wise
    List<List<MapCell>> procgenTerrain() {
        List<List<MapCell>> result = new ArrayList<>();
        char fillCharacter = '#';
        fillMap(result, fillCharacter);

        int numOfRooms = rng.intInRange(5, 12);
        Room[] rooms = new Room[numOfRooms];
        for (int i = 0; i < numOfRooms; i++) {
            Room room = new Room(COLUMNS, ROWS);
            rooms[i] = room;
            placeRoomOnMap(result, room);
        }


        for (Room room : rooms) {
            room.findBorderTiles();
        }

        for (Room room : rooms) {
            boolean isConnected = false;
            for (Coordinate tile : room.borderTiles) {
                if (getCharAt(tile, result) == '.') {
                    isConnected = true;
                    break;
                }
            }

            Coordinate currentCorridorCell = room.getRandomBorderTile();
            Coordinate direction = findDirection(room, currentCorridorCell);
            List<Coordinate> trail = new ArrayList<>();
            while (!isConnected) {
                while (isOuterWall(currentCorridorCell)) {
                    currentCorridorCell = room.getRandomBorderTile();
                    direction = findDirection(room, currentCorridorCell);
                    trail.clear();
                }
                if (getCharAt(currentCorridorCell, result) == '.') {
                    for (Coordinate tile : trail) {
                        setCharAt(tile, result, '.');
                    }
                    isConnected = true;
                } else {
                    trail.add(currentCorridorCell);
                    currentCorridorCell = currentCorridorCell.add(direction);
                }
            }
        }
        return result;
    }

    private Coordinate findDirection(Room room, Coordinate currentCorridorCell) {
        Coordinate result = new Coordinate(0, 0);
        if (currentCorridorCell.row() < room.topEdgeRow) {
            result = new Coordinate(-1, 0);
        } else if (currentCorridorCell.column() < room.leftEdgeColumn) {
            result = new Coordinate(0, -1);
        } else if (currentCorridorCell.row() > room.bottomEdgeRow) {
            result = new Coordinate(+1, 0);
        } else if (currentCorridorCell.column() > room.rightEdgeColumn) {
            result = new Coordinate(0, +1);
        }
        return result;
    }

    private Character getCharAt(Coordinate cell, List<List<MapCell>> map) {
        return map.get(cell.row()).get(cell.column()).symbol;
    }

    private void setCharAt(Coordinate cell, List<List<MapCell>> map, char character) {
        map.get(cell.row()).set(cell.column(), new MapCell(character));
    }

    private void placeRoomOnMap(List<List<MapCell>> result, Room room) {
        for (int row = room.topEdgeRow; row <= room.bottomEdgeRow; row++) {
            for (int column = room.leftEdgeColumn; column <= room.rightEdgeColumn; column++) {
                Coordinate cell = new Coordinate(row, column);
                if (isOuterWall(cell)) {
                    setCharAt(cell, result, '#');
                } else {
                    setCharAt(cell, result, '.');
                }
            }
        }
    }

    private void fillMap(List<List<MapCell>> result, char fillCharacter) {
        for (int row = 0; row < ROWS; row++) {
            result.add(new ArrayList<>());
            for (int column = 0; column < COLUMNS; column++) {
                result.get(row).add(new MapCell(fillCharacter));
            }
        }
    }

    private boolean isOuterWall(Coordinate coordinate) {
        boolean result = false;
        if (coordinate.row() == 0 || coordinate.row() == ROWS - 1) {
            result = true;
        }
        if (coordinate.column() == 0 || coordinate.column() == COLUMNS - 1) {
            result = true;
        }
        return result;
    }

    // TODO Put the placeables in one List to traverse over when checking for empty fields
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
            if (trap.comparePosition(coordinate) || trap.getTrigger().comparePosition(coordinate)) {
                return false;
            }
        }
        return true;
    }

    private boolean isNotItemPosition(Coordinate coordinate) {
        for (IItem item : getItemList()) {
            if (item.comparePosition(coordinate)) {
                return false;
            }
        }
        return true;
    }

    // TODO there's duplication of that somewhere in here
    private boolean isNotPlayerPosition(Coordinate coordinate) {
        return !getPlayer().comparePosition(coordinate);
    }

    public boolean isAccessibleByPlayer(Coordinate coordinate) {
        return isInsideMapBounds(coordinate) && isEmptyTerrain(coordinate);
    }

    private boolean isEmptyTerrain(Coordinate coordinate) {
        return getCharAt(coordinate, getTerrain()) == '.';
    }

    private boolean isInsideMapBounds(Coordinate coordinate) {
        return coordinate.row() >= 0 &&
               coordinate.row() < getTerrain().size() &&
               coordinate.column() >= 0 &&
               coordinate.column() < getTerrain().get(coordinate.row()).size();
    }

    Coordinate findRandomEmptyCell(Area area) {
        try {
            List<Coordinate> coordinates = area.getCells();
            Coordinate possibleLocation = rng.chooseOne(coordinates);
            while (!isCellEmpty(possibleLocation)) {
                coordinates.remove(possibleLocation);
                if (coordinates.isEmpty()) {
                    throw new Exception("target area seems to be full");
                }
                possibleLocation = rng.chooseOne(coordinates);
            }
            return possibleLocation;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
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

