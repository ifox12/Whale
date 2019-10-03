package whale.util;

public class Coordinate {
    private int row;
    private int column;

    public Coordinate() {
        row = 0;
        column = 0;
    }

    public Coordinate(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int row() {
        return row;
    }

    public int column() {
        return column;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int column) {
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

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }
}
