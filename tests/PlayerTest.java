import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void moveTo_OneToTheRight_OneAddedToLocationX() {
        Player testPlayer = new Player(new Coordinate(0, 0));

        testPlayer.moveTo(1, 0);

        assertEquals(new Coordinate(1, 0), testPlayer.getPosition());
    }

    @Test
    void moveTo_OneUpAndRight_OneAddedToXAndYEach() {
        Player testPlayer = new Player(new Coordinate(0, 0));

        testPlayer.moveTo(1, 1);

        assertEquals(new Coordinate(1, 1), testPlayer.getPosition());
    }

    //TODO test moveDir methods in GameManager
//    @Test
//    void moveLeft_OneStep_LocationXReducedByOne() {
//        IMap stubMap = new FakeMap(true);
//        Player testPlayer = new Player(new Coordinate(0, 0), stubMap);
//
//        boolean result = GameManager.moveLeftFor(testPlayer, 1);
//
//        assertTrue(result);
//        assertEquals(new Coordinate(0, -1), testPlayer.getPosition());
//    }
//
//    @Test
//    void moveLeft_ZeroArgument_LocationStaysTheSame() {
//        IMap stubMap = new FakeMap(true);
//        Player testPlayer = new Player(new Coordinate(0, 0), stubMap);
//
//        boolean result = GameManager.moveLeftFor(testPlayer, 0);
//
//        assertTrue(result);
//        assertEquals(new Coordinate(0, 0), testPlayer.getPosition());
//    }
//
//    @Test
//    void moveRight_OneStep_AddedOneToLocationX() {
//        IMap stubMap = new FakeMap(true);
//        Player testPlayer = new Player(new Coordinate(0, 0), stubMap);
//
//        boolean result = GameManager.moveRightFor(1);
//
//        assertTrue(result);
//        assertEquals(new Coordinate(0, 1), testPlayer.getPosition());
//    }
//
//    @Test
//    void moveUp_OneStepOnInaccessibleCell_NoMove() {
//        IMap stubMap = new FakeMap(false);
//        Player testPlayer = new Player(new Coordinate(0, 0), stubMap);
//
//        boolean result = GameManager.moveUpFor(1);
//
//        assertFalse(result);
//        assertEquals(new Coordinate(0, 0), testPlayer.getPosition());
//    }
//
//    @Test
//    void moveDown_OneStep_SubtractOneFromLocationY() {
//        IMap stubMap = new FakeMap(true);
//        Player testPlayer = new Player(new Coordinate(0, 0), stubMap);
//
//        boolean result = GameManager.moveDownFor(1);
//
//        assertTrue(result);
//        assertEquals(new Coordinate(1, 0), testPlayer.getPosition());
//    }
//
//    @Test
//    void moveDown_3Steps_SubtractThreeFromLocationY() {
//        IMap stubMap = new FakeMap(true);
//        Player testPlayer = new Player(new Coordinate(0, 0), stubMap);
//
//        boolean result = GameManager.moveDownFor(3);
//
//        assertTrue(result);
//        assertEquals(new Coordinate(3, 0), testPlayer.getPosition());
//    }

    @Test
    void addToInventory_ItemInSamePlaceAsPlayer_InventoryContainsItem() {
        IMap stubMap = new FakeMap(true);
        Player testPlayer = new Player(new Coordinate(0, 0));
        IItem stubItem = new FakeItem(new Coordinate(0, 0));

        testPlayer.addToInventory(stubItem);

        assertTrue(testPlayer.inventoryContains(stubItem));
    }

    @Test
    void addToInventory_ItemNotInSamePlaceAsPlayer_ItemNotPickedUp() {
        IMap stubMap = new FakeMap(true);
        Player testPlayer = new Player(new Coordinate(0, 0));
        IItem stubItem = new FakeItem(new Coordinate(1, 1));

        testPlayer.addToInventory(stubItem);

        assertFalse(testPlayer.inventoryContains(stubItem));
    }


    private class FakeMap implements IMap {

        boolean empty;

        FakeMap(boolean empty) {
            this.empty = empty;
        }

        public boolean isCellEmpty(int x, int y) {
            return empty;
        }

    }
}
    class FakeItem implements IItem {
        Coordinate position;

        FakeItem(Coordinate position) {
            this.position = position;
        }

        @Override
        public Coordinate getPosition() {
            return position;
        }

        @Override
        public char getSymbol() {
            return '$';
        }
    }

