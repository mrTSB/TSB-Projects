import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

public class Bullets
{
  private List<Ammo> ammo;

  public Bullets()
  {
    ammo = new ArrayList<Ammo>();
  }

  public void add(Ammo al)
  {
    ammo.add(al);
  }

  //post - draw each Ammo
  public void draw(Graphics window)
  {
    for(int i =0;i<ammo.size();i++){
      ammo.get(i).draw(window);
    }
  }

  public void move(String direction)
  {
    for(int i =0;i<ammo.size();i++){
      ammo.get(i).move(direction);
    }
  }
  
  // remove any Ammo which has reached the edge of the screen
  public void cleanUpEdges()
  {
    for(int i=ammo.size()-1;i>=0;i--){
      if(ammo.get(i).getY()<0 || ammo.get(i).getY()>600){
        ammo.remove(i);
      }
    }
  }

  public List<Ammo> getList()
  {
    return ammo;
  }

  public String toString()
  {
    return ""+ammo.size();
  }
}
