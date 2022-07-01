import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;



public class KawhiAtk extends Hex {

	 public KawhiAtk(double x, double y,double dmg, int t, double range)
  {
  super(x,y,15,dmg,1,range);
// x-pos y-pos size damage speed range
    setTeam(t);
	    setImage("sprites/basketBall.png");
  }
  public void onHit(Battleground b){

		getTarget().setHealth(getTarget().getHealth() - getDamage());
  }






	
}