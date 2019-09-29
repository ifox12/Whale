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
    private ITrap trap2;
    private Window window;
    private Blitter blitter;
    private Input input;
    private Timer timer;
    private int targetRow;
    private int targetColumn;
    private char[][] drawableRepresentation;
    private final int NUM_OF_ROWS = 12;
    private final int NUM_OF_COLUMNS = 12;
    private LinkedList<IItem> items;

    GameManager() throws IOException, FontFormatException {
        map = new Map(NUM_OF_ROWS, NUM_OF_COLUMNS);
        player = new Player(new Coordinate(3, 3));
        itemList = new LinkedList<>();
        itemList.add(new Item(new Coordinate(2, 1)));
        try {
            trap = new Trap(new Coordinate(3,2), new TrapType("spike"));
            trap2 = new Trap(new Coordinate(6, 5), new TrapType("spiked_board"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        Coordinate possibleTriggerLocation = trap.trapTriggerLocationPossibility();
        while (!map.isCellEmpty(possibleTriggerLocation)) {
            possibleTriggerLocation = trap.trapTriggerLocationPossibility();
        }
        trap.connectTrapTrigger(possibleTriggerLocation);

        possibleTriggerLocation = trap2.trapTriggerLocationPossibility();
        while (!map.isCellEmpty(possibleTriggerLocation)) {
            possibleTriggerLocation = trap2.trapTriggerLocationPossibility();
        }
        trap2.connectTrapTrigger(possibleTriggerLocation);

        blitter = new Blitter(NUM_OF_ROWS, NUM_OF_COLUMNS);
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
            addToDrawableMap(trap.getTrigger());
        }
        if (trap2 != null) {
            addToDrawableMap(trap2);
            addToDrawableMap(trap2.getTrigger());
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
            if (trap != null && player.getPosition().equals(trap.getTrigger().getPosition())) {
                blitter.setMessage("Trap sprung.");
                player.hit(trap.getType().getDamage());
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

    public void setMap(IMap map) {
        this.map = map;
    }

    public void setPlayer(IPlayer player) {
        this.player = player;
    }

    public void setItems(LinkedList<IItem> items) {
        this.items = items;
    }

    public void setTrap(ITrap trap) {
        this.trap = trap;
    }
}
