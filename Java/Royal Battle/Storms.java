import java.util.*;

public class Storms {
  private static ArrayList<LightningStorm> storms = new ArrayList<LightningStorm> ();
  public static void addStorm(LightningStorm storm) {
    storms.add(storm);
  }
  public static void removeStorm(LightningStorm storm) {
    storms.remove(storm);
  }
  public static List<LightningStorm> getList() {
    return storms;
  }
  
}