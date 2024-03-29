import javax.swing.*;
import java.awt.*;

class Window extends JFrame {
    private Image screen;
    private DrawingPanel panel;

    Window() {
        super("Whale");
        panel = new DrawingPanel(screen);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(1000, 1000);
        setLocationRelativeTo(null);
        add(panel);

        setVisible(true);
    }

    void setScreen(Image screen) {
        this.screen = screen;
        panel.setScreen(screen);
    }
}

class DrawingPanel extends JPanel {

    private Image screen;

    DrawingPanel(Image screen) {
        this.setScreen(screen);
    }

    @Override
    public void paint(Graphics gfx) {
        super.paint(gfx);
        gfx.drawImage(screen, 0, 0, null);
    }

    void setScreen(Image screen) {
        this.screen = screen;
    }
}