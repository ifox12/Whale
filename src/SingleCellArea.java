import whale.util.Coordinate;

import java.util.ArrayList;
import java.util.List;

public class SingleCellArea implements Area {
    private Coordinate basePosition;

    @Override
    public void setDistance(int distance) {
    }

    @Override
    public List<Coordinate> getCells() {
        List<Coordinate> result = new ArrayList<>();
        result.add(basePosition);
        return result;
    }

    public void setBasePosition(Coordinate basePosition) {
        this.basePosition = basePosition;
    }
}
