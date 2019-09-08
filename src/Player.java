class Player {
    private IMap map;

    int y;
    int x;

    Player(IMap map) {
        this.map = map;
        this.x = 0;
        this.y = 0;
    }

    boolean moveTo(int x, int y) {
        if (map.isCellEmpty(x, y)) {
            this.x = x;
            this.y = y;
            return true;
        } else {
            return false;
        }
    }

    boolean moveLeftFor(int steps) {
        return moveTo(this.x - steps, this.y);
    }

    boolean moveRightFor(int steps) {
        return moveTo(this.x + steps, this.y);
    }

    boolean moveUpFor(int steps) {
        return moveTo(this.x, this.y + steps);
    }

    boolean moveDownFor(int steps) {
        return moveTo(this.x, this.y - steps);
    }
}
