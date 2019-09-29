public class TrapTrigger implements Placeable {

    private final char symbol = 'O';
    private Coordinate position;

    TrapTrigger(Coordinate position) {
        this.position = position;
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
