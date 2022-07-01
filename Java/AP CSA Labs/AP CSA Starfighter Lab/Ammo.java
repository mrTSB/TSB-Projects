import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;

public class Ammo extends MovingThing
{
  private int speed;

  public Ammo()
  {
    this(0,0,0);
  }

  public Ammo(int x, int y)
  {
    this(x,y,0);
  }

  public Ammo(int x, int y, int s)
  {
    super(x,y,10,10);
    speed = s;
  }

  public void setSpeed(int s)
  {
    speed = s;
  }

  public int getSpeed()
  {
    return speed;
  }

  public void draw(Graphics window)
  {
    window.setColor(Color.YELLOW);
    window.fillRect(getX(),getY(),getWidth(),getHeight());
  }
        
        
  public void move(String direction)
  {
    if(direction.equals("up")){
      setY(getY()-speed);
    }
    if(direction.equals("down")){
      setY(getY()+speed);
    }
    if(direction.equals("right")){
      setX(getX()-speed);
    }
    if(direction.equals("left")){
      setX(getX()+speed);
    }
  }

  public String toString()
  {
    return super.toString() + " s: " + speed;
  }
}
