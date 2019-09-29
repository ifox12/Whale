import java.util.Random;

public class CardinalGap implements Gap {
    @Override
    public Coordinate validPosition(Coordinate basePosition) {
        Random rng = new Random();

        Coordinate result = new Coordinate();
        if (rng.nextBoolean()) {
            result.setRow(basePosition.row() + rng.nextInt(6) - 3);
            result.setColumn(basePosition.column());
        } else {
            result.setColumn(basePosition.column() + rng.nextInt(6) - 3);
            result.setRow(basePosition.row());
        }
        return result;
    }
}
