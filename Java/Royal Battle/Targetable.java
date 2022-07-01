public class Targetable extends Block {
  private int health;

  public Targetable(int x, int y, int size, int health) {
    super(x, y, size);
    this.health = health;
  }
  
  public int getHealth() {
    return health;
  }
  public void setHealth(int health) {
    this.health = health;
  }
}