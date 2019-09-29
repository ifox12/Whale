interface ITrap extends Placeable {
    int getDamage();

    Coordinate trapTriggerLocationPossibility();

    void connectTrapTrigger(Coordinate triggerPosition);

    Placeable getTrigger();
}
