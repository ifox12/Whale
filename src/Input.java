import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Input {
    // TODO testable
    void setUpInput(GameManager manager, Component frame) {
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent event) {
                switch (event.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        manager.moveLeftFor(1);
                        break;
                    case KeyEvent.VK_RIGHT:
                        manager.moveRightFor(1);
                        break;
                    case KeyEvent.VK_UP:
                        manager.moveUpFor(1);
                        break;
                    case KeyEvent.VK_DOWN:
                        manager.moveDownFor(1);
                        break;
                }
            }
        });
    }
}
