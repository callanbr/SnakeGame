import java.util.ArrayList;
import javax.swing.JFrame;
import java.awt.GridLayout;
import java.awt.event.KeyListener;

public class Window extends JFrame {
    public static ArrayList<ArrayList<DataOfSquare>> Grid;
    public static int width = 20;
    public static int height = 20;

    public Window() {
        // This creates the arrayList that contains the treads
        Grid = new ArrayList<ArrayList<DataOfSquare>>();
        ArrayList<DataOfSquare> data;

        // Creates threads and thread data => put in ArrayList
        for (int w = 0; w < width; w++) {
            data = new ArrayList<DataOfSquare>();
            for (int h = 0; h < height; h++) {
                DataOfSquare newData = new DataOfSquare(2);
                data.add(newData);
            }
            Grid.add(data);
        }

        // Sets the layout of our window panel (GridLayout)
        getContentPane().setLayout(new GridLayout(20, 20, 0, 0));

        // TODO Start and pause threads - Add squares to panel
        for (int w = 0; w < width; w++) {
            for (int h = 0; h < height; h++) {
                getContentPane().add(Grid.get(w).get(h).square);
            }
        }

        // TODO Position the snake
        XY position = new XY(10, 10);
        SnakeController c = new SnakeController(position);
        c.start();

        // TODO Link window to keyboard commands
        this.addKeyListener((KeyListener) new KeyboardListener());

    }
}
