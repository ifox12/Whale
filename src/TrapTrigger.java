import whale.util.Coordinate;

public class TrapTrigger implements Placeable {

    private final char symbol = 'O';
    private Coordinate position;
    private int weight;

    TrapTrigger(Coordinate position) {
        this.position = position;
        this.weight = 10;
    }

    @Override
    public Coordinate getPosition() {
        return position;
    }

    @Override
    public char getSymbol() {
        return symbol;
    }
}
