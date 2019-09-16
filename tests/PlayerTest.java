import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void moveTo_OneToTheRight_OneAddedToLocationX() {
        IMap stubMap = new FakeMap(true);
        Player testPlayer = new Player(new Coordinate(0, 0), stubMap);

        boolean result = testPlayer.moveTo(1, 0);

        assertTrue(result);
        assertEquals(new Coordinate(1, 0), testPlayer.getPosition());
    }

    @Test
    void moveTo_OneUpAndRight_OneAddedToXAndYEach() {
        IMap stubMap = new FakeMap(true);
        Player testPlayer = new Player(new Coordinate(0, 0), stubMap);

        boolean result = testPlayer.moveTo(1, 1);

        assertTrue(result);
        assertEquals(new Coordinate(1, 1), testPlayer.getPosition());
    }

    @Test
    void moveLeft_OneStep_LocationXReducedByOne() {
        IMap stubMap = new FakeMap(true);
        Player testPlayer = new Player(new Coordinate(0, 0), stubMap);

        testPlayer.moveLeftFor(1);

        assertEquals(0, testPlayer.position.row);
        assertEquals(-1, testPlayer.position.column);
    }

    @Test
    void moveLeft_ZeroArgument_LocationStaysTheSame() {
        IMap stubMap = new FakeMap(true);
        Player testPlayer = new Player(new Coordinate(0, 0), stubMap);

        testPlayer.moveLeftFor(0);

        assertEquals(0, testPlayer.position.row);
        assertEquals(0, testPlayer.position.column);
    }

    @Test
    void moveRight_OneStep_AddedOneToLocationX() {
        IMap stubMap = new FakeMap(true);
        Player testPlayer = new Player(new Coordinate(0, 0), stubMap);

        testPlayer.moveRightFor(1);

        assertEquals(0, testPlayer.position.row);
        assertEquals(1, testPlayer.position.column);
    }

    @Test
    void moveUp_OneStepOnInaccessibleCell_NoMove() {
        IMap stubMap = new FakeMap(false);
        Player testPlayer = new Player(new Coordinate(0, 0), stubMap);

        boolean result = testPlayer.moveUpFor(1);

        assertFalse(result);
        assertEquals(0, testPlayer.position.row);
        assertEquals(0, testPlayer.position.column);

    }

    @Test
    void moveDown_OneStep_SubtractOneFromLocationY() {
        IMap stubMap = new FakeMap(true);
        Player testPlayer = new Player(new Coordinate(0, 0), stubMap);

        testPlayer.moveDownFor(1);

        assertEquals(1, testPlayer.position.row);
        assertEquals(0, testPlayer.position.column);
    }

    @Test
    void moveDown_3Steps_SubtractThreeFromLocationY() {
        IMap stubMap = new FakeMap(true);
        Player testPlayer = new Player(new Coordinate(0, 0), stubMap);

        testPlayer.moveDownFor(3);

        assertEquals(3, testPlayer.position.row);
        assertEquals(0, testPlayer.position.column);
    }

    @Test
    void moveTo_InaccessibleTile_ReturnsFalseAndLocationUnchanged() {
        IMap stubMap = new FakeMap(false);
        Player testPlayer = new Player(new Coordinate(0, 0), stubMap);

        boolean result = testPlayer.moveTo(1, 1);

        assertFalse(result);
        assertEquals(0, testPlayer.position.row);
        assertEquals(0, testPlayer.position.column);

    }

    class FakeMap implements IMap {

        boolean empty;

        FakeMap(boolean empty) {
            this.empty = empty;
        }

        public boolean isCellEmpty(int x, int y) {
            return empty;
        }

        @Override
        public char[][] drawableRepresentation() {
            return new char[0][0];
        }
    }

}
