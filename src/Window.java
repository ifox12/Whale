import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    private Image screen;

    Window(Image screen) {
        super("Whale");
        this.setScreen(screen);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(200, 200);
        setLocationRelativeTo(null);
//
//        JLabel content = new JLabel(new ImageIcon(screen));

//        add(content, BorderLayout.CENTER);
//        pack();
        setVisible(true);
    }

    public void paint(Graphics gfx) {
        gfx.drawImage(getScreen(), 0, 0, null);
    }

    public Image getScreen() {
        return screen;
    }

    public void setScreen(Image screen) {
        this.screen = screen;
    }
}
