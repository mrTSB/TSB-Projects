import java.awt.Color;
import java.util.Scanner;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Canvas;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import static java.lang.Character.*;
import java.awt.image.BufferedImage;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.RenderingHints;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
public class Battleground extends Canvas implements KeyListener, Runnable

{

  private boolean[] keys;
  private boolean[] last;
  private boolean hS;
  private boolean won;
  private boolean lost;
  
  private BufferedImage back;
  ArrayList < Entity > units;
  private ArrayList < Entity > soccer;
  private ArrayList < Entity > basketball;
  private ArrayList < Spell > spells;
  private ArrayList < Thing > spellCards = new ArrayList < Thing > ();
  ArrayList < Hex > hexes;
  ArrayList < Entity > deadUnits = new ArrayList < Entity > ();
  ArrayList < Hex > deadHexes = new ArrayList < Hex > ();
  ArrayList < Spell > deadSpells = new ArrayList < Spell > ();
  // Card testCard;
  int t = 0;
  int fps = 30;
  double elixir_per_tick = 0.005;
  private Player Player1;
  
  public Battleground() {
    setTrophies(getTrophies()+1);
    Player1 = new Player();
    units = new ArrayList < Entity > ();
    soccer = new ArrayList < Entity > ();
    basketball = new ArrayList < Entity > ();
    spells = new ArrayList< Spell > ();
    
    soccer.add(new Maguire(0, 0));
    soccer.add(new Messi(0, 0));
    soccer.add(new Neymar(0, 0));
    soccer.add(new Salah(0, 0));
    soccer.add(new RonaldoGiant(0, 0));
    soccer.add(new RonaldoSmall(0, 0));
    soccer.add(new Suarez(0, 0));

    spellCards.add(new Lightning(0, 0));
    spellCards.add(new Poison(0, 0));
    spellCards.add(new Heal(0, 0));

    hexes = new ArrayList < Hex > ();

    //testCard = new Card(units.get(0));

    //units.add(new Unit())
    keys = new boolean[12];
    last = new boolean[12];
    
    //instantiate other instance variables

    this.addKeyListener(this);
    new Thread(this).start();
    setVisible(true);

    reset();

    
  }

  public void reset() {
    for(int i=units.size()-1;i>=0;i--){
      units.remove(i);
    }
    units.add(new Hoop(50, 150));
    units.add(new Goal(720, 150));
    units.get(0).setTeam(1);
    units.get(1).setTeam(2);

    for (int i=hexes.size()-1;i>=0;i--){
      hexes.remove(i);
    }
    hS=true; 
    Player1.setElixir(10);

    //System.out.println(won);
    if(won){
      System.out.println("add");
      setTrophies(getTrophies()+1);
    }
  if(lost){
      if(getTrophies()>0){
        //System.out.println()
        setTrophies(getTrophies()-1);
      }
    }

    won = false;
    lost = false;
    
    
  }

  public ArrayList < Entity > getUnits() {
    return units;
  }
  public void addUnit(Unit u) {
    if (u != null)
      units.add(u);
  }
  public ArrayList < Hex > getHexes() {
    return hexes;
  }
  public void addHex(Hex h) {
    if (h != null)
      hexes.add(h);
  }
  public void removeUnit(Unit u){
    deadUnits.add(u);
  }

  public void update(Graphics window) {
    
    if (!hS){
      if (units.get(1).getHealth()<=0){
        win(window);
        //System.out.println("win");
      } else if (units.get(0).getHealth()<=0){
        loss(window);
        //System.out.println(units.get(0).getHealth());
        //System.out.println("loss");
      } else {
        tick(window);
        paint(window);
      }
    } else {
      homeScreen(window);
    }
  }

  public void win(Graphics window) {
    window.setColor(Color.WHITE);
    //window.fillRect(0,0,800,450);
    window.setColor(Color.GREEN);
    window.drawString("VICTORY! Press space to go to the home screen",250,25);
    //twoDGraph.drawImage(back, null, 0, 0);
    if(keys[7]){
      Color c = new Color(173, 216, 230);
      window.setColor(c);
      window.drawString("VICTORY! Press space to go to the home screen",250,25);
      won = true;
      reset();
      hS = true;
      
    }
  }

