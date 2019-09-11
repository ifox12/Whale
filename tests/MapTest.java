import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MapTest {

    private char[][] dotMapData() {
        // TODO there's some array filling function in java.util or sth
        char[][] result = new char[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                result[i][j] = '.';
            }
        }
        return result;
    }

    @Test
    void drawableRepresentation_MapFilledWithDots_Returns2DCharArrayWithDots() {
        Map testMap = new Map();
        testMap.setMap(dotMapData());

        char[][] result = testMap.drawableRepresentation();

        assertEquals('.', result[0][0]);
        assertEquals('.', result[5][5]);
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
