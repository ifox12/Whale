import org.junit.jupiter.api.Test;
import whale.util.Coordinate;

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

    @Test
    void isCellEmpty_InaccessibleCell_ReturnFalse() {
        Map testMap = new Map(6, 6);

        boolean result = testMap.isCellEmpty(new Coordinate(0, 0));

        assertFalse(result);
    }

    @Test
    void isCellEmpty_EmptyCell_ReturnTrue() {
        Map testMap = new Map(6, 6);

        boolean result = testMap.isCellEmpty(new Coordinate(2, 1));

        assertTrue(result);
    }

    @Test
    void isCellEmpty_OutOfBounds_ReturnFalse() {
        Map testMap = new Map(6, 6);

        boolean result = testMap.isCellEmpty(new Coordinate(9999, 9999));

        assertFalse(result);
    }

    @Test
    void isCellEmpty_NegativeOutOfBounds_ReturnFalse() {
        Map testMap = new Map(6, 6);

        boolean result = testMap.isCellEmpty(new Coordinate(-1, -1));

        assertFalse(result);
    }


}
