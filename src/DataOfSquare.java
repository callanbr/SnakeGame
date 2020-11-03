import java.util.ArrayList;
import java.awt.Color;

public class DataOfSquare {
    ArrayList<Color> ColorList = new ArrayList<Color>();
    int color; // 0 = Blank(white), 1 = Food(red), 2 = Snake(blue)
    SquarePanel square;

    public DataOfSquare(int c) {
        // This adds colors to the ArrayList
        ColorList.add(Color.white); // Blank = 0
        ColorList.add(Color.red); // Food = 1
        ColorList.add(Color.blue); // Snake = 2
        ColorList.add(Color.orange); // test = 3
        color = c;
        square = new SquarePanel(ColorList.get(color));
    }

    public void ColorIt(int c) {
        square.ChangeColor(ColorList.get(c));
    }

}