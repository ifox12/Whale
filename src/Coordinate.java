public class Coordinate {
    int row;
    int column;

    Coordinate(int row, int column) {
        this.row = row;
        this.column = column;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (other.getClass() != getClass()) {
            return false;
        }
        if (row != ((Coordinate) other).row) {
            return false;
        }
        if (column != ((Coordinate) other).column) {
            return false;
        }
        return true;
    }
}
