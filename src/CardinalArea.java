import whale.util.Coordinate;

import java.util.ArrayList;
import java.util.List;

public class CardinalArea implements Area {
    private int distance;
    private Coordinate basePosition;

    public void setBasePosition(Coordinate basePosition) {
        this.basePosition = basePosition;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    @Override
    public List<Coordinate> getCells() {
        List<Coordinate> result = new ArrayList<>();
        for (int i = -distance; i <= distance; i++) {
            if (i != 0) {
                result.add(new Coordinate(basePosition.row() + i, basePosition.column()));
                result.add(new Coordinate(basePosition.row(), basePosition.column() + i));
            }
        }
        return result;
    }
}
