import java.awt.*;
import java.io.IOException;

public class GameManager {
    private Map map;
    private Player player;
    private Window window;
    private Blitter blitter;
    private Input input;

    GameManager() throws IOException, FontFormatException {
        setMap(new Map());
        setPlayer(new Player(new Coordinate(3, 3), map));
        setBlitter(new Blitter());
        Image screen = blitter.calculateScreen(map, player);
        window = new Window(screen);
        input = new Input();
        input.setUpInput(player, window.getFrame());
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
}
