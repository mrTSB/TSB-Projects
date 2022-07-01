import java.awt.Color;

public class Wall extends Block {
  public Wall(String type, int h, int w){
    super();
    if(type.equals("top")){
      setX(0);
      setY(0);
      setHeight(10);
      setWidth(w);
      setColor(Color.BLACK);
    }
    if(type.equals("bottom")){
      setX(0);
      setY(h-40);
      setHeight(10);
      setWidth(w);
      setColor(Color.BLACK);
    }
    if(type.equals("left")){
      setY(0);
      setX(0);
      setHeight(h-20);
      setWidth(10);
      setColor(Color.PINK);
    }
    if(type.equals("right")){
      setY(0);
      setX(w-20);
      setHeight(h-20);
      setWidth(10);
      setColor(Color.PINK);
    }
  }
}