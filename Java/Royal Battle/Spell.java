import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.File;
import java.net.URL;
import java.util.*;


public abstract class Spell extends Thing {
  // private Card card = new Card();
  private double speed = 0.0;
  private double acc = 0.02;
  private int cost;
  private int side;
  
  public Spell(double x, double y, String imageSrc, int cost, int side) {
    super(x, y, 30); // size = 30
    this.cost = cost;
    this.side = side;
    setImage(imageSrc);
    setType("spell");
  }

  public int getCost() {
    return cost;
  }
  public void setCost(int cost) {
    this.cost = cost;
  }

  public int getSide() {
    return side;
  }
  public void setSide(int side) {
    this.side = side;
  }
  
  // public void draw(Graphics window){
  //   window.drawImage(image,(int)(getX() - getSize()),(int)(getY() - getSize()) ,(int)getSize()*2,(int)getSize()*2,null);
    
  // }

  public void move() {
    setY(getY() + speed); // fall
    speed += acc; // accelerate
  }
  
  public boolean fallen(Graphics window, List<Entity> units) {
    if (getY() > 210) {
      trigger(window, units);
      return true;
    }
    return false;
  }
  
  public abstract void trigger(Graphics window, List<Entity> units);
}