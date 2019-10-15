import whale.util.Coordinate;

interface ITrap extends Placeable {
    void connectTrapTrigger(Coordinate triggerPosition);

    Placeable getTrigger();

    Area getTriggerArea();

    int getDamage();
}
