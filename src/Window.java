import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Window {
    private JFrame mainFrame;
    GameManager gameManager = new GameManager();
    private BufferedImage img;
    private Graphics2D gfx;
    private FontData fontData;
    private Player player;
    private char[][] drawableMap;


    private Window() throws IOException, FontFormatException {
        fontData = new FontData("DejaVuSansMono.ttf");
        gameManager.setMap(new Map());
        setPlayer(new Player(new Coordinate(3, 3), gameManager.getMap()));
        populateMap();
        setUpWindow();
        setUpInput();

        JLabel content = new JLabel(new ImageIcon(drawMap()));

        mainFrame.add(content, BorderLayout.CENTER);
        mainFrame.pack();
        mainFrame.setVisible(true);
    }

    private void populateMap() {
        drawableMap = gameManager.getMap().drawableRepresentation();
        drawableMap[getPlayer().getPosition().y][getPlayer().getPosition().x] = '@';
    }

    private Image drawMap() throws IOException, FontFormatException {
        BufferedImage screen = new BufferedImage(fontData.fontWidth * 6, fontData.fontHeight * 6, BufferedImage.TYPE_INT_RGB);
        Graphics2D screenGfx = (Graphics2D) screen.getGraphics();


        for (int i = 0; i < drawableMap.length; i++) {
            for (int j = 0; j < drawableMap[i].length; j++) {
                screenGfx.drawImage(makeGlyph(drawableMap[i][j]), fontData.fontWidth * j, fontData.fontHeight * i, null);
            }
        }

        return screen;
    }

    // TODO testable
    private void setUpInput() {
        mainFrame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent event) {
                switch (event.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        getPlayer().moveLeftFor(1);
                        break;
                    case KeyEvent.VK_RIGHT:
                        getPlayer().moveRightFor(1);
                        break;
                    case KeyEvent.VK_UP:
                        getPlayer().moveUpFor(1);
                        break;
                    case KeyEvent.VK_DOWN:
                        getPlayer().moveDownFor(1);
                        break;
                }
            }
        });
    }

    private void setUpWindow() {
        mainFrame = new JFrame("Whale");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(new BorderLayout());
        mainFrame.setSize(100, 100);
        mainFrame.setLocationRelativeTo(null);
    }

    private Image makeGlyph(char glyph) throws IOException, FontFormatException {
        setUpGraphicsContext();

        drawGlyphBackground();
        drawGlyphForeground(glyph);

        return img;

    }

    private void setUpGraphicsContext() {
        img = new BufferedImage(fontData.fontWidth, fontData.fontHeight, BufferedImage.TYPE_INT_RGB);
        gfx = img.createGraphics();
        gfx.setFont(fontData.font);
    }

    // TODO Test pixel values
    private void drawGlyphForeground(char glyph) {
        gfx.setColor(Color.WHITE);
        gfx.drawString(String.valueOf(glyph), 0, fontData.fontAscent);
    }

    // TODO Test pixel values
    private void drawGlyphBackground() {
        gfx.setColor(Color.BLACK);
        gfx.clearRect(0, 0, fontData.fontWidth, fontData.fontHeight);
    }

    public static void main(String[] args) throws IOException, FontFormatException {
        new Window();
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
