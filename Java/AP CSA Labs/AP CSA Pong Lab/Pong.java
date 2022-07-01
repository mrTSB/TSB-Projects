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

public class Pong extends Canvas implements KeyListener, Runnable
{
  private Ball ball;
  private Score tracker;
  private Paddle leftPaddle;
  private Paddle rightPaddle;
  private boolean[] keys;
  private BufferedImage back;
  private int width,height;
  private Wall leftWall, rightWall, topWall, bottomWall;

  // private int leftScore;
  // private int rightScore;

  private boolean gameOver = false;


  public Pong(int w, int h, int type)
  {
    //set up all variables related to the game
    //ball = new Ball(100,100,10,10,Color.BLACK);
    if (type==2){
      ball = new SpeedUpBall(100,100,10,10,Color.BLACK);
    } else if (type==1) {
      ball = new BlinkyBall(100,100,10,10,Color.BLACK);
    } else {
      ball = new Ball(100,100,10,10,Color.BLACK);
    }
    
    leftPaddle = new Paddle(20,100,10,80,Color.RED);
    rightPaddle = new Paddle(w-40,100,10,80,Color.BLUE);
    width = w;
    height = h;
    leftWall = new Wall("left",height-50,width);
    rightWall = new Wall("right", height-50, width);
    topWall = new Wall("top", height, width);
    bottomWall = new Wall("bottom", height-30, width);

    
    //rightWall.setColor(Color.BLACK);
    // rightScore = 0;
    // leftScore = 0;
    keys = new boolean[4];
    tracker = new Score();

    
    setBackground(Color.WHITE);
    setVisible(true);
                
    new Thread(this).start();
    addKeyListener(this);               //starts the key thread to log key strokes
  }
        
  public void update(Graphics window){
    paint(window);
  }

  public void paint(Graphics window)
  {
    //set up the double buffering to make the game animation nice and smooth
    Graphics2D twoDGraph = (Graphics2D)window;

    //take a snap shop of the current screen and same it as an image
    //that is the exact same width and height as the current screen
    if (back==null)
      back = (BufferedImage)(createImage(getWidth(),getHeight()));

    //create   a graphics reference to the back ground image
    //we will draw all changes on the background image
    Graphics graphToBack = back.createGraphics();


    ball.moveAndDraw(graphToBack);
    leftPaddle.draw(graphToBack);
    rightPaddle.draw(graphToBack);
    leftWall.draw(graphToBack);
    rightWall.draw(graphToBack);
    topWall.draw(graphToBack);
    bottomWall.draw(graphToBack);


    //check if ball hits left or right walls
    if (ball.didCollideLeft(leftPaddle)){
      ball.setXSpeed(-ball.getXSpeed());
    } else if (ball.didCollideLeft(leftWall)){
      tracker.givePoint(2);
      //System.out.println(tracker);
      
      graphToBack.clearRect(0,0,width,height);
      graphToBack.setColor(Color.GREEN);   graphToBack.drawString(tracker.toString(),width/2,height-35);
      tracker.serve(ball, width/2, height/2);
    }

    if (ball.didCollideRight(rightPaddle)){
      ball.setXSpeed(-ball.getXSpeed());
    } else if (ball.didCollideRight(rightWall)){
      tracker.givePoint(1);
      //System.out.println(tracker);
      graphToBack.clearRect(0,0,width,height);
      graphToBack.setColor(Color.GREEN);  graphToBack.drawString(tracker.toString(),width/2,height-35);
      tracker.serve(ball, width/2, height/2);
    }
                
    //see if the ball hits the top or bottom wall 
    if (ball.didCollideTop(topWall))
    {
      ball.setYSpeed(-ball.getYSpeed());
    }
    if (ball.didCollideBottom(bottomWall))
    {
      ball.setYSpeed(-ball.getYSpeed());
    }

    
    //move and check paddle
    if (keys[0])
    {
      //move left paddle up and draw it on the window
      if (leftPaddle.getY()-leftPaddle.getSpeed()>=10){
        leftPaddle.moveUpAndDraw(graphToBack);
      }
    } else if (keys[1])
    {
      //move left paddle down and draw it on the window
      if (leftPaddle.getY()+leftPaddle.getSpeed()<=bottomWall.getY()-leftPaddle.getHeight()){
        leftPaddle.moveDownAndDraw(graphToBack);
      }
    } else {
      leftPaddle.draw(graphToBack);
    }
    
    if (keys[2])
    {
      if (rightPaddle.getY()-rightPaddle.getSpeed()>=10){
        rightPaddle.moveUpAndDraw(graphToBack);
      }
    } else if (keys[3])
    {
      if (rightPaddle.getY()+rightPaddle.getSpeed()<=bottomWall.getY()-rightPaddle.getHeight()){
        rightPaddle.moveDownAndDraw(graphToBack);
      }
    } else {
      rightPaddle.draw(graphToBack);
    }

    //random default stuff
    twoDGraph.drawImage(back, null, 0, 0);
  }

  public void keyPressed(KeyEvent e)
  {
    switch(toUpperCase(e.getKeyChar()))
    {
    case 'W' : keys[0]=true; break;
    case 'Z' : keys[1]=true; break;
    case 'I' : keys[2]=true; break;
    case 'M' : keys[3]=true; break;
    }
  }

  public void keyReleased(KeyEvent e)
  {
    switch(toUpperCase(e.getKeyChar()))
    {
    case 'W' : keys[0]=false; break;
    case 'Z' : keys[1]=false; break;
    case 'I' : keys[2]=false; break;
    case 'M' : keys[3]=false; break;
    }
  }

  public void keyTyped(KeyEvent e){}
        
  public void run()
  {
    try
    {
      while(true)
      {
        Thread.currentThread().sleep(8);
        repaint();
      }
    }catch(Exception e)
    {
    }
  }     
}