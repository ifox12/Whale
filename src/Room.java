import whale.util.Coordinate;
import whale.util.JavaRNG;
import whale.util.RNG;

public class Room {
    int height;
    int width;
    int startRow;
    int startColumn;
    RNG rng = new JavaRNG();
    Coordinate[] borderTiles;
    int borderTilesIndex = 0;
    int topEdgeRow;
    int bottomEdgeRow;
    int leftEdgeColumn;
    int rightEdgeColumn;
    int topBorderRow;
    int bottomBorderRow;
    int leftBorderColumn;
    int rightBorderColumn;

    Room(int mapWidth, int mapHeight) {
        this.width = rng.intInRange(5, 15);
        this.height = rng.intInRange(5, 15);
        this.startRow = rng.intInRange(1, mapHeight - height);
        this.startColumn = rng.intInRange(1, mapWidth - width);
        borderTiles = new Coordinate[width * 2 + height * 2];

        calculateConvenienceVars();
    }

    Coordinate getRandomBorderTile() {
        return borderTiles[rng.intInRange(0, borderTiles.length)];
    }

    void findBorderTiles() {
        for (int row = topEdgeRow; row <= bottomEdgeRow; row++) {
            borderTiles[borderTilesIndex++] = new Coordinate(row, leftBorderColumn);
            borderTiles[borderTilesIndex++] = new Coordinate(row, rightBorderColumn);
        }
        for (int column = leftEdgeColumn; column <= rightEdgeColumn; column++) {
            borderTiles[borderTilesIndex++] = new Coordinate(topBorderRow, column);
            borderTiles[borderTilesIndex++] = new Coordinate(bottomBorderRow, column);
        }
    }

    void calculateConvenienceVars() {
        this.topEdgeRow = startRow;
        this.bottomEdgeRow = startRow + height - 1;
        this.leftEdgeColumn = startColumn;
        this.rightEdgeColumn = startColumn + width - 1;
        this.topBorderRow = topEdgeRow - 1;
        this.bottomBorderRow = bottomEdgeRow + 1;
        this.leftBorderColumn = leftEdgeColumn - 1;
        this.rightBorderColumn = rightEdgeColumn + 1;
    }
}
