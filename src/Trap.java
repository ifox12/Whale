import whale.util.Coordinate;

public class Trap implements ITrap {
    private Coordinate position;
    private TrapType type;
    private TrapTrigger trigger;

    Trap(Coordinate position, TrapType type) {
        this.position = position;
        this.type = type;
        this.type.triggerArea.setBasePosition(this.position);
    }

    @Override
    public Coordinate getPosition() {
        return position;
    }

    @Override
    public char getSymbol() {
        return '*';
    }

    public void connectTrapTrigger(Coordinate triggerPosition) {
        trigger = new TrapTrigger(triggerPosition);
    }

    @Override
    public Placeable getTrigger() {
        return trigger;
    }

    @Override
    public TrapType getType() {
        return type;
    }
}