  public void homeScreen(Graphics window) {

    window.setColor(Color.WHITE);
    //window.fillRect(0,0,800,450);
    window.setColor(Color.BLACK);
    int trophies  = getTrophies();
    System.out.println(trophies);
    window.drawString("Trophies:" + trophies,375,15);
    window.drawString("Press shift to play",375,35);
    if(keys[8]){
      hS=false;
    }
  }

  public void loss(Graphics window) {
    window.setColor(Color.WHITE);
    //window.fillRect(0,0,800,450);
    window.setColor(Color.RED);
    window.drawString("TAKE THE L! Press space to go to the homescreen",350,25);

    if(keys[7]){
      Color c = new Color(173, 216, 230);
      window.setColor(c);
      window.drawString("TAKE THE L! Press space to go to the homescreen",350,25);
      hS = true;
          reset();
      lost = true;
    }
  }
  
  public void tick(Graphics window) {
    // try{
    // setTrophies(getTrophies()+1);
    // System.out.println(getTrophies());
    //   }
    // catch(IOException a){
    
    // }
    
    
    deadUnits.clear();
		deadHexes.clear();
    deadSpells.clear();
    
    
    t++;

    
    for (Spell spell : spells) {
      spell.move();
      if (spell.fallen(window, units)) {
        deadSpells.add(spell);
      }
    }

    for (Spell spell : deadSpells) {
      spells.remove(spell);
    }
    
    if(Math.random() < 0.002) spawnEnemy();
    Player1.addElixir(elixir_per_tick);

    
    double min = Double.MAX_VALUE;
    for (int i = 2; i < units.size(); i++) {
      Entity unitOne = units.get(i);
      //System.out.println(unitOne.getAtkTimer()+" "+unitOne.getAtkSpeed());
      if (unitOne.getAtkTimer() >= unitOne.getAtkSpeed()) {
        unitOne.attack(this);
        unitOne.setAtkTimer(0);
        //System.out.println("im gay");
      }
     min = Double.MAX_VALUE;
      
        for (int j = 0; j < units.size(); j++) {
          Entity unitTwo = units.get(j);
          if (j != i) {
            if (unitTwo.isTargetable() && unitOne.getTeam() != unitTwo.getTeam() && unitOne.dist(unitTwo) < min) {
              min = unitOne.dist(unitTwo);
              unitOne.setTarget(unitTwo);
             
              //System.out.println(unitOne + ":"+unitOne.getTarget());
            }
          }
        }
      // if (unitOne.getTarget() != null){
      //   if(unitOne.getTarget().isDead()) {
      //     unitOne.setTarget(null);
      //   } 
      // }
      // if (unitOne.getTarget().dist(unitOne)>unitOne.getRange()) {
      //   unitOne.setTarget(null);
      // }
    }


    // Move if not in range, increment attack timer if in range
    for (int i=0; i<units.size();i++) {
      Entity u = units.get(i);
      if(u.getTarget()!= null){
        if (!u.inRange(u.getTarget())) {
          u.move();
        } else {
          u.setAtkTimer(u.getAtkTimer() + 0.02);
        }
      }
      if (u.isDead()) {
        deadUnits.add(u);
      }
    }
    
    for (Hex h: hexes) {
      if (h.getTarget().isDead()) deadHexes.add(h);
    }
    for (Hex h: deadHexes) {
      hexes.remove(h);
    }
    deadHexes.clear();
    // Move every attack
    // If attack has hit an enemy, call onhit and remove the attack
    for (Hex h: hexes) {
      h.move();
      if (h.getTarget() == null) {
        deadHexes.add(h);
        continue;
      }

      if (h.didCollide(h.getTarget()) && h.getTeam() != h.getTarget().getTeam()) {

        h.onHit(this);
        deadHexes.add(h);
      }

    }
    //remove dead units and attacks
    for (Hex h: deadHexes) {
      hexes.remove(h);
    }
    for (Entity u : deadUnits) {
      u.onDeath(this);
      units.remove(u);
    }

  }

