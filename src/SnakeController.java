import java.util.ArrayList;

// Controlling the game logic
public class SnakeController extends Thread {
    ArrayList<ArrayList<DataOfSquare>> Squares = new ArrayList<ArrayList<DataOfSquare>>();
    XY foodPosition;
    XY headPosition;
    ArrayList<XY> positions = new ArrayList<XY>();
    int snakeSize = 3;
    long snakeSpeed = 100;
    public static int snakeDirection;
    static int gridNumber = 40;
    int gridIdx = gridNumber - 1;

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
            checkCollision();
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

    private void checkCollision() {
        XY pos = positions.get(positions.size() - 1);
        for (int i = 0; i <= positions.size() - 2; i++) {
            boolean eatSnake = pos.getX() == positions.get(i).getX() && pos.getY() == positions.get(i).getY();
            if (eatSnake) {
                System.out.println("Game Over!");
                // TODO - Add notification
                while (true) {
                    pauseSnake();
                }
            }
        }

        // If snake eats food
        boolean eatingFood = pos.getX() == foodPosition.y && pos.getY() == foodPosition.x;
        if (eatingFood) {
            snakeSize = snakeSize + 1;
            foodPosition = getPosWithoutSnake();
            positionFood(foodPosition);
        }
        if (snakeSize > 9 && snakeSize < 35) {
            snakeSpeed = 90;
        } else if (snakeSize >= 35) {
            snakeSpeed = 80;
        } else {
            snakeSpeed = 100;
        }
    }

    // TODO What happens on stop? (game over)

    // position the food (new food)
    private void positionFood(XY foodPos) {
        Squares.get(foodPos.x).get(foodPos.y).ColorIt(1);
    }

    // TODO Method that shows where snake is / is not
    // TODO Check the position the snake occupies
    private XY getPosWithoutSnake() {
        XY pos;
        int randomX = 0 + (int) (Math.random() * gridIdx); // 0-gridIdx
        int randomY = 0 + (int) (Math.random() * gridIdx);
        pos = new XY(randomX, randomY);
        for (int i = 0; i <= positions.size() - 1; i++) {
            if (pos.getY() == positions.get(i).getX() && pos.getX() == positions.get(i).getY()) {
                pos = new XY(randomX, randomY);
                i = 0;
            }
        }
        return pos;
    }

    // Ability to move snake - which direction => arrayList
    // 1 => Right; 2 => Left; 3 => Up; 4 => Down; 0 => Do Nothing;
    private void moveSnake(int direction) {
        switch (direction) {
            case 4: // Move Down
                headPosition.ChangeData(headPosition.x, Math.abs(headPosition.y + 1) % gridNumber);
                positions.add(new XY(headPosition.x, headPosition.y));
                break;
            case 3: // Move Up
                if (headPosition.y - 1 < 0) {
                    headPosition.ChangeData(headPosition.x, gridIdx);
                } else {
                    headPosition.ChangeData(headPosition.x, Math.abs(headPosition.y - 1) % gridNumber);
                }
                positions.add(new XY(headPosition.x, headPosition.y));
                break;
            case 2: // Move Left
                if (headPosition.x - 1 < 0) {
                    headPosition.ChangeData(gridIdx, headPosition.y);
                } else {
                    headPosition.ChangeData(Math.abs(headPosition.x - 1) % gridNumber, headPosition.y);
                }
                positions.add(new XY(headPosition.x, headPosition.y));
                break;
            case 1: // Move Right
                headPosition.ChangeData(Math.abs(headPosition.x + 1) % gridNumber, headPosition.y);
                positions.add(new XY(headPosition.x, headPosition.y));
                break;
        }
    }

    private void moveSnakeColor() {
        for (XY i : positions) {
            int y = i.getY();
            int x = i.getX();
            Squares.get(y).get(x).ColorIt(2);
        }
    }

    // Refresh the squares (repaint?) => tail and head of snake
    private void snakeTail() {
        int newSnakeSize = snakeSize;
        for (int i = positions.size() - 1; i >= 0; i--) {
            if (newSnakeSize == 0) {
                XY t = positions.get(i);
                Squares.get(t.y).get(t.x).ColorIt(0);
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
