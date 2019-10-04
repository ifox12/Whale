import org.junit.jupiter.api.Test;
import whale.util.Coordinate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class SingleCellAreaTest {
    @Test
    void getCells_Distance2And5By5_ListWithOnlyOneCorrectCell() {
        SingleCellArea testArea = new SingleCellArea();
        testArea.setBasePosition(new Coordinate(5, 5));

        List<Coordinate> result = testArea.getCells();

        List<Coordinate> expected = new ArrayList<>();
        expected.add(new Coordinate(5, 5));

        assertTrue(Arrays.deepEquals(expected.toArray(), result.toArray()));
    }
}
