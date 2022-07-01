import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Canvas;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import static java.lang.Character.*;
import java.awt.image.BufferedImage;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.RenderingHints;


public class Card{
  private int cost;
  //String img;
	private Image picture;
  private Image scaled;
  private Entity unit;
  private int size;
  private int team;
  
  public Card(int cost, Image picture, int size, int team) {
    this.cost = cost;
    this.picture = picture;
    this.size = size;
    this.team = team;
  }
  public Card(Entity thing){
    unit = Entity.newInstance(thing);
    unit.setImage(thing.imageName());
    cost =(int)thing.getElixirCost();
    picture = thing.getImage();
    size = (int) thing.getSize();
    //scaled doesnt work: https://stackoverflow.com/questions/7252983/resizing-image-java-getscaledinstance
    scaled = picture.getScaledInstance(50,800,java.awt.Image.SCALE_SMOOTH);
    team = thing.getTeam();
  }
  
  private BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) {
    Image resultingImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_DEFAULT);
    BufferedImage outputImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
    outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);
    return outputImage;
  }

  public void draw(Graphics window, int x, int y)  {
    window.drawImage(scaled,x,250,100,100,null);
    Color c = new Color(255,0,255);
    window.setColor(c);
    window.fillOval(x,250,20,20);
    window.setColor(Color.WHITE);
    window.drawString(""+cost,x+6,265);
  }

  public static void drawDeck(Graphics window, ArrayList<Thing> cards,int x,int y) {
    int xd = 1;
    String[] chars = new String[] {"q", "w", "e"};
    // x+=50;
    for (Thing unit : cards){
      boolean isUnit = unit.getType().equals("unit");
      int eCost = isUnit ? ((Unit)unit).getElixirCost() : ((Spell)unit).getCost();
      Image cardPic = unit.getImage();
      
      window.drawImage(cardPic,x,y,isUnit ? 75 : 60, isUnit ? 75 : 60,null);
      Color c = new Color(255,0,255);
      window.setColor(c);
      window.fillOval(x,y,20,20);
      window.setColor(Color.WHITE);
      window.drawString(""+eCost,x+6,y+15);
      window.setColor(Color.BLACK);
      window.fillOval(x,y+25,20,20);
      window.setColor(Color.WHITE);
      if (isUnit == true);
      window.drawString(""+(isUnit ? xd : chars[xd - 1]),x+6,y+40);
      x+=101;
      xd++;
    }
  }

  

  // this method will play the card, when the player selects it
  public void play(Graphics window) {
    unit.setPos(0,100);
    System.out.println("Play called");
  }
	




	
}