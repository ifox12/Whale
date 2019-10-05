import whale.util.Coordinate;

import java.util.ArrayList;
import java.util.List;

public class DistanceArea implements Area {
    private int distance;
    private Coordinate basePosition;

    @Override
    public void setDistance(int distance) {
        this.distance = distance;
    }

    @Override
    public List<Coordinate> getCells() {
        List<Coordinate> result = new ArrayList<>();
        for (int row = -distance; row <= distance; row++) {
            for (int column = -distance; column <= distance; column++) {
                if (!(basePosition.row() == row && basePosition.column() == column)) {
                    result.add(new Coordinate(basePosition.row() + row, basePosition.column() + column));
                }
            }
        }
        return result;
    }

    public void setBasePosition(Coordinate basePosition) {
        this.basePosition = basePosition;
    }
}
