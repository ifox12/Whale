class TrapType {


    private int damage;
    private int weight;

    TrapType(String name) throws Exception {
        if (name.equals("spike")) {
            damage = 10;
            this.weight = 1;
        } else if (name.equals("spiked_board")) {
            damage = 40;
            this.weight = 30;
        } else {
            throw new Exception("unknown trap type");
        }
    }
}
