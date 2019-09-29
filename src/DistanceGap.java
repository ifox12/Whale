import java.util.Random;

public class DistanceGap implements Gap {
    @Override
    public Coordinate validPosition(Coordinate basePosition) {
        Random rng = new Random();

        Coordinate result = new Coordinate();
        result.setRow(basePosition.row() + rng.nextInt(8) - 4);
        result.setColumn(basePosition.column() + rng.nextInt(8) - 4);
        return result;
    }
}
