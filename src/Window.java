import java.util.ArrayList;
import javax.swing.JFrame;
import java.awt.GridLayout;
import java.awt.event.KeyListener;

public class Window extends JFrame {
    public static ArrayList<ArrayList<DataOfSquare>> Grid;
    int gridNumber = SnakeController.gridNumber;
    public static int width = 40;
    public static int height = 40;

    public Window() {
        // This creates the arrayList that contains the treads
        Grid = new ArrayList<ArrayList<DataOfSquare>>();
        ArrayList<DataOfSquare> data;

        // Creates threads and thread data => put in ArrayList
        for (int w = 0; w < width; w++) {
            data = new ArrayList<DataOfSquare>();
            for (int h = 0; h < height; h++) {
                DataOfSquare newData = new DataOfSquare(0);
                data.add(newData);
            }
            Grid.add(data);
        }

        // Sets the layout of our window panel (GridLayout)
        getContentPane().setLayout(new GridLayout(gridNumber, gridNumber, 0, 0));

        // Start and pause threads - Add squares to panel
        for (int w = 0; w < width; w++) {
            for (int h = 0; h < height; h++) {
                getContentPane().add(Grid.get(w).get(h).square);
            }
        }

        // Position the snake
        XY position = new XY(gridNumber / 2, gridNumber / 2);
        SnakeController c = new SnakeController(position);
        c.start();

        // Link window to keyboard commands
        this.addKeyListener((KeyListener) new KeyboardListener());

    }
}
