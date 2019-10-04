enum TrapType {
    Spike(10, new SingleCellArea(), 0),
    SpikedBoard(40, new DistanceArea(), 3),
    Dart(10, new CardinalArea(), 4);

    private int damage;
    Area triggerArea;

    TrapType(int damage, Area placementArea, int placementDistance) {
        triggerArea = placementArea;
        triggerArea.setDistance(placementDistance);
        this.damage = damage;
    }

    int getDamage() {
        return damage;
    }
}
