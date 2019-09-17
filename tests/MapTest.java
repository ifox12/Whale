import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class MapTest {

    @Test
    void drawableRepresentation_MapFilledWithDots_Returns2DCharArrayWithDots() {
        Map testMap = new Map();
        testMap.setMap(dotMapData());

        char[][] result = testMap.drawableRepresentation();

        assertEquals('.', result[0][0]);
        assertEquals('.', result[5][5]);
    }

    private char[][] dotMapData() {
        char[][] result = new char[10][10];
        for (char[] row : result) {
            Arrays.fill(row, '.');
        }
        return result;
    }

    @Test
    void drawableRepresentation_DefaultMap_Returns2DCharArrayWithDefaultMap() {
        Map testMap = new Map();

        char[][] result = testMap.drawableRepresentation();

        assertEquals('#', result[0][0]);
        assertEquals('#', result[5][5]);
        assertEquals('.', result[2][3]);
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

    // TODO this is not very clever, this expects a client class to first call isCellEmpty() before changePlayerPosition,
    //  better give Map the whole responsiblity or something like that
    @Test
    void changePlayerPosition_Scenario_ExpectedResult() {


    }
}
