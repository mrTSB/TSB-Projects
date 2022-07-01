import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.File;
import java.net.URL;


public class Entity extends Thing {
  
	private Entity curTarget = null;
	private int team;
	Entity target;
	private boolean targetable;
	private boolean flying;
	double health;
  private double speed;
  //maxhealth for bar, just passes health, then health is current health
  double maxHealth;
	double dmg;
	double atkTimer;
	double atkSpeed;
  double range; 
  int elixirCost;
	public Entity(double x, double y){
    super(x, y);
    targetable = true;
  }
	public Entity(double x, double y, double size, double health, double atkSpeed, double dps, double speed){
    super(x,y,size);
    this.health = health;
    
    this.atkSpeed = atkSpeed;
		this.dmg = dmg;
		this.maxHealth = health;
    this.speed = speed;
    targetable = true;
	}

  public Entity(Entity test){
    super(0,0,test.getSize());
    this.health = test.getHealth();
    this.atkSpeed = test.getAtkSpeed();
    this.dmg = test.getDamage();
    this.maxHealth = test.getMaxHealth();
    this.elixirCost = test.getElixirCost();
    this.range = test.getRange();
  }

  public void attack(Battleground b){
  };
  public void onDeath(Battleground b){
  };
  
  public int getTeam() {
    return team;
  }

  public void setTarget(Entity other) {
    target = other;
  }

  public double getAtkTimer(){
    return atkTimer;
  }

  public double getSpeed(){
    return speed;
  }

  public void setSpeed(double s){
    speed = s;
  }

  public void setAtkTimer(double xd){
    atkTimer = xd;
  }
 public void setDamage(double d){
   dmg = d;
 }
  public double getDamage() {
    return dmg;
  }

  public String imageName(){
    return "Image Not Set";
  }
  
  public int getElixirCost(){
    return elixirCost;
  }

  public void setElixirCost(int xd){
    elixirCost = xd;
  }

  public Entity getTarget(){
    return target;
  }

  public double getAtkSpeed(){
    return atkSpeed;
  }

	public void setAtkSpeed(double a){
		atkSpeed = a;
	}
	

	public void setTeam(int a){
 team = a;
	}
  public boolean isTargetable(){
    return targetable;
  }
//health atk speed dmg range
  public void setHealth(double h){
    health = h;
  }
  public double getHealth(){
    return health;
  }
  public void setMaxHealth(double h){
    maxHealth = h;
  }
  public double getMaxHealth(){
    return maxHealth;
  }

  public void setRange(double r){
    range = r;
  }
  public double getRange(){
    return range;
  }
  public boolean inRange(Entity e){
    return this.dist(e) < (this.getSize() + e.getSize())/2 + range;
  }

  public static Entity newInstance(Entity xd) {
    return new Entity(xd);
  }

  // public Entity getTarget() {

  // }

  public boolean isDead(){
    return health <= 0;
  }
  public void move(){
    if(getTeam() == 1){
      super.move(getSpeed(), 0);
    }
    if(getTeam() == 2){
      super.move(-getSpeed(), 0);
    }
  }

  
	public void draw(Graphics window){
		super.draw(window);
    
    //Bigger health target will have bigger health bar
  //   window.setColor(Color.WHITE);
		// window.fillRect((int) (getX()+15), (int) (getY()-3), (int) (maxHealth/5), (int)(5));
		// window.setColor(Color.GREEN);
		// window.fillRect((int) (getX()+15), (int) (getY()-3), (int)(health/5), (int)(5));

    
    //All health bar are same size, and go down reflectively on health
    window.setColor(Color.WHITE);
		window.fillRect((int) (getX()-10), (int) (getY()-getSize()), (int) (50), (int)(5));
		window.setColor(Color.GREEN);
    double percent = health/maxHealth;
		window.fillRect((int) (getX()-10), (int) (getY()-getSize()), (int)(percent*50), (int)(5));

    
    //this makes the atkSpeed bar small or large based on attak Speed
    window.setColor(Color.WHITE);
		window.fillRect((int) (getX()-10), (int) (getY()-7-getSize()), (int)(atkSpeed*10), (int)(5));
    window.setColor(Color.BLUE);
    window.fillRect((int) (getX()-10), (int) (getY()-7-getSize()), (int)(atkTimer*10), (int)(5));

    
    //All atkSpeed bar are same size and go up based on atkTimer, so fast atkSpeed means bar fills up quickly
    //work in progress
  //   window.setColor(Color.WHITE);
		// window.fillRect((int) (getX()+15), (int) (getY()-10), (int)(50), (int)(5));
  //   window.setColor(Color.BLUE);
  //   double percent2 = atkTimer/atkSpeed;
  //   window.fillRect((int) (getX()+15), (int) (getY()-10), (int)(percent*50), (int)(5));

    
    //window.setColor(Color.WHITE);
    //window.drawString(""+atkTimer,(int) (getX()+15), (int) (getY()-20));
	}
	
}