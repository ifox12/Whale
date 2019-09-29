import java.util.Random;

public class CardinalGap implements Gap {
    private int distance;

    @Override
    public Coordinate validPosition(Coordinate basePosition) {
        Random rng = new Random();

        Coordinate result = new Coordinate();
        if (rng.nextBoolean()) {
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
}
