import whale.util.Coordinate;

public class SpikeTrap implements ITrap {

    private Coordinate position;
    private char symbol = '*';
    private Placeable trigger;
    private final int damage = 40;
    private final Area triggerArea = new DistanceArea();
    private final int areaRange = 3;

    SpikeTrap(Coordinate position) {
        this.position = position;
        this.triggerArea.setBasePosition(this.position);
        this.triggerArea.setDistance(areaRange);
    }

    @Override
    public void connectTrapTrigger(Coordinate triggerPosition) {
        trigger = new TrapTrigger(triggerPosition);
    }

    @Override
    public Placeable getTrigger() {
        return trigger;
    }

    @Override
    public Area getTriggerArea() {
        return triggerArea;
    }

    @Override
    public int getDamage() {
        return damage;
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

