import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.File;
import java.net.URL;


public abstract class Thing implements Moveable, Collideable<Thing>
{
  private double xPos;
  private double yPos;
  private double size;
  public Image image;
  public String type;
  
  public Thing()
  {
    xPos = 10;
    yPos = 10;
    size = 10;
  }

  public Thing(double x, double y)
  {
    xPos = x;
    yPos = y;
    size = 10;
  }

  public Thing(double x, double y, double s)
  {
    xPos = x;
    yPos = y;
    size = s;
  }


	public void setImage(String s){
	 	try{
			URL url = getClass().getResource(s);
      image = ImageIO.read(url);	
			}
		catch(Exception e)
			{
				}
		
	}

	
  public void setPos( double x, double y)
  {
    //add code here
        xPos = x;
    yPos = y;
  }

  public void setX(double x)
  {
    //add code here
        xPos = x;
  }

  public void setY(double y)
  {
    //add code here
    yPos = y;
  }

  public double getX()
  {
    return xPos;   //finish this method
  }

  public double getY()
  {
    return yPos;  //finish this method
  }

  public void setSize(double s)
  {
    size = s;
  }
  public String getType() {
    return type;
  }
  public void setType(String type) {
    this.type = type;
  }

	public Image getImage(){
	return image;
		}
	


  public double getSize()
  {
    return size;  //finish this method
  }


  public void move(double dx, double dy){
    setX(getX() + dx);
    setY(getY() + dy);
  }
  
  public void draw(Graphics window){
    window.drawImage(image,(int)(getX() - getSize()),(int)(getY() - getSize()) ,(int)getSize()*2,(int)getSize()*2,null);
  }
  public double dist(Thing other){
    return Math.pow(Math.pow(this.getX() - other.getX(), 2) + Math.pow(this.getX() - other.getX(), 2), 0.5);
  }
public boolean didCollide(Thing other){
  return this.dist(other) <= this.getSize() + other.getSize();
}
  public String toString()
  {
    return getX() + " " + getY() + " " +  getSize();
  }
}
