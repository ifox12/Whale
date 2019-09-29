import java.util.Random;

public class Trap implements ITrap {
    private Coordinate position;
    private int damage;
    private TrapType type;
    private TrapTrigger trigger;

    Trap(Coordinate position, TrapType type) {
        this.position = position;
        damage = 10;
        this.type = type;
    }

    public int getDamage() {
        return damage;
    }

    @Override
    public Coordinate getPosition() {
        return position;
    }

    @Override
    public char getSymbol() {
        return '*';
    }

    public Coordinate trapTriggerLocationPossibility() {
        Random rng = new Random();
        Coordinate result = new Coordinate();
        //TODO make deviation (4) a field of the Trap Class
        result.setRow(position.row() + rng.nextInt(8) - 4);
        result.setColumn(position.column() + rng.nextInt(8) - 4);
        return result;
    }

    public void connectTrapTrigger(Coordinate triggerPosition) {
        trigger = new TrapTrigger(triggerPosition);
    }

    @Override
    public Placeable getTrigger() {
        return trigger;
    }
}
