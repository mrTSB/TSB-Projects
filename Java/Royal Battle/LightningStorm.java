import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.File;
import java.net.URL;
import java.util.*;

public class LightningStorm {
  private boolean active = true;
  private int count = 0;
  private Image image;
  private int lightningCount = 3;
  // private List<Entity> units;
  int x; int y;
  private Entity curTarget = null;

  public LightningStorm(String imageSrc) {
    setImage(imageSrc);
  }

  public int getCount() {
    return count;
  }
  public void setCount(int count) {
    this.count = count;
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

  public void updateCoors(List<Entity> units) {
    Entity max = null;
    for (Entity e : units) {
      if (e.getType().equals("unit") && e.getTeam() == 2) {
        max = e;
        break;
      }
    }
    if (max == null) return;
    for (Entity e : units) {
      if (e.getType().equals("unit") && e.getTeam() == 2 && e.getHealth() > max.getHealth()) max = e;
    }
    x = (int)max.getX();
    y = (int)max.getY();
    curTarget = max;
  }
  
  private final int restPeriod = 2;
  private final int drawPeriod = 10;
  public void draw(Graphics window) {
    count++;
    if (count % (restPeriod + drawPeriod) < restPeriod) return;
    window.drawImage(image, x-200, y-360, 200, 360, null);
    curTarget.setHealth(0);
  }
}