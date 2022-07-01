import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;

//melee low damage  high atk speed in Maguire.java
//skeleton

public class MessiAtk extends Hex {

	 public MessiAtk(double x, double y, double dmg, int t, double range)
  {
     super(x,y,8,dmg,1,range);

// x-pos y-pos size damage speed range
    setTeam(t);
	    setImage("sprites/soccerBall.png");
  }
  public void onHit(Battleground b){


		getTarget().setHealth(getTarget().getHealth() - getDamage());
  }






	
}