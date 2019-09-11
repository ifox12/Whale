import javax.swing.*;
import java.awt.*;

public class Window {
    private JFrame frame;

    Window(Image screen) {
        frame = new JFrame("Whale");
        getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getFrame().setLayout(new BorderLayout());
        getFrame().setSize(100, 100);
        getFrame().setLocationRelativeTo(null);

        JLabel content = new JLabel(new ImageIcon(screen));

        getFrame().add(content, BorderLayout.CENTER);
        getFrame().pack();
        getFrame().setVisible(true);
    }

    public JFrame getFrame() {
        return frame;
    }
}