  public void paint(Graphics window) {
    //set up the double buffering to make the game animation nice and smooth
    Graphics2D twoDGraph = (Graphics2D) window;

    if (back == null)
      back = (BufferedImage)(createImage(getWidth(), getHeight()));

    Graphics graphToBack = back.createGraphics();

    
    for (LightningStorm s : Storms.getList()) {
      s.updateCoors(units);
      s.draw(window);
    }
    Color ground = new Color(60, 100, 60);
    graphToBack.setColor(ground);
    graphToBack.fillRect(0, 0, 800, 400);
    Color c = new Color(173, 216, 230);
    graphToBack.setColor(c);
    graphToBack.fillRect(0, 0, 800, 175);
    
    Player1.drawElixir(graphToBack);
    //System.out.println(Player1.getElixir());
    
    for (Entity u: units) {
      u.draw(graphToBack);
    }
    for (Hex h: hexes) {
      h.draw(graphToBack);
    }
    for (Spell s : spells) {
      s.draw(graphToBack);
    }
    
    // testCard.draw(graphToBack,0,300);

    //playing cards when key is pressed & checks elixir cost (hardcoded)
    for(int i = 0; i < keys.length; i++){
          if ((keys[i] && keys[i] != last[i])) {
            
            Entity u = null;
            Spell s = null;
            int spawn_x = 30;
            int spawn_y = 130 + (int)(Math.random()*40);
            if(i == 0){
              u = new Maguire(spawn_x, spawn_y);
            }
            if(i == 1){
              u = new Messi(spawn_x, spawn_y);
            }
            if(i == 2){
              u = new Neymar(spawn_x, spawn_y);
            }
            if(i == 3){
              u = new Salah(spawn_x, spawn_y);
            }
            if(i == 4){
              u = new RonaldoGiant(spawn_x, spawn_y);
            }
            if(i == 5){
              u = new RonaldoSmall(spawn_x, spawn_y);
            }
            if(i == 6){
              u = new Suarez(spawn_x, spawn_y);
            }
            if(i == 9){
              s = new Lightning(600, 20);
            }
            if(i == 10){
              s = new Poison(600, 20);
            }
            if(i == 11){
              s = new Heal(200, 20);
            }
      if (u == null && s == null) continue;
      if (u != null && u.getType().equals("unit")) {
        if(Player1.getElixir()>=u.getElixirCost()){
          units.add(u);
          units.get(units.size() - 1).setTeam(1);
          Player1.addElixir(-u.getElixirCost());
        } else {
          Player1.notEnoughElixir(graphToBack);
        }
      } else if (s != null) {
          if (Player1.getElixir()>=s.getCost()){
          spells.add(s);
          Player1.addElixir(-s.getCost());
        } else {
          Player1.notEnoughElixir(graphToBack);
        }
      }
    }
  }
      
    
      
    for (int i = 0; i < last.length; i++) {
      last[i] = keys[i];
    }
    ArrayList<Thing> soccerAsThings = new ArrayList<Thing> ();
    // ArrayList<Thing> spellsAsThings = new ArrayList<Thing> ();
    for (Entity entity : soccer) soccerAsThings.add(entity);
    // for (Spell spell : spellCards) spellsAsThings.add(spell);
    Card.drawDeck(graphToBack, soccerAsThings, 0, 280);
    Card.drawDeck(graphToBack, spellCards, 0, 220);

    twoDGraph.drawImage(back, null, 0, 0);
    // //if (units.get(0).getHealth()<=0||units.get(1).getHealth()<=0){
    //   back = null;
    // }
  }

