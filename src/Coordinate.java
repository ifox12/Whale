public class Coordinate {
    private int row;
    private int column;

    Coordinate() {
        row = 0;
        column = 0;
    }

    Coordinate(int row, int column) {
        this.row = row;
        this.column = column;
    }

    int row() {
        return row;
    }

    int column() {
        return column;
    }

    void setRow(int row) {
        this.row = row;
    }

    void setColumn(int column) {
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
