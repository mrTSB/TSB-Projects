// import java.awt.Color;
// import java.awt.Graphics;
// import java.awt.Image;
// import javax.imageio.ImageIO;
// import java.io.File;
// import java.net.URL;
// import java.util.*;

// public class Poison extends Spell {
  
//   private String imageSrc = "sprites/PoisonBottle.png";
//   private int cost = 2;
//   private int side = 1;

  
//   // double x, double y, String imageSrc, int cost, int side
  
//   public Poison(double x, double y) {
//     super(x, y, "sprites/PoisonBottle.png", 2, 1);
//   }

//    public void trigger(Graphics window, List<Entity> units) {
//     for (Entity e : units) {
//       if (e.getType.equals("unit") && e.getTeam() == 2 && e.getX() > 200) {
//         e.setHealth(e.getHealth() * 0.5);
//       }
//     }
//   }
// }