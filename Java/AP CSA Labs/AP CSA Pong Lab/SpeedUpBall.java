import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

class SpeedUpBall extends Ball
{

  //instance variables

  public SpeedUpBall()
  {
    super();
  }

  public SpeedUpBall(int x, int y)
  {
    super(x,y);
  }


  public SpeedUpBall(int x, int y, int xSpd, int ySpd)
  {
    super(x,y,xSpd,ySpd);
  }

  public SpeedUpBall(int x, int y, int xSpd, int ySpd, Color c)
  {
    super(x,y,xSpd,ySpd,c);
  }

  public SpeedUpBall(int x, int y, int w, int h, int xSpd, int ySpd)
  {
    super(x,y,w,h,xSpd,ySpd);
  }


  public SpeedUpBall(int x, int y, int w, int h, Color col, int xSpd, int ySpd)
  {
    super(x,y,w,h,col,xSpd,ySpd);
  }

  public void setXSpeed( int xSpd )
  {
    if(xSpd>0){
      super.setXSpeed(xSpd+1);
    } else if (xSpd<0){
      super.setXSpeed(xSpd-1);
    } else {
      super.setXSpeed(xSpd);
    }
  }

  public void setYSpeed( int ySpd )
  {
    if(ySpd>0){
      super.setYSpeed(ySpd+1);
    } else if (ySpd<0){
      super.setYSpeed(ySpd-1);
    } else {
      super.setYSpeed(ySpd);
    }
  }
}

