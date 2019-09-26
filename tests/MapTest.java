import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class MapTest {

//    @Test
//    void drawableRepresentation_MapFilledWithDots_DotMapWithPlayerAndItem() {
//        Map testMap = new Map();
//        testMap.setMap(dotMapData());
//        IPlayer fakePlayer = new FakePlayer();
//        LinkedList<IItem> fakeItemList = new LinkedList<>();
//        fakeItemList.add(new FakeItem(new Coordinate(2,2)));
//        ITrap fakeTrap = new FakeTrap();
//
//        char[][] result = GameManager.drawableRepresentation(testMap, fakePlayer, fakeItemList, fakeTrap);
//
//        assertEquals('.', result[0][0]);
//        assertEquals('€', result[1][1]);
//        assertEquals('$', result[2][2]);
//        assertEquals('*', result[3][2]);
//        assertEquals('.', result[5][5]);
//    }

    private char[][] dotMapData() {
        char[][] result = new char[10][10];
        for (char[] row : result) {
            Arrays.fill(row, '.');
        }
        return result;
    }

    @Test
    void isCellEmpty_InaccessibleCell_ReturnFalse() {
        Map testMap = new Map();

        boolean result = testMap.isCellEmpty(0, 0);

        assertFalse(result);
    }

    @Test
    void isCellEmpty_EmptyCell_ReturnTrue() {
        Map testMap = new Map();

        boolean result = testMap.isCellEmpty(2, 1);

        assertTrue(result);
    }

    @Test
    void isCellEmpty_OutOfBounds_ReturnFalse() {
        Map testMap = new Map();

        boolean result = testMap.isCellEmpty(9999, 9999);

        assertFalse(result);
    }

    private class FakePlayer implements IPlayer {
        @Override
        public Coordinate getPosition() {
            return new Coordinate(1,1);
        }

        @Override
        public char getSymbol() {
            return '€';
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
        public void is_dead() {

        }
    }

    private class FakeTrap implements ITrap {
        @Override
        public Coordinate getPosition() {
            return new Coordinate(3, 2);
        }

        @Override
        public char getSymbol() {
            return '*';
        }

        @Override
        public int getDamage() {
            return 0;
        }
    }
}
