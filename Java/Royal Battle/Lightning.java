import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.File;
import java.net.URL;
import java.util.*;

public class Lightning extends Spell {
  
  private String imageSrc = "sprites/LightningBottle.png";
  private int cost = 6;
  private int side = 1;

  
  // double x, double y, String imageSrc, int cost, int side
  
  public Lightning(double x, double y) {
    super(x, y, "sprites/LightningBottle.png", 6, 1);
  }

  public void trigger(Graphics window, List<Entity> units) {
    Storms.addStorm(new LightningStorm("sprites/bolt.png"));
  }
}