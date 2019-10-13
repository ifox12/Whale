import whale.util.Coordinate;

public class Trap implements ITrap {
    private Coordinate position;
    private TrapType type;
    private TrapTrigger trigger;

    private Trap(Coordinate position, TrapType type) {
        this.position = position;
        this.type = type;
        this.type.triggerArea.setBasePosition(this.position);
    }

    static ITrap makeTrap(Map map) {
        Coordinate targetCoordinate = map.findRandomEmptyCell(new NoArea());
        TrapType targetType = TrapType.returnRandom();

        ITrap trap = new Trap(targetCoordinate, targetType);

        // TODO Is placing the trigger on the same cell as the trap disabled?
        Coordinate possibleTriggerLocation = map.findRandomEmptyCell(targetType.triggerArea);
        trap.connectTrapTrigger(possibleTriggerLocation);

        return trap;
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
