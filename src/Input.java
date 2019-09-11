import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Input {
    // TODO testable
    void setUpInput(Player player, Component frame) {
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent event) {
                switch (event.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        player.moveLeftFor(1);
                        break;
                    case KeyEvent.VK_RIGHT:
                        player.moveRightFor(1);
                        break;
                    case KeyEvent.VK_UP:
                        player.moveUpFor(1);
                        break;
                    case KeyEvent.VK_DOWN:
                        player.moveDownFor(1);
                        break;
                }
            }
        });
    }
}
