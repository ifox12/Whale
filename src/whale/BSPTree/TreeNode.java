package whale.BSPTree;

import whale.util.Coordinate;
import whale.util.JavaRNG;
import whale.util.RNG;

class TreeNode {
    MapPart data;
    TreeNode left = null;
    TreeNode right = null;
    TreeNode parent;
    RNG rng = new JavaRNG();

    TreeNode(TreeNode parent) {
        this.parent = parent;
    }

    public void split() {
        int direction = rng.intInRange(0, 2); // 0: horizontal, 1: vertical
        int splitLine;
        if (direction == 0) {
            splitLine = rng.intInRange(data.origin.row() + data.size.row() / 4, data.size.row() / 4 * 3 + data.origin.row());

            left = new TreeNode(this);
            left.data = new MapPart(this.data.origin, new Coordinate(splitLine, this.data.size.column()));

            right = new TreeNode(this);
            right.data = new MapPart(new Coordinate(splitLine + 1, this.data.origin.column()), new Coordinate(this.data.size.row() - splitLine, this.data.size.column()));
        } else {
            splitLine = rng.intInRange(data.origin.column() + data.size.column() / 4, data.size.column() / 4 * 3 + data.origin.column());

            left = new TreeNode(this);
            left.data = new MapPart(this.data.origin, new Coordinate(this.data.size.row(), splitLine));

            right = new TreeNode(this);
            right.data = new MapPart(new Coordinate(this.data.origin.row(), splitLine + 1), new Coordinate(this.data.size.row(), this.data.size.column() - splitLine));
        }
//        if (left.data.size.row() >= 10 && left.data.size.column() >= 10) {
//            left.split();
//        }
//        if (right.data.size.row() >= 10 && right.data.size.column() >= 10) {
//            right.split();
//        }

    }
}
