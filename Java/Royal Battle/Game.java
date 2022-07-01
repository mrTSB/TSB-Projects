//
import javax.swing.SwingConstants;
import java.awt.*;
import javax.swing.JFrame;
import java.awt.Component;

public class Game extends JFrame
{
  private static final int WIDTH = 800;
  private static final int HEIGHT = 430;

  public Game()
  {
    super("Royal Battle");
    setSize(WIDTH,HEIGHT);

    Battleground game = new Battleground();

    ((Component)game).setFocusable(true);
    getContentPane().add(game);

    setVisible(true);

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  public static void main(String args[])
  {
    Game run = new Game();
  }
}