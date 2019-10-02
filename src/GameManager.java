import whale.util.Coordinate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


public class GameManager implements ActionListener {
    private final int NUM_OF_ROWS = 12;
    private final int NUM_OF_COLUMNS = 12;
    private IMap map;
    private IPlayer player;
    private LinkedList<IItem> itemList;
    private ITrap trap;
    private ITrap trap2;
    private ITrap trap3;
    private List<ITrap> traps;
    private Window window;
    private Blitter blitter;
    private Input input;
    private Timer timer;
    private int targetRow;
    private int targetColumn;
    private char[][] drawableRepresentation;
    private LinkedList<IItem> items;

    GameManager() throws IOException, FontFormatException {
        map = new Map(NUM_OF_ROWS, NUM_OF_COLUMNS);
        player = new Player(new Coordinate(3, 3));
        itemList = new LinkedList<>();
        itemList.add(new Item(new Coordinate(2, 1)));

        traps = new LinkedList<>();
        traps.add(makeTrap(new Coordinate(3, 2), TrapType.Spike));
        traps.add(makeTrap(new Coordinate(6, 5), TrapType.SpikedBoard));
        traps.add(makeTrap(new Coordinate(9, 9), TrapType.Dart));

        blitter = new Blitter(NUM_OF_ROWS, NUM_OF_COLUMNS);
        window = new Window();
        updateScreen();
        input = new Input();
        input.setUpInput(this, window);
        timer = new Timer(16, this);
        timer.start();
    }

    public static void main(String[] args) throws IOException, FontFormatException {
        new GameManager();
    }

    private ITrap makeTrap(Coordinate position, TrapType type) {
        ITrap trap = new Trap(position, type);
        Coordinate possibleTriggerLocation = trap.trapTriggerLocationPossibility();
        while (!map.isCellEmpty(possibleTriggerLocation)) {
            possibleTriggerLocation = trap.trapTriggerLocationPossibility();
        }
        trap.connectTrapTrigger(possibleTriggerLocation);
        return trap;
    }

    char[][] prepareMapForBlitting() {
        drawableRepresentation = map.getDrawableMap();
        for (IItem item : itemList) {
            addToDrawableMap(item);
        }
        for (ITrap trap : traps) {
            addToDrawableMap(trap);
            addToDrawableMap(trap.getTrigger());
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

            updateTraps();

            player.is_dead();

            updateItemsAndInventory();

            updateScreen();
            window.repaint();
        }
    }

    private void updateTraps() {
        Iterator<ITrap> i = traps.iterator();
        ITrap trap;
        while (i.hasNext()) {
            trap = i.next();
            if (player.getPosition().equals(trap.getTrigger().getPosition())) {
                blitter.setMessage("Trap sprung: " + trap.getType());
                // TODO apply damage to entity on specific field, not just player (to have traps hit enemies/other traps etc)
                player.hit(trap.getType().getDamage());
                i.remove();
            }
        }
    }

    private void updateItemsAndInventory() {
        for (IItem item : itemList) {
            if (player.getPosition().equals(item.getPosition())) {
                player.addToInventory(item);
                // TODO Is the remove threadsafe?
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
