import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Blitter {
    private BufferedImage img;
    private Graphics2D gfx;
    private FontData fontData;
    private final int NUM_OF_ROWS = 6;
    private final int NUM_OF_COLUMNS = 6;

    public void setMessage(String message) {
        this.message = message;
    }

    private String message = "";

    Blitter() throws IOException, FontFormatException {
        setFontData(new FontData("DejaVuSansMono.ttf"));
    }

    Image calculateScreen(char[][] drawableMap) {
        final int cellWidth = getFontData().fontWidth;
        final int cellHeight = getFontData().fontHeight;

        BufferedImage screen = new BufferedImage(cellWidth * NUM_OF_COLUMNS, cellHeight * (NUM_OF_ROWS + 1), BufferedImage.TYPE_INT_RGB);
        Graphics2D screenGfx = (Graphics2D) screen.getGraphics();

        screenGfx.setColor(Color.WHITE);
        screenGfx.drawString(message, 0, getFontData().fontAscent);

        for (int i = 0; i < drawableMap.length; i++) {
            for (int j = 0; j < drawableMap[i].length; j++) {
                screenGfx.drawImage(makeGlyph(drawableMap[i][j]), cellWidth * j, cellHeight + cellHeight * i, null);
            }
        }

        return screen;
    }


    void setUpGraphicsContext() {
        setImg(new BufferedImage(getFontData().fontWidth, getFontData().fontHeight, BufferedImage.TYPE_INT_RGB));
        setGfx(getImg().createGraphics());
        getGfx().setFont(getFontData().font);
    }

    Image makeGlyph(char glyph) {
        setUpGraphicsContext();

        drawGlyphBackground();
        drawGlyphForeground(glyph);

        return getImg();

    }

    // TODO Test pixel values
    void drawGlyphBackground() {
        getGfx().setColor(Color.BLACK);
        getGfx().clearRect(0, 0, getFontData().fontWidth, getFontData().fontHeight);
    }

    // TODO Test pixel values
    void drawGlyphForeground(char glyph) {
        getGfx().setColor(Color.WHITE);
        getGfx().drawString(String.valueOf(glyph), 0, getFontData().fontAscent);
    }


    public BufferedImage getImg() {
        return img;
    }

    public void setImg(BufferedImage img) {
        this.img = img;
    }

    public Graphics2D getGfx() {
        return gfx;
    }

    public void setGfx(Graphics2D gfx) {
        this.gfx = gfx;
    }

    public FontData getFontData() {
        return fontData;
    }

    public void setFontData(FontData fontData) {
        this.fontData = fontData;
    }

}
