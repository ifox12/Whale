import whale.util.Coordinate;

interface Gap {
    Coordinate validPosition(Coordinate basePosition);

    void setDistance(int distance);
}
