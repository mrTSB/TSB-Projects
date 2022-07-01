import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.util.List;

public class AlienHorde
{
  private List<Alien> aliens;

  public AlienHorde(int size)
  {
    aliens = new ArrayList<Alien>();
    for(int i=0;i<size;i++){
      aliens.add(new Alien(25*i %700,100+(50*(25*i/700)),1) );
    }
  }

  public List<Alien> getAliens() {
    return aliens;
  }
  
  public void add(Alien al)
  {
    aliens.add(al);
  }

  public void draw(Graphics window)
  {
    for(int i=0;i<aliens.size();i++){
      aliens.get(i).draw(window);
    }
  }

  public void move()
  {
    for(int i=0;i<aliens.size();i++){
      Alien alien = aliens.get(i);
      alien.move(alien.getDir());
      if(alien.getX()<0){
        alien.setDir("right");
        alien.setY(alien.getY()+alien.getHeight());
      }
      if(alien.getX()+alien.getWidth()>800){
        alien.setDir("left");
        alien.setY(alien.getY()+alien.getHeight());
      }
    }
  }

  // calulate if Aliens are hit by shots, if so remove the shot and alien and return the number of hits
  public int calcHits(List<Ammo> shots)
  {
    int hit = 0;
    for (int i=aliens.size()-1;i>=0;i--){
      for (int j=shots.size()-1;j>=0;j--){
        Ammo shot = shots.get(j);
        Alien alien = aliens.get(i);
        if(alien.didCollide(shot)){
          hit++;
          aliens.remove(alien);
          shots.remove(shot);
          System.out.println("Ammo-Alien hit at "+alien.getX()+","+alien.getY());
          break;
        }
      }
    }
    return hit;
  }

  public String toString()
  {
    return ""+aliens.size();
  }
}
