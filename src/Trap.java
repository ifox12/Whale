public class Trap implements ITrap {
    Coordinate position;
    int damage;
    TrapType type;

    Trap(Coordinate position, TrapType type) {
        this.position = position;
        damage = 10;
        this.type = type;
    }

    public int getDamage() {
        return damage;
    }

    @Override
    public Coordinate getPosition() {
        return position;
    }

    @Override
    public char getSymbol() {
        return '*';
    }
}
