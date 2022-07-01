import javax.swing.JFrame;
import java.awt.Component;
import java.util.Scanner;

public class TheGame extends JFrame
{
  private static int WIDTH = 800;
  private static int HEIGHT = 600;
  private static int type = 0;

  public TheGame()
  {
    super("PONG");
    System.out.println("Welcome to pong!");
    Scanner scan = new Scanner(System.in);
    System.out.println("Choose ball type: 0-regular, 1-blinky, 2-speedup: ");
    type = scan.nextInt();
    System.out.println("Enter width: ");
    WIDTH = scan.nextInt();
    System.out.println("Enter height: ");
    HEIGHT = scan.nextInt();

    System.out.println("Starting game...");
    setSize(WIDTH,HEIGHT);

    //Pong game = new Pong(WIDTH,HEIGHT,type);
    Pong game = new Pong(WIDTH,HEIGHT,type);

    ((Component)game).setFocusable(true);
    getContentPane().add(game);

    setVisible(true);

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  public static void main(String args[])
  {
    TheGame run = new TheGame();
  }
}