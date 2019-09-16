import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    private Image screen;
    private DrawingPanel panel;

    Window(Image screen) {
        super("Whale");
        panel = new DrawingPanel(null);
        this.setScreen(screen);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(200, 200);
        setLocationRelativeTo(null);
        panel.setScreen(screen);
        add(panel);

        setVisible(true);
    }

    public void setScreen(Image screen) {
        this.screen = screen;
        panel.setScreen(screen);
    }
}

class DrawingPanel extends JPanel {

    private Image screen;

    DrawingPanel(Image screen) {
        this.setScreen(screen);
    }

    public void paint(Graphics gfx) {
        super.paint(gfx);
        gfx.drawImage(screen, 0, 0, null);
    }

    public void setScreen(Image screen) {
        this.screen = screen;
    }
}