import java.awt.Color;
import java.awt.Graphics;

public class Ball extends Block implements Collideable<Block>
{
  private int xSpeed;
  private int ySpeed;

  public Ball()
  {
    super(200,200);
    xSpeed = 3;
    ySpeed = 1;
  }

  //add the other Ball constructors
  public Ball(int x, int y){
    super(x,y);
    xSpeed = 3;
    ySpeed = 1;
  }
  public Ball(int x, int y,int w, int h){
    super(x,y,w,h);
    xSpeed = 3;
    ySpeed = 1;
  }
  public Ball(int x, int y,int w, int h, Color c){
    super(x,y,w,h,c);
    xSpeed = 3;
    ySpeed = 1;
  }
  public Ball(int x, int y, int w, int h, int xS, int yS){
    super(x,y,w,h);
    xSpeed = xS;
    ySpeed = yS;
  }
  public Ball(int x, int y,int w, int h, Color c, int xs, int ys){
    super(x,y,w,h,c);
    xSpeed = xs;
    ySpeed = ys;
  }        
  //add the set methods

  public void setXSpeed(int xs){
    xSpeed = xs;
  }
  public void setYSpeed(int ys){
    ySpeed = ys;
  }
  public int getXSpeed(){
    return xSpeed;
  }
  public int getYSpeed(){
    return ySpeed;
  }

  public void moveAndDraw(Graphics window)
  {
    //draw a white ball at old ball location
    //Block temp = new Block(getX(),getY(),getWidth(),getHeight(),Color.WHITE);
    draw(window, Color.WHITE);

    setX(getX()+xSpeed);
    //setY
    setY(getY()+ySpeed);
    //draw the ball at its new location
    draw(window,getColor());
  }
   
  public boolean equals(Object obj)
  {
    Ball xd = (Ball) obj;
    boolean output = (super.equals(xd)) && (this.xSpeed == xd.xSpeed) && (this.ySpeed == xd.ySpeed);
    return output;
  }   

  //add the get methods

  @Override
	public boolean didCollideLeft(Block b) {
		return getX() + getXSpeed() < b.getX() + b.getWidth() && getY() + getHeight() > b.getY() && getY() < b.getY() + b.getHeight()&&getXSpeed() < 0;
	}
  @Override
	public boolean didCollideRight(Block b) {
		return getX()  + getWidth() + getXSpeed() > b.getX() && getY() + getHeight() > b.getY() && getY() < b.getY() + b.getHeight() && getXSpeed() > 0;
	}
  @Override
	public boolean didCollideTop(Block b) {
		return getX() + getWidth() > b.getX() && getX()  <  b.getX() + b.getWidth() && getY() + getYSpeed() < b.getY() + b.getHeight()&& getYSpeed() < 0;
	}
  @Override
	public boolean didCollideBottom(Block b) {
		return getX() + getWidth() > b.getX() && getX()  <  b.getX() + b.getWidth() && getY() + getHeight() + getYSpeed() > b.getY() && getYSpeed() > 0;
	}   
  
  //add a toString() method
  public String toString(){
    return "x: " + getX() + " y: " + getY() + " h: " + getHeight() + " w: " + getWidth() + " c: " + getColor() + " xS: " + getXSpeed() + " yS: " + getYSpeed();
  }
}