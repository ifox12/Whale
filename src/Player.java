class Player {
    private IMap map;

    Coordinate position;

    Player(Coordinate position, IMap map) {
        this.map = map;
        this.position = position;
    }

    boolean moveTo(int x, int y) {
        if (map.isCellEmpty(x, y)) {
            position.x = x;
            position.y = y;
            return true;
        } else {
            return false;
        }
    }

    boolean moveLeftFor(int steps) {
        return moveTo(position.x - steps, position.y);
    }

    boolean moveRightFor(int steps) {
        return moveTo(position.x + steps, position.y);
    }

    boolean moveUpFor(int steps) {
        return moveTo(position.x, position.y + steps);
    }

    boolean moveDownFor(int steps) {
        return moveTo(position.x, position.y - steps);
    }

    Coordinate getPosition() {
        return position;
    }
}
