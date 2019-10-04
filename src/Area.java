import whale.util.Coordinate;

import java.util.List;

interface Area {
    Coordinate validPosition(Coordinate basePosition);

    void setDistance(int distance);

    List<Coordinate> getCells();

    void setBasePosition(Coordinate position);
}
