import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class Window {
    private JFrame mainFrame;
    private Font font;
    private int fontWidth;
    private int fontHeight;
    private int fontAscent;
    private BufferedImage img;
    private Graphics2D gfx;

    private Window() throws IOException, FontFormatException {
        setUpWindow();

        JLabel content = new JLabel(new ImageIcon(makeGlyph()));

        mainFrame.add(content);
        mainFrame.setVisible(true);
    }

    private void setUpWindow() {
        mainFrame = new JFrame("Whale");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(500,500);
        mainFrame.setLocationRelativeTo(null);
    }

    private Image makeGlyph() throws IOException, FontFormatException {
        loadFont();

        getFontMetrics();

        setUpGraphicsContext();

        drawGlyphBackground();
        drawGlyphForeground();

        return img;

    }

    private void setUpGraphicsContext() {
        img = new BufferedImage(fontWidth, fontHeight, BufferedImage.TYPE_INT_RGB);
        gfx = img.createGraphics();
    }

    // TODO Test pixel values
    private void drawGlyphForeground() {
        gfx.setColor(Color.WHITE);
        gfx.drawString("@", 0, fontAscent);
    }

    // TODO Test pixel values
    private void drawGlyphBackground() {
        gfx.setColor(Color.BLACK);
        gfx.clearRect(0, 0, fontWidth, fontHeight);
    }

    // TODO test for given Font
    private void getFontMetrics() {
        BufferedImage tmpImage = new BufferedImage(5, 5, BufferedImage.TYPE_INT_RGB);
        Graphics2D tmpGraphics = tmpImage.createGraphics();
        FontMetrics fm = tmpGraphics.getFontMetrics();
        fontWidth = fm.charWidth('@');
        fontHeight = fm.getHeight();
        fontAscent = fm.getAscent();
    }

    // TODO Put Font code in own class?
    // TODO Error handling and testing it
    private void loadFont() throws FontFormatException, IOException {
        font = Font.createFont(Font.TRUETYPE_FONT, new File("DejaVuSansMono.ttf"));
    }

    public static void main(String[] args) throws IOException, FontFormatException {
        new Window();
    }

}
