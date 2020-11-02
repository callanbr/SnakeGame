import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyboardListener extends KeyAdapter {

    public void kyePressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case 39: // Right
                // if already going right => do nothing.
                // if not going right => start going right
                break;
            case 38: // Up
                break;
            case 37: // Left
                break;
            case 40: // Down
                break;
            default:
                break;
        }
    }
}
