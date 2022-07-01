import java.io.File;
import java.net.URL;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;

public class Alien extends MovingThing
{
  private int speed;
  private Image image;
  private String direction;

  public Alien()
  {
    this(0,0,30,30,0);
  }

  public Alien(int x, int y)
  {
    this(x,y,30,30,0);
  }

  public Alien(int x, int y, int s)
  {
    this(x,y,30,30,s);
  }

  // all ctors call this ctor
  public Alien(int x, int y, int w, int h, int s)
  {
    super(x, y, w,h);
    speed=s;
    direction = "right";
    try
    {
      URL url = getClass().getResource("alien.jpg");
      image = ImageIO.read(url);
    }
    catch(Exception e)
    {
      System.out.println("Alien main ctor catch exception thing");
      //feel free to do something here
    }
  }

  public void setSpeed(int s)
  {
    speed = s;
  }

  public int getSpeed()
  {
    return speed;
  }

  public String getDir(){
    return direction;
  }

  public void setDir(String d){
    direction = d;
  }

  public void move(String d)
  {
    if(direction.equals("up")){
      setY(getY()-speed);
    }
    if(direction.equals("down")){
      setY(getY()+speed);
    }
    if(direction.equals("right")){
      setX(getX()+speed);
    }
    if(direction.equals("left")){
      setX(getX()-speed);
    }
  }

  public void draw( Graphics window )
  {
    window.drawImage(image,getX(),getY(),getWidth(),getHeight(),null);
  }

  public String toString()
  {
    return super.toString() + " s: " + speed + " dir: " + direction;
  }
}
