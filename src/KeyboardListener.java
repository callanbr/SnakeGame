import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyboardListener extends KeyAdapter {

    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case 39: // Right
                if (SnakeController.snakeDirection != 2) {
                    SnakeController.snakeDirection = 1;
                }
                break;
            case 38: // Up
                if (SnakeController.snakeDirection != 4) {
                    SnakeController.snakeDirection = 3;
                }
                break;
            case 37: // Left
                if (SnakeController.snakeDirection != 1) {
                    SnakeController.snakeDirection = 2;
                }
                break;
            case 40: // Down
                if (SnakeController.snakeDirection != 3) {
                    SnakeController.snakeDirection = 4;
                }
                break;
            default:
                break;
        }
    }
}
