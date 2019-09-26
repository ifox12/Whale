public class Trap implements ITrap {
    Coordinate position;
    int damage;

    Trap(Coordinate position) {
        this.position = position;
        damage = 10;
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
