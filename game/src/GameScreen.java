import java.awt.HeadlessException;
import javax.swing.JFrame;

public class GameScreen extends JFrame //inheritance: gamescreen jframe'in özelliklerini kullanacak.(miras almak)
{

    public GameScreen(String title) throws HeadlessException { //constructor
        super(title);
    }
    
    public static void main(String[] args) {
    GameScreen screen = new GameScreen("DRAGON FIRE GAME");
    screen.setResizable(false);
    screen.setFocusable(false);
    screen.setSize(800, 600);
    screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    Game game = new Game();
    game.requestFocus();
    game.addKeyListener(game);
    game.setFocusable(true);
    game.setFocusTraversalKeysEnabled(false);

    screen.add(game);
    screen.setVisible(true);

    // Move these lines to the end of the main method
    screen.requestFocus();  // Make sure the JFrame has focus
    screen.addKeyListener(game);  // Add key listener to the JFrame

    // Rest of your existing code...
}

         
}