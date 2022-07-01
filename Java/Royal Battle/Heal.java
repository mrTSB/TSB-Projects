import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.File;
import java.net.URL;
import java.util.*;

public class Heal extends Spell {
  
  private String imageSrc = "sprites/HealBottle.png";
  private int cost = 4;
  private int side = -1;

  
  // double x, double y, String imageSrc, int cost, int side
  
  public Heal(double x, double y) {
    super(x, y, "sprites/HealBottle.png", 4, -1);
  }

  public void trigger(Graphics window, List<Entity> units) {
    for (Entity e : units) {
      if (e.getTeam() == 1 && e.getX() < 600) {
        e.setHealth(e.getMaxHealth());
      }
    }
  }
}