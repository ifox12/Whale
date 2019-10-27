import whale.util.Coordinate;

import java.util.ArrayList;
import java.util.List;

public class NoArea implements Area {
    int rows;
    int columns;

    public NoArea(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
    }

    @Override
    public void setDistance(int distance) {

    }

    @Override
    public List<Coordinate> getCells() {
        List<Coordinate> wholeMap = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                wholeMap.add(new Coordinate(i, j));
            }
        }

        return wholeMap;
    }

    @Override
    public void setBasePosition(Coordinate position) {

    }
}
