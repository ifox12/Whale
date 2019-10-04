import whale.util.Coordinate;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DistanceArea implements Area {
    private int distance;
    private Coordinate basePosition;

    @Override
    public Coordinate validPosition(Coordinate basePosition) {
        Random rng = new Random();

        Coordinate result = new Coordinate();
        result.setRow(basePosition.row() + rng.nextInt(distance * 2) - distance);
        result.setColumn(basePosition.column() + rng.nextInt(distance * 2) - distance);
        return result;
    }

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
