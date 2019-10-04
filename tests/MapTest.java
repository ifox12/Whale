import org.junit.jupiter.api.Test;
import whale.util.Coordinate;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MapTest {

    @Test
    void generatedMap_RunItWith4By4_ReturnsSquareRoom() {
        Map testMap = new Map(4, 4);

        testMap.setTerrain(testMap.generatedTerrain());
        char[][] result = testMap.getDrawableTerrain();

        char[][] squareRoom = new char[][] {
                {'#', '#', '#', '#'},
                {'#', '.', '.', '#'},
                {'#', '.', '.', '#'},
                {'#', '#', '#', '#'},
        };


        for (int i = 0; i < squareRoom.length; i++) {
            assertArrayEquals(squareRoom[i], result[i]);
        }
    }

    @Test
    void generatedMap_RunItWith5By6_ReturnsSquareRoom() {
        Map testMap = new Map(5, 6);

        testMap.setTerrain(testMap.generatedTerrain());
        char[][] result = testMap.getDrawableTerrain();

        char[][] squareRoom = new char[][] {
                {'#', '#', '#', '#', '#', '#'},
                {'#', '.', '.', '.', '.', '#'},
                {'#', '.', '.', '.', '.', '#'},
                {'#', '.', '.', '.', '.', '#'},
                {'#', '#', '#', '#', '#', '#'},
        };


        for (int i = 0; i < squareRoom.length; i++) {
            assertArrayEquals(squareRoom[i], result[i]);
        }
    }


    // TODO test null returned with no empty fields
//    @Test
//    void findRandomEmptyCell_3x3Map_CenterCell() {
//        Map testMap = new Map(3, 3);
//        CardinalArea cArea = new CardinalArea();
//        cArea.setBasePosition(new Coordinate(0, 1));
//        cArea.setDistance(2);
//
//        Coordinate result = testMap.findRandomEmptyCell(cArea);
//
//        assertEquals(1, result.row());
//        assertEquals(1, result.column());
//    }

    @Test
    void isAccessibleByPlayer_Wall_ReturnsFalse() {
        Map testMap = new Map(6, 6);

        boolean result = testMap.isAccessibleByPlayer(new Coordinate(0, 0));

        assertFalse(result);
    }

    @Test
    void isAccessibleByPlayer_EmptySpace_ReturnsTrue() {
        Map testMap = new Map(6, 6);

        boolean result = testMap.isAccessibleByPlayer(new Coordinate(1, 1));

        assertTrue(result);
    }

    @Test
    void isAccessibleByPlayer_OutOfBounds_ReturnsFalse() {
        Map testMap = new Map(6, 6);

        boolean result = testMap.isAccessibleByPlayer(new Coordinate(9999, 9999));

        assertFalse(result);
    }

    @Test
    void isAccessibleByPlayer_NegativeOutOfBounds_ReturnsFalse() {
        Map testMap = new Map(6, 6);

        boolean result = testMap.isAccessibleByPlayer(new Coordinate(-1, -1));

        assertFalse(result);
    }

    @Test
    void isCellEmpty_Wall_ReturnsFalse() {
        Map testMap = new Map(6, 6);
        IPlayer stubPlayer = new FakePlayer(new Coordinate(2, 1));
        testMap.setPlayer(stubPlayer);
        testMap.setItemList(new ArrayList<>());
        testMap.setTraps(new ArrayList<>());

        boolean result = testMap.isCellEmpty(new Coordinate(0, 0));

        assertFalse(result);
    }

    @Test
    void isCellEmpty_EmptySpace_ReturnsTrue() {
        Map testMap = new Map(6, 6);
        IPlayer stubPlayer = new FakePlayer(new Coordinate(2, 1));
        testMap.setPlayer(stubPlayer);
        testMap.setItemList(new ArrayList<>());
        testMap.setTraps(new ArrayList<>());

        boolean result = testMap.isCellEmpty(new Coordinate(1, 1));

        assertTrue(result);
    }

    @Test
    void isCellEmpty_OutOfBounds_ReturnsFalse() {
        Map testMap = new Map(6, 6);
        IPlayer stubPlayer = new FakePlayer(new Coordinate(2, 1));
        testMap.setPlayer(stubPlayer);
        testMap.setItemList(new ArrayList<>());
        testMap.setTraps(new ArrayList<>());

        boolean result = testMap.isCellEmpty(new Coordinate(9999, 9999));

        assertFalse(result);
    }

    @Test
    void isCellEmpty_NegativeOutOfBounds_ReturnsFalse() {
        Map testMap = new Map(6, 6);
        IPlayer stubPlayer = new FakePlayer(new Coordinate(2, 1));
        testMap.setPlayer(stubPlayer);
        testMap.setItemList(new ArrayList<>());
        testMap.setTraps(new ArrayList<>());

        boolean result = testMap.isCellEmpty(new Coordinate(-1, -1));

        assertFalse(result);
    }

    @Test
    void isCellEmpty_PlayerPresentOnCell_ReturnsFalse() {
        Map testMap = new Map(6, 6);
        IPlayer stubPlayer = new FakePlayer(new Coordinate(1, 1));
        testMap.setPlayer(stubPlayer);
        testMap.setItemList(new ArrayList<>());
        testMap.setTraps(new ArrayList<>());

        boolean result = testMap.isCellEmpty(new Coordinate(1, 1));

        assertFalse(result);
    }

    @Test
    void isCellEmpty_PlayerNotPresentOnCell_ReturnsTrue() {
        Map testMap = new Map(6, 6);
        IPlayer stubPlayer = new FakePlayer(new Coordinate(2, 1));
        testMap.setPlayer(stubPlayer);
        testMap.setItemList(new ArrayList<>());
        testMap.setTraps(new ArrayList<>());

        boolean result = testMap.isCellEmpty(new Coordinate(1, 1));

        assertTrue(result);
    }

    @Test
    void isCellEmpty_ItemPresentOnCell_ReturnsFalse() {
        Map testMap = new Map(6, 6);
        IPlayer stubPlayer = new FakePlayer(new Coordinate(3, 3));
        testMap.setPlayer(stubPlayer);
        testMap.setItemList(new ArrayList<>());
        testMap.getItemList().add(new FakeItem(new Coordinate(1, 1)));
        testMap.setTraps(new ArrayList<>());

        boolean result = testMap.isCellEmpty(new Coordinate(1, 1));

        assertFalse(result);
    }

    @Test
    void isCellEmpty_ItemNotPresentOnCell_ReturnsFalse() {
        Map testMap = new Map(6, 6);
        IPlayer stubPlayer = new FakePlayer(new Coordinate(3, 3));
        testMap.setPlayer(stubPlayer);
        testMap.setItemList(new ArrayList<>());
        testMap.getItemList().add(new FakeItem(new Coordinate(2, 1)));
        testMap.setTraps(new ArrayList<>());

        boolean result = testMap.isCellEmpty(new Coordinate(1, 1));

        assertTrue(result);
    }

    @Test
    void isCellEmpty_TrapPresentOnCell_ReturnsFalse() {
        Map testMap = new Map(6, 6);
        IPlayer stubPlayer = new FakePlayer(new Coordinate(3, 3));
        testMap.setPlayer(stubPlayer);
        testMap.setItemList(new ArrayList<>());
        testMap.setTraps(new ArrayList<>());
        testMap.getTraps().add(new FakeTrap(new Coordinate(1, 1)));

        boolean result = testMap.isCellEmpty(new Coordinate(1, 1));

        assertFalse(result);
    }

    @Test
    void isCellEmpty_TrapNotPresentOnCell_ReturnsFalse() {
        Map testMap = new Map(6, 6);
        IPlayer stubPlayer = new FakePlayer(new Coordinate(3, 3));
        testMap.setPlayer(stubPlayer);
        testMap.setItemList(new ArrayList<>());
        testMap.setTraps(new ArrayList<>());
        testMap.getTraps().add(new FakeTrap(new Coordinate(2, 1)));

        boolean result = testMap.isCellEmpty(new Coordinate(1, 1));

        assertTrue(result);
    }


    private class FakePlayer implements IPlayer {
        Coordinate position;

        FakePlayer(Coordinate coordinate) {
            this.position = coordinate;
        }

        @Override
        public void moveTo(int row, int column) {

        }

        @Override
        public void addToInventory(IItem item) {

        }

        @Override
        public boolean inventoryContains(Placeable item) {
            return false;
        }

        @Override
        public void hit(int damage) {

        }

        @Override
        public void isDead() {

        }

        @Override
        public Coordinate getPosition() {
            return position;
        }

        @Override
        public char getSymbol() {
            return 0;
        }
    }

    private class FakeTrap implements ITrap {
        private final Coordinate position;
        private TrapType type;
        private TrapTrigger trigger;

        FakeTrap(Coordinate coordinate) {
            this.position = coordinate;
            this.trigger = new TrapTrigger(position);
        }

        @Override
        public Coordinate trapTriggerLocationPossibility() {
            return null;
        }

        @Override
        public void connectTrapTrigger(Coordinate triggerPosition) {

        }

        @Override
        public Placeable getTrigger() {
            return trigger;
        }

        @Override
        public TrapType getType() {
            return null;
        }

        @Override
        public Coordinate getPosition() {
            return position;
        }

        @Override
        public char getSymbol() {
            return 0;
        }
    }
}
