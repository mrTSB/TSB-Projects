public class Block{
  private int x, y, size;
  
  public Block(int x, int y){
    this.x = x;
    this.y = y;
  }

  public Block(int x, int y, int size) {
    this(x, y);
    this.size = size;
  }

  public int getX(){
    return this.x;
  }
  public int getY(){
    return this.y;
  }
  public void setX(int x){
    this.x = x;
  }
  public void setY(int y){
    this.y = y;
  }

  public void setPos(int x, int y){
    this.x = x;
    this.y = y;
  }
}