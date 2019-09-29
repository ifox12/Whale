public class Trap implements ITrap {
    private Coordinate position;
    private TrapType type;
    private TrapTrigger trigger;
    private Gap gapToTrigger;

    Trap(Coordinate position, TrapType type) {
        this.position = position;
        this.type = type;
        // TODO make GapType dependent of TrapType
        this.gapToTrigger = new DistanceGap();
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
        return gapToTrigger.validPosition(position);
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
