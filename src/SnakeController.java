import java.util.ArrayList;

// Controlling the game logic
public class SnakeController extends Thread {
    ArrayList<ArrayList<DataOfSquare>> Squares = new ArrayList<ArrayList<DataOfSquare>>();
    XY foodPosition;
    XY headPosition;
    ArrayList<XY> positions = new ArrayList<XY>();
    int snakeSize = 3;
    long snakeSpeed = 80;
    public static int snakeDirection;

    SnakeController(XY pos) {
        Squares = Window.Grid;

        headPosition = new XY(pos.x, pos.y);
        snakeDirection = 1;

        XY newHeadPosition = new XY(headPosition.getX(), headPosition.getY());
        positions.add(newHeadPosition);

        foodPosition = new XY(Window.height - 1, Window.width - 1);
        positionFood(foodPosition);
    }
    // get threads

    // Create run method to start game.
    public void run() {
        while (true) {
            moveSnake(snakeDirection);
            moveSnakeColor();
            snakeTail();
            pauseSnake();
        }
    }

    private void pauseSnake() {
        try {
            sleep(snakeSpeed);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Check if snake eats food

    // Check if snake runs into self

    // What happens on stop? (game over)

    // position the food (new food)
    private void positionFood(XY foodPos) {
        Squares.get(foodPos.x).get(foodPos.y).ColorIt(1);
    }
    // Method that shows where snake is / is not

    // Ability to move snake - which direction => arrayList
    // 1 => Right; 2 => Left; 3 => Up; 4 => Down; 0 => Do Nothing;

    private void moveSnake(int direction) {
        switch (direction) {
            case 4: // Move Down
                headPosition.ChangeData(headPosition.x, Math.abs(headPosition.y + 1) % 20);
                positions.add(new XY(headPosition.x, headPosition.y));
                break;
            case 3: // Move Up
                if (headPosition.y - 1 < 0) {
                    headPosition.ChangeData(headPosition.x, 19);
                } else {
                    headPosition.ChangeData(headPosition.x, Math.abs(headPosition.y - 1) % 20);
                }
                positions.add(new XY(headPosition.x, headPosition.y));
                break;
            case 2: // Move Left
                if (headPosition.x - 1 < 0) {
                    headPosition.ChangeData(19, headPosition.y);
                } else {
                    headPosition.ChangeData(Math.abs(headPosition.x - 1) % 20, headPosition.y);
                }
                positions.add(new XY(headPosition.x, headPosition.y));
                break;
            case 1: // Move Right
                headPosition.ChangeData(Math.abs(headPosition.x + 1) % 20, headPosition.y);
                positions.add(new XY(headPosition.x, headPosition.y));
                break;
            default:
                break;
        }
    }

    private void moveSnakeColor() {
        for (XY i : positions) {
            int y = i.getY();
            int x = i.getX();
            Squares.get(y).get(x).ColorIt(0);
        }
    }

    // Refresh the squares (repaint?) => tail and head of snake
    private void snakeTail() {
        int newSnakeSize = snakeSize;
        for (int i = positions.size() - 1; i >= 0; i--) {
            if (newSnakeSize == 0) {
                XY t = positions.get(i);
                Squares.get(t.y).get(t.x).ColorIt(2);
            } else {
                newSnakeSize--;
            }
        }
        newSnakeSize = snakeSize;
        for (int i = positions.size() - 1; i >= 0; i--) {
            if (newSnakeSize == 0) {
                positions.remove(i);
            } else {
                newSnakeSize--;
            }
        }
    }

}
