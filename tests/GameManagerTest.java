import whale.util.Coordinate;

import java.util.Arrays;

class GameManagerTest {

    // TODO make this work (or refactor GameManager to be testable

//    @Test
//    void drawableRepresentation_MapFilledWithDots_DotMapWithPlayerAndItem() throws IOException, FontFormatException {
//        GameManager testManager = new GameManager();
//        IMap fakeMap = new FakeMap();
//        fakeMap.setMap(dotMapData());
//        IPlayer fakePlayer = new FakePlayer();
//        LinkedList<IItem> fakeItemList = new LinkedList<>();
//        fakeItemList.add(new FakeItem(new Coordinate(2, 2)));
//        ITrap fakeTrap = new FakeTrap();
//        testManager.setMap(fakeMap);
//        testManager.setPlayer(fakePlayer);
//        testManager.setItems(fakeItemList);
//        testManager.setTrap(fakeTrap);
//
//        char[][] result = testManager.prepareMapForBlitting();
//
//        assertEquals('.', result[0][0]);
//        assertEquals('€', result[1][1]);
//        assertEquals('$', result[2][2]);
//        assertEquals('*', result[3][2]);
//        assertEquals('.', result[5][5]);
//    }

    private char[][] dotMapData() {
        char[][] result = new char[10][10];
        for (char[] row : result) {
            Arrays.fill(row, '.');
        }
        return result;
    }

    private class FakePlayer implements IPlayer {
        @Override
        public Coordinate getPosition() {
            return new Coordinate(1,1);
        }

        @Override
        public char getSymbol() {
            return '€';
        }

        @Override
        public void moveTo(int row, int column) {

        }

        @Override
        public void addToInventory(IItem item) {

        }

        @Override
        public boolean inventoryContains(Placeable item) {
            return false;
        }

        @Override
        public void hit(int damage) {

        }

        @Override
        public void isDead() {

        }
    }

    private class FakeMap implements IMap {
        @Override
        public boolean isCellEmpty(Coordinate coordinate) {
            return false;
        }

        @Override
        public char[][] getDrawableMap() {
            return new char[0][];
        }

        @Override
        public void updateItemsAndInventory() {
        }

        @Override
        public void updateTraps() {
        }

        @Override
        public String getMessage() {
            return null;
        }

        @Override
        public boolean isAccessibleByPlayer(Coordinate coordinate) {
            return false;
        }
    }
}
