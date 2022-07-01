import javax.swing.JFrame;
import java.awt.Component;

public class customTest extends JFrame
{
  private static final int WIDTH = 800;
  private static final int HEIGHT = 600;

  public static void main(String args[])
  {
    Score test = new Score();
    System.out.println(test);
    test.givePoint(1);
    System.out.println(test);
  }
}