import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;


public class GameManager implements ActionListener {
    private Map map;
    private Player player;
    private LinkedList<Item> itemList;
    private Window window;
    private Blitter blitter;
    private Input input;
    private Timer timer;

    private GameManager() throws IOException, FontFormatException {
        map = new Map();
        player = new Player(new Coordinate(3, 3), map);
        itemList = new LinkedList<>();
        itemList.add(new Item(new Coordinate(2, 1)));
        blitter = new Blitter();
        window = new Window();
        updateScreen();
        input = new Input();
        input.setUpInput(player, window);
        timer = new Timer(16, this);
        timer.start();
    }

    private void updateScreen() throws IOException, FontFormatException {
        window.setScreen(blitter.calculateScreen(map, player, itemList));
    }

    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == timer) {
            try {
                itemList.removeIf(item -> player.getPosition().equals(item.getPosition()));
                updateScreen();
            } catch (IOException | FontFormatException e) {
                e.printStackTrace();
            }
            window.repaint();
        }
    }

    public static void main(String[] args) throws IOException, FontFormatException {
        new GameManager();
    }
}
