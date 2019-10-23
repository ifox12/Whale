import org.junit.jupiter.api.Test;

class ProcGenTest {

    @Test
    void displayCurrentProcGenResult() {
        Map testMap = new Map(50, 100);
        testMap.setTerrain(testMap.procgenTerrain());

        for (char[] row : testMap.getDrawableTerrain()) {
            for (char column : row) {
                System.out.print(column);
            }
            System.out.print('\n');
        }
    }
}
