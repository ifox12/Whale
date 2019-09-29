class TrapType {

    private int damage;
    Gap gapToTrigger;


    TrapType(String name) throws Exception {
        if (name.equals("spike")) {
            damage = 10;
            this.gapToTrigger = new NoGap();
        } else if (name.equals("spiked_board")) {
            damage = 40;
            this.gapToTrigger = new DistanceGap();
        } else if (name.equals("dart")) {
            damage = 10;
            this.gapToTrigger = new CardinalGap();
        } else {
            throw new Exception("unknown trap type");
        }
    }

    int getDamage() {
        return damage;
    }
}
