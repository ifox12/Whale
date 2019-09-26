import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.LinkedList;


public class GameManager implements ActionListener {
    private IMap map;
    private IPlayer player;
    private LinkedList<IItem> itemList;
    private ITrap trap;
    private Window window;
    private Blitter blitter;
    private Input input;
    private Timer timer;
    private int targetRow;
    private int targetColumn;
    private char[][] drawableRepresentation;

    GameManager() throws IOException, FontFormatException {
        map = new Map();
        player = new Player(new Coordinate(3, 3));
        itemList = new LinkedList<>();
        itemList.add(new Item(new Coordinate(2, 1)));
        trap = new Trap(new Coordinate(3,2));
        blitter = new Blitter();
        window = new Window();
        updateScreen();
        input = new Input();
        input.setUpInput(this, window);
        timer = new Timer(16, this);
        timer.start();
    }

    char[][] prepareMapForBlitting() {
        drawableRepresentation = map.getDrawableMap();
        for (IItem item : itemList) {
            addToDrawableMap(item);
        }
        if (trap != null) {
            addToDrawableMap(trap);
        }
        addToDrawableMap(player);
        return drawableRepresentation;
    }

    private void addToDrawableMap(Placeable placeable) {
        int row = placeable.getPosition().row();
        int column = placeable.getPosition().column();
        drawableRepresentation[row][column] = placeable.getSymbol();
    }

    private void updateScreen() {
        final char[][] drawableMap = prepareMapForBlitting();
        final Image surfaceToBlit = blitter.calculateScreen(drawableMap);
        window.setScreen(surfaceToBlit);
    }

    public static void main(String[] args) throws IOException, FontFormatException {
        new GameManager();
    }

    void moveLeftFor(int steps) {
        extractPlayerPosition();

        if (map.isCellEmpty(targetRow, targetColumn - steps))
            player.moveTo(targetRow, targetColumn - steps);
    }

    private void extractPlayerPosition() {
        targetRow = player.getPosition().row();
        targetColumn = player.getPosition().column();
    }

    void moveRightFor(int steps) {
        extractPlayerPosition();

        if (map.isCellEmpty(targetRow, targetColumn + steps))
            player.moveTo(targetRow, targetColumn + steps);
    }

    void moveUpFor(int steps) {
        extractPlayerPosition();

        if (map.isCellEmpty(targetRow - steps, targetColumn))
            player.moveTo(targetRow - steps, targetColumn);
    }

    void moveDownFor(int steps) {
        extractPlayerPosition();

        if (map.isCellEmpty(targetRow + steps, targetColumn))
            player.moveTo(targetRow + steps, targetColumn);
    }

    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == timer) {
            if (trap != null && player.getPosition().equals(trap.getPosition())) {
                blitter.setMessage("Trap sprung.");
                player.hit(trap.getDamage());
                trap = null;

            }
            player.is_dead();
            updateItemsAndInventory();
            updateScreen();
            window.repaint();
        }
    }

    private void updateItemsAndInventory() {
        for (IItem item : itemList) {
            if (player.getPosition().equals(item.getPosition())) {
                player.addToInventory(item);
                itemList.remove(item);
                blitter.setMessage("Item picked up.");
            }
        }
    }

    void setMap(IMap map) {
        this.map = map;
    }

    void setPlayer(IPlayer player) {
        this.player = player;
    }

    void setItems(LinkedList<IItem> items) {
        this.itemList = items;
    }

    void setTrap(ITrap trap) {
        this.trap = trap;
    }
}
