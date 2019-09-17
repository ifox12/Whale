import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;

public class Blitter {
    private BufferedImage img;
    private Graphics2D gfx;
    private FontData fontData;
    private char[][] drawableMap;


    Image calculateScreen(Map map, Player player, LinkedList<Item> itemList) throws IOException, FontFormatException {
        setFontData(new FontData("DejaVuSansMono.ttf"));
        populateMap(map, player, itemList);
        return drawMap();
    }

    // TODO Put that into map or some superior structure to hold the different map levels
    void populateMap(Map map, Player player, LinkedList<Item> itemList) {
        setDrawableMap(map.drawableRepresentation());
        for (Item item : itemList) {
            addToDrawableMap(item);
        }
        addToDrawableMap(player);
    }

    private void addToDrawableMap(Placeable placeable) {
        int row = placeable.getPosition().row();
        int column = placeable.getPosition().column();
        getDrawableMap()[row][column] = placeable.getSymbol();
    }

    Image drawMap() {
        BufferedImage screen = new BufferedImage(getFontData().fontWidth * 6, getFontData().fontHeight * 6, BufferedImage.TYPE_INT_RGB);
        Graphics2D screenGfx = (Graphics2D) screen.getGraphics();


        for (int i = 0; i < getDrawableMap().length; i++) {
            for (int j = 0; j < getDrawableMap()[i].length; j++) {
                screenGfx.drawImage(makeGlyph(getDrawableMap()[i][j]), getFontData().fontWidth * j, getFontData().fontHeight * i, null);
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

    public char[][] getDrawableMap() {
        return drawableMap;
    }

    public void setDrawableMap(char[][] drawableMap) {
        this.drawableMap = drawableMap;
    }
}
