package whale.BSPTree;

import whale.util.Coordinate;

import java.util.ArrayList;
import java.util.List;

public class Generator {

    static final int ROWS = 12;
    static final int COLUMNS = 12;
    static List<List<Character>> map = new ArrayList<>();

    public static List<List<Character>> generate() {
        fillMap('.');

        printMap();

        TreeNode root = new TreeNode(null);
        root.data = new MapPart(new Coordinate(0, 0), new Coordinate(ROWS, COLUMNS));

        TreeNode currentNode = root;

//        for (int i = 0; i< 4; i++) {
        currentNode.split();
//        }


        for (int i = root.left.data.origin.row(); i < root.left.data.size.row(); i++) {
            for (int j = root.left.data.origin.column(); j < root.left.data.size.column(); j++) {
                map.get(i).set(j, 'A');
            }
        }

        printMap();

        return map;
    }

    private static void fillMap(final char fillCharacter) {

        for (int row = 0; row < ROWS; row++) {
            map.add(new ArrayList<>());
            for (int column = 0; column < COLUMNS; column++) {
                map.get(row).add(fillCharacter);
            }
        }
    }

    private static void printMap() {
        for (var row : map) {
            for (var column : row) {
                System.out.print(column);
            }
            System.out.print('\n');
        }
    }
}
