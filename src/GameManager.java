import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


public class GameManager implements ActionListener {
    private Map map;
    private Player player;
    private Item item;
    private Window window;
    private Blitter blitter;
    private Input input;
    private Timer timer;

    GameManager() throws IOException, FontFormatException {
        setMap(new Map());
        setPlayer(new Player(new Coordinate(3, 3), map));
        setItem(new Item(2, 1));
        setBlitter(new Blitter());
        Image screen = blitter.calculateScreen(map, player, item);
        window = new Window(screen);
        input = new Input();
        input.setUpInput(player, window);
        timer = new Timer(16, this);
        timer.start();
    }

    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == timer) {
            try {
                window.setScreen(blitter.calculateScreen(map, player, item));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (FontFormatException e) {
                e.printStackTrace();
            }
            window.repaint();
        }
    }


    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public static void main(String[] args) throws IOException, FontFormatException {
        new GameManager();
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Blitter getBlitter() {
        return blitter;
    }

    public void setBlitter(Blitter blitter) {
        this.blitter = blitter;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
