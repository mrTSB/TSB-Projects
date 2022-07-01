import java.awt.Color;
import java.awt.Graphics;

public class Block implements Locateable
{
  private int xPos;
  private int yPos;
  private int width;
  private int height;

  private Color color;

  public Block()
  {
    xPos = 0;
    yPos = 0;
    width = 10;
    height = 10;
    color = Color.BLACK;
  }

  //add other Block constructors - x , y , width, height, color

  public Block(int x, int y, int w, int h){
    xPos = x;
    yPos = y;
    width = w;
    height = h;
    color = Color.BLACK;
  }
     
  public Block(int x, int y, int w, int h, Color c){
    xPos = x;
    yPos = y;
    width = w;
    height = h;
    color = c;
  }

  public Block(int x, int y){
    xPos = x;
    yPos = y;
    width = 10;
    height = 10;
    color = Color.BLACK;
  }

  
  //add the other set methods
   

  public void setColor(Color col)
  {
    color = col;
  }

  public void draw(Graphics window)
  {
    //uncomment after you write the set and get methods
    window.setColor(color);
    window.fillRect(getX(), getY(), getWidth(), getHeight());
  }

  public void draw(Graphics window, Color col)
  {
    window.setColor(col);
    window.fillRect(getX(), getY(), getWidth(), getHeight());
  }
   
  public boolean equals(Object obj)
  {
    Block obt = (Block) obj;
    boolean output = (this.xPos == obt.xPos) && (this.yPos == obt.yPos) && (this.width == obt.width) && (this.height == obt.height) && (this.color.equals(obt.color));
    return output;
  }   

  //add the other get methods
  public int getX(){
    return xPos;
  }
  public int getY(){
    return yPos;
  }
  public int getHeight(){
    return height;
  }
  public int getWidth(){
    return width;
  }
  public Color getColor(){
    return color;
  }

  public void setPos(int x, int y){
    xPos = x;
    yPos = y;
  }
  public void setX(int x){
    xPos = x;
  }
  public void setY(int y){
    yPos = y;
  }
  public void setWidth(int w){
    width = w;
  }
  public void setHeight(int h){
    height = h;
  }

  

  //add a toString() method  - x , y , width, height, color
  public String toString(){
    return "The x,y position is: " + xPos +","+yPos+" and the width is "+width+" and the height is "+height+" the color is: "+color;
  }
}