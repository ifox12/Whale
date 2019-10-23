package whale.map;

import java.awt.*;

public class MapCell {
    public char symbol;
    Color bgColor = Color.BLACK;
    Color fgColor = Color.WHITE;

    public MapCell(char symbol) {
        this.symbol = symbol;
    }
}
