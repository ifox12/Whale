class TrapType {

    private int damage;

    TrapType(String name) throws Exception {
        if (name.equals("spike")) {
            damage = 10;
        } else if (name.equals("spiked_board")) {
            damage = 40;
        } else {
            throw new Exception("unknown trap type");
        }
    }

    int getDamage() {
        return damage;
    }
}
