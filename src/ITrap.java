import whale.util.Coordinate;

interface ITrap extends Placeable {
    Coordinate trapTriggerLocationPossibility();

    void connectTrapTrigger(Coordinate triggerPosition);

    Placeable getTrigger();

    TrapType getType();
}
