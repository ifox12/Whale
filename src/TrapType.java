enum TrapType {
    Spike(10, new NoGap(), 0),
    SpikedBoard(40, new DistanceGap(), 3),
    Dart(10, new CardinalGap(), 4);

    private int damage;
    Gap gapToTrigger;

    TrapType(int damage, Gap gappingType, int gappingDistance) {
        gapToTrigger = gappingType;
        gapToTrigger.setDistance(gappingDistance);
        this.damage = damage;
    }

    int getDamage() {
        return damage;
    }
}
