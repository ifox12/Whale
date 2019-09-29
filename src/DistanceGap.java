import java.util.Random;

public class DistanceGap implements Gap {
    private int distance;

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
}
