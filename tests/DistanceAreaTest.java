import org.junit.jupiter.api.Test;
import whale.util.Coordinate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class DistanceAreaTest {

    @Test
    void getCells_distance2And5by5_CorrectResultList() {
        DistanceArea testArea = new DistanceArea();
        testArea.setDistance(2);
        testArea.setBasePosition(new Coordinate(5, 5));

        List<Coordinate> result = testArea.getCells();

        List<Coordinate> expected = new ArrayList<>();
        for (int row = 3; row <= 7; row++) {
            for (int column = 3; column <= 7; column++) {
                expected.add(new Coordinate(row, column));
            }
        }

        Collections.sort(result);
        Collections.sort(expected);

        assertTrue(Arrays.deepEquals(expected.toArray(), result.toArray()));
    }
}
