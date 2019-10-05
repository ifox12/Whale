import whale.util.Coordinate;

interface ITrap extends Placeable {
    void connectTrapTrigger(Coordinate triggerPosition);

    Placeable getTrigger();

    TrapType getType();
}