  public void spawnEnemy(){
        int r = (int)(Math.random()*7);
            Entity u = null;
            int spawn_x = 750;
            int spawn_y = 130 + (int)(Math.random()*40);
            if(r == 0){
              u = new ChrisP(spawn_x, spawn_y);
            }
            if(r == 1){
              u = new Curry(spawn_x, spawn_y);
            }
            if(r == 2){
              u = new Draymond(spawn_x, spawn_y);
            }
            if(r == 3){
              u = new Durant(spawn_x, spawn_y);
            }
            if(r == 4){
              u = new Kawhi(spawn_x, spawn_y);
            }
            if(r == 5){
              u = new LeBron(spawn_x, spawn_y);
            }
            if(r == 6){
              u = new Westbrook(spawn_x, spawn_y);
            }
            u.setTeam(2);
            units.add(u);
  }

  
  public int getTrophies(){
    try{
    Scanner scanner = new Scanner(new File("trophies.txt"));
    return scanner.nextInt();
      }
    catch(FileNotFoundException a){
      return 0;
    }
  }
  
  public void setTrophies(int t){
    try{
    FileWriter trophy = new FileWriter("trophies.txt", false);
    String s=Integer.toString(t);
    for(int i = 0; i < s.length(); i++){
      trophy.write(s.charAt(i));
    }
    trophy.close();
      }
    catch(IOException asdf){
      
    }
    
    
  }





  
  public void keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_1) {
      keys[0] = true;
    }
    if (e.getKeyCode() == KeyEvent.VK_2) {
      keys[1] = true;
    }
    if (e.getKeyCode() == KeyEvent.VK_3) {
      keys[2] = true;
    }
    if (e.getKeyCode() == KeyEvent.VK_4) {
      keys[3] = true;
    }
    if (e.getKeyCode() == KeyEvent.VK_5) {
      keys[4] = true;
    }
    if (e.getKeyCode() == KeyEvent.VK_6) {
      keys[5] = true;
    }
    if (e.getKeyCode() == KeyEvent.VK_7) {
      keys[6] = true;
    }
    if (e.getKeyCode() == KeyEvent.VK_SPACE){
      keys[7] = true;
    }
    if (e.getKeyCode() == KeyEvent.VK_SHIFT){
      keys[8] = true;
    }
    if (e.getKeyCode() == KeyEvent.VK_Q) {
      keys[9] = true;
    }
    if (e.getKeyCode() == KeyEvent.VK_W) {
      keys[10] = true;
    }
    if (e.getKeyCode() == KeyEvent.VK_E) {
      keys[11] = true;
    }
    
    repaint();
  }

  public void keyReleased(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_1) {
      keys[0] = false;
    }
    if (e.getKeyCode() == KeyEvent.VK_2) {
      keys[1] = false;
    }
    if (e.getKeyCode() == KeyEvent.VK_3) {
      keys[2] = false;
    }
    if (e.getKeyCode() == KeyEvent.VK_4) {
      keys[3] = false;
    }
    if (e.getKeyCode() == KeyEvent.VK_5) {
      keys[4] = false;
    }
    if (e.getKeyCode() == KeyEvent.VK_6) {
      keys[5] = false;
    }
    if (e.getKeyCode() == KeyEvent.VK_7) {
      keys[6] = false;
    }
    if (e.getKeyCode() == KeyEvent.VK_SPACE){
      keys[7] = false;
    }
    if (e.getKeyCode() == KeyEvent.VK_SHIFT){
      keys[8] = false;
    }
    if (e.getKeyCode() == KeyEvent.VK_Q) {
      keys[9] = false;
    }
    if (e.getKeyCode() == KeyEvent.VK_W) {
      keys[10] = false;
    }
    if (e.getKeyCode() == KeyEvent.VK_E) {
      keys[11] = false;
    }
    repaint();
  }

  public void keyTyped(KeyEvent e) {
    //no code needed here
  }

  public void run() {
    try {
      while (true) {
        Thread.sleep(8);
        repaint();
      }
    } catch (Exception e) {}
  }
}