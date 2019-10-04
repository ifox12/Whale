import org.junit.jupiter.api.Test;
import whale.util.Coordinate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class CardinalAreaTest {
    @Test
    void getCells_distance2And5by5_CorrectResultList() {
        CardinalArea testArea = new CardinalArea();
        testArea.setDistance(2);
        testArea.setBasePosition(new Coordinate(5, 5));

        List<Coordinate> result = testArea.getCells();

        List<Coordinate> expected = new ArrayList<>();
        expected.add(new Coordinate(3, 5));
        expected.add(new Coordinate(4, 5));
        expected.add(new Coordinate(6, 5));
        expected.add(new Coordinate(7, 5));
        expected.add(new Coordinate(5, 3));
        expected.add(new Coordinate(5, 4));
        expected.add(new Coordinate(5, 6));
        expected.add(new Coordinate(5, 7));
        Collections.sort(result);
        Collections.sort(expected);

        assertTrue(Arrays.deepEquals(expected.toArray(), result.toArray()));
    }
}
