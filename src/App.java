import javax.swing.JFrame;

public class App {
    public static void main(String[] args) throws Exception {
        // Creating the game window
        Window window = new Window();

        // Set window settings
        window.setTitle("Snake Game");
        window.setSize(500, 500);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
