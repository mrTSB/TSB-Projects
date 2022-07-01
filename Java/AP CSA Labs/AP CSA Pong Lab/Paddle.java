import java.awt.Color;
import java.awt.Graphics;

public class Paddle extends Block implements Collideable<Block>
{
  //instance variables
  private int speed;

  public Paddle()
  {
    super(10,10);
    speed =5;
  }

  public Paddle(int x, int y)
  {
    super(x,y);
    speed = 5;
  }

  public Paddle(int x, int y, int w, int h)
  {
    super(x,y,w,h);
    speed = 5;
  }

  public Paddle(int x, int y, int w, int h, Color c)
  {
    super(x,y,w,h,c);
    speed = 5;
  }

  public Paddle(int x, int y, int w, int h, int s)
  {
    super(x,y,w,h);
    speed = s;
  }

  public Paddle(int x, int y, int s)
  {
    super(x,y);
    speed = s;
  }

  public Paddle(int x, int y, int w, int h, Color c, int s)
  {
    super(x,y,w,h,c);
    speed = s;
  }


  //add the other Paddle constructors

  public void moveUpAndDraw(Graphics window)
  {
    Color c = getColor();
    super.setColor(Color.white);
    draw(window);

    setY(getY()-speed);

    super.setColor(c);
    draw(window);
  }

  public void moveDownAndDraw(Graphics window)
  {
    Color c = getColor();
    super.setColor(Color.white);
    draw(window);

    setY(getY()+speed);

    super.setColor(c);
    draw(window);
  }

  //add get methods
  public int getSpeed(){
    return speed;
  }

  @Override
	public boolean didCollideLeft(Block b) {
		return false;
	}
  @Override
	public boolean didCollideRight(Block b) {
		return false;
	}
  @Override
	public boolean didCollideTop(Block b) {
		return getX() + getWidth() > b.getX() && getX()  <  b.getX() + b.getWidth() && getY() + getSpeed() < b.getY() + b.getHeight()&& getSpeed() < 0;
	}
  @Override
	public boolean didCollideBottom(Block b) {
		return getX() + getWidth() > b.getX() && getX()  <  b.getX() + b.getWidth() && getY() + getHeight() + getSpeed() > b.getY() && getSpeed() > 0;
	} 
   
  //add a toString() method
  public String toString(){
    return super.toString() + " s: " + getSpeed();
  }
}