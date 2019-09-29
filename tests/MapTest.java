import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapTest {

    @Test
    void generatedMap_RunItWith4By4_ReturnsSquareRoom() {
        Map testMap = new Map();

        char[][] result = testMap.generatedMap(4, 4);

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
        Map testMap = new Map();

        char[][] result = testMap.generatedMap(5, 6);

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

    @Test
    void isCellEmpty_NegativeOutOfBounds_ReturnFalse() {
        Map testMap = new Map();

        boolean result = testMap.isCellEmpty(-1, -1);

        assertFalse(result);
    }


}
