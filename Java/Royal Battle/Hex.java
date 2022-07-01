//taken by Aryav

public class Hex extends Thing{

	private int team;
	private Entity target;
  boolean alive;
  double dx, dy;
	double dmg;
	double speed;
  double range;
  double distTravelled;
  
  public Hex(double x, double y, double s){
    super(x,y,s);
	}
		
	public Hex(double x, double y, double s, double dmg, double speedies, double range){
	super(x,y,s);
			setDamage(dmg);
			setSpeed(speedies);
		  setRange(range);
    alive = true;
		
}
  
	public void onHit(Battleground b){
		System.out.println("base class");
	};
  public void onTouch(Battleground b){
    System.out.println("aaaaaa");
  }

  public void move(){
    double d = dist(target);
    if(d == 0) return;
    dx = (target.getX() - getX())/d;
    dy = (target.getY() - getY())/d;
    dx *= speed;
    dy *= speed;
    this.move(dx, dy);
    distTravelled += speed;
  }
	
	public void setDamage(double d){
		dmg = d;
	}
  public double getDamage(){
    return dmg;
  }
	public void setSpeed(double s){
		speed = s;
	}

		public void setRange(double ranger){
		range = ranger;
	}

	public double getRange(){
		return range;
	}
	public void setDx(double d){
    dx = d;
  }
  public void setTarget(Entity e){
    target = e;
  }
  public Entity getTarget(){
    return target;
  }
  public void setTeam(int t){
    team = t;
  }
  public int getTeam(){
    return team;
  }
	public boolean isAlive(){
    return alive;
  }
  public void kill(){
    alive = false;
  }

	
}

