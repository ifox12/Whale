import whale.util.Coordinate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

// TODO make this testable!!!
public class GameManager implements ActionListener {
    private final int NUM_OF_ROWS = 12;
    private final int NUM_OF_COLUMNS = 12;
    private IMap map;
    private Window window;
    private Blitter blitter;
    private Input input;
    private Timer timer;
    private int targetRow;
    private int targetColumn;

    // TODO don't place player on traps or items or other things
    // TODO unify the code to find an empty map field to place things on
    // TODO have the map story the location of things (or the things as a placeable layer)
    GameManager() throws IOException, FontFormatException {
        map = new Map(NUM_OF_ROWS, NUM_OF_COLUMNS);

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

    private void updateScreen() {
        final Image surfaceToBlit = blitter.calculateScreen(map.getDrawableMap());
        window.setScreen(surfaceToBlit);
    }

    void moveLeftFor(int steps) {
        extractPlayerPosition();

        if (map.isAccessibleByPlayer(new Coordinate(targetRow, targetColumn - steps)))
            getPlayer().moveTo(targetRow, targetColumn - steps);
    }

    private void extractPlayerPosition() {
        targetRow = getPlayer().getPosition().row();
        targetColumn = getPlayer().getPosition().column();
    }

    void moveRightFor(int steps) {
        extractPlayerPosition();

        if (map.isAccessibleByPlayer(new Coordinate(targetRow, targetColumn + steps)))
            getPlayer().moveTo(targetRow, targetColumn + steps);
    }

    void moveUpFor(int steps) {
        extractPlayerPosition();

        if (map.isAccessibleByPlayer(new Coordinate(targetRow - steps, targetColumn)))
            getPlayer().moveTo(targetRow - steps, targetColumn);
    }

    void moveDownFor(int steps) {
        extractPlayerPosition();

        if (map.isAccessibleByPlayer(new Coordinate(targetRow + steps, targetColumn)))
            getPlayer().moveTo(targetRow + steps, targetColumn);
    }

    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == timer) {

            map.updateTraps();

            getPlayer().isDead();

            map.updateItemsAndInventory();

            blitter.setMessage(map.getMessage());

            updateScreen();
            window.repaint();
        }
    }


    public IPlayer getPlayer() {
        return ((Map) map).getPlayer();
    }
}
