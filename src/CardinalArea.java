import whale.util.Coordinate;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CardinalArea implements Area {
    private int distance;
    private Coordinate basePosition;

    public void setBasePosition(Coordinate basePosition) {
        this.basePosition = basePosition;
    }

    @Override
    public Coordinate validPosition(Coordinate pos) {
        basePosition = pos;
        Random rng = new Random();

        Coordinate result = new Coordinate();
        boolean horizontal = rng.nextBoolean();
        if (horizontal) {
            result.setRow(basePosition.row() + rng.nextInt(distance * 2) - distance);
            result.setColumn(basePosition.column());
        } else {
            result.setColumn(basePosition.column() + rng.nextInt(distance * 2) - distance);
            result.setRow(basePosition.row());
        }
        return result;
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
