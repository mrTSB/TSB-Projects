public class Unit extends Entity {
  private String favoriteTarget;
  
  public Unit(double x, double y){
    super(x, y);
    setType("unit");
  }
  public Unit(double x,double y, double size, double health, double atkSpeed, double dps, double speed, String favTarg) {
    super(x,y,size,health,atkSpeed,dps, speed);
    //this.speed = speed;
    favoriteTarget = favTarg;
    setType("unit");
    
  }

  public void move(){
    if(getTeam() == 1){
      super.move(getSpeed(), 0);
    }
    if(getTeam() == 2){
      super.move(-getSpeed(), 0);
    }
  }
  


	
}
