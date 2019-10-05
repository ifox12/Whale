import whale.util.Coordinate;

import java.util.ArrayList;
import java.util.List;

public class NoArea implements Area {
    @Override
    public void setDistance(int distance) {

    }

    @Override
    public List<Coordinate> getCells() {
        List<Coordinate> emptyList = new ArrayList<>();
        return emptyList;
    }

    @Override
    public void setBasePosition(Coordinate position) {

    }
}
