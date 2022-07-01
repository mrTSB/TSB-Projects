import java.awt.Color;
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

public class OuterSpace extends Canvas implements KeyListener, Runnable
{
  private Ship ship;
  private Alien alienOne;
  private Alien alienTwo;
  private AlienHorde horde;
  private int numAliens = 20;
  
  private Bullets shots;
  private Bullets aShots;
  private int timer = 0;

  private int lives=0;
  private int iF=0;
  private int score=0;
  

  /* uncomment once you are ready for this part
   *
   private AlienHorde horde;
   private Bullets shots;
  */

  private boolean playing;
  private boolean[] keys;
  private BufferedImage back;

  public OuterSpace()
  {
    setBackground(Color.black);

    keys = new boolean[6];

    lives =3;
    score=0;
    //instantiate other instance variables
    //Ship, Alien

    ship = new Ship(410,410,20,20,1);
    horde = new AlienHorde(numAliens);
    shots = new Bullets();
    aShots = new Bullets();
    playing = true;

    this.addKeyListener(this);
    new Thread(this).start();

    setVisible(true);
  }

  public void update(Graphics window)
  {
    if(!playing){
      loss(window);
    }else if (horde.getAliens().size()==0){
      win(window);
    } else{
      paint(window);
    }
  }

  public void win(Graphics window) {
    window.setColor(Color.GREEN);
    window.drawString("VICTORY! Press space to continue and play further and reset",350,350);

    if(keys[4]){
      playing=true;
      horde = new AlienHorde(numAliens);
      lives = 3;
      score = 0;
      shots = new Bullets();
      aShots = new Bullets();
    }
  }

  public void loss(Graphics window) {
    window.setColor(Color.RED);
    window.drawString("TAKE THE L! Press space to continue and play further and reset",350,350);

    if(keys[4]){
      playing=true;
      horde = new AlienHorde(numAliens);
      lives = 3;
      score = 0;
      shots = new Bullets();
      aShots = new Bullets();
    }
  }

  public void paint( Graphics window )
  {
    //set up the double buffering to make the game animation nice and smooth
    Graphics2D twoDGraph = (Graphics2D)window;

    //take a snap shop of the current screen and same it as an image
    //that is the exact same width and height as the current screen
    if (back==null)
      back = (BufferedImage)(createImage(getWidth(),getHeight()));

    //create a graphics reference to the back ground image
    //we will draw all changes on the background image
    Graphics graphToBack = back.createGraphics();
    graphToBack.setColor(Color.BLACK);
    graphToBack.fillRect(0,0,800,600);
    graphToBack.setColor(Color.BLUE);
    graphToBack.drawString("StarFighter! Lives: " + lives+ " Score: "+ score, 25, 50 );
    ship.draw(graphToBack);
    horde.draw(graphToBack);
    shots.draw(graphToBack);
    aShots.draw(graphToBack);
    
    
    //ship.draw(graphToBack);
    // System.out.println(ship);
    // ship.setPos(200,200);
    // ship.setSpeed(50);
    // System.out.println(ship);
    // Alien one = new Alien(350,350,50,50,10);
    // Alien two = new Alien(300,300,50,50,10);
    // one.draw(graphToBack);
    // two.draw(graphToBack);

    if (keys[0])
    {
      ship.move("left");
    }
    if (keys[1]){
      ship.move("right");
    }
    if (keys[2]){
      ship.move("up");
    }
    if (keys[3]){
      ship.move("down");
    }
    horde.move();
    shots.move("up");
    aShots.move("down");

    //add code to move Ship, Alien, etc. is above

    if(timer==0){
      shots.add(new Ammo(ship.getX()+5,ship.getY()-20,2));
    }
    timer = (timer+1)%70;
    for(int i=0;i<horde.getAliens().size();i++){
      Alien alien = horde.getAliens().get(i);
      if (Math.random() < 0.002){
        aShots.add(new Ammo(alien.getX(),alien.getY(),1));
      }
    }
    
    int hit = horde.calcHits(shots.getList());
    score+= hit;
    if(hit!=0){
      shots.cleanUpEdges();
      aShots.cleanUpEdges();
    }

    if (iF<0){
      ship.draw(graphToBack);
      for (Ammo a: aShots.getList()){
        if (a.didCollide(ship)){
          lives -= 1;
          iF = 160;
          System.out.println("Ammo-Ship colision at:"+ship.getX()+","+ship.getY());
          break;
        }
      }
    } else {
      iF--;
      if(iF/20 %2==0){
        ship.draw(graphToBack);
      }
    }
    if (lives==0){
      playing=false;
    }

    //add in collision detection to see if Bullets hit the Aliens and if Bullets hit the Ship


    twoDGraph.drawImage(back, null, 0, 0);
  }


  public void keyPressed(KeyEvent e)
  {
    if (e.getKeyCode() == KeyEvent.VK_LEFT)
    {
      keys[0] = true;
    }
    if (e.getKeyCode() == KeyEvent.VK_RIGHT)
    {
      keys[1] = true;
    }
    if (e.getKeyCode() == KeyEvent.VK_UP)
    {
      keys[2] = true;
    }
    if (e.getKeyCode() == KeyEvent.VK_DOWN)
    {
      keys[3] = true;
    }
    if (e.getKeyCode() == KeyEvent.VK_SPACE)
    {
      keys[4] = true;
    }
    repaint();
  }

  public void keyReleased(KeyEvent e)
  {
    if (e.getKeyCode() == KeyEvent.VK_LEFT)
    {
      keys[0] = false;
    }
    if (e.getKeyCode() == KeyEvent.VK_RIGHT)
    {
      keys[1] = false;
    }
    if (e.getKeyCode() == KeyEvent.VK_UP)
    {
      keys[2] = false;
    }
    if (e.getKeyCode() == KeyEvent.VK_DOWN)
    {
      keys[3] = false;
    }
    if (e.getKeyCode() == KeyEvent.VK_SPACE)
    {
      keys[4] = false;
    }
    repaint();
  }

  public void keyTyped(KeyEvent e)
  {
    //no code needed here
  }

  public void run()
  {
    try
    {
      while(true)
      {
        Thread.currentThread().sleep(5);
        repaint();
      }
    }catch(Exception e)
    {
    }
  }
}

