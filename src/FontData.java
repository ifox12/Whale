import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

class FontData {
    private final String fontFileName;
    Font font;

    int fontWidth;
    int fontHeight;
    int fontAscent;

    FontData(String fontFileName) throws IOException, FontFormatException {
        this.fontFileName = fontFileName;
        loadFont();
        getFontMetrics();
    }

    // TODO test for given Font
    private void getFontMetrics() {
        BufferedImage tmpImage = new BufferedImage(5, 5, BufferedImage.TYPE_INT_RGB);
        Graphics2D tmpGraphics = tmpImage.createGraphics();
        tmpGraphics.setFont(font);
        FontMetrics fm = tmpGraphics.getFontMetrics();
        // TODO this returns variable widths even for monospaced fonts
        // tried:
        // getMaxAdvance() and with the same result (too wide) getMaxCharBounds(tmpGraphics).getWidth()
        // getWidths()
        fontWidth = fm.charWidth('@');
        fontHeight = fm.getHeight();
        fontAscent = fm.getAscent();
    }

    // TODO Put Font code in own class?
    // TODO Error handling and testing it
    private void loadFont() throws FontFormatException, IOException {
        font = java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT, new File(fontFileName)).deriveFont(18f);
    }
}
