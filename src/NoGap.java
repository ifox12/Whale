public class NoGap implements Gap {
    @Override
    public Coordinate validPosition(Coordinate basePosition) {
        return basePosition;
    }

    @Override
    public void setDistance(int distance) {
    }
}
