public class Score{
  private int p1score;
  private int p2score;
  public Score(){
    p1score = 0;
    p2score = 0;
  }

  public void givePoint(int p) {
    if (p==1){
      p1score++;
    } else {
      p2score++;
    }
  }

  // public void reset() {
  //   p1score=0;
  //   p2score=0;
  // }

  public int getp2Score(){
    return p2score;
  }

  public int getp1Score(){
    return p1score;
  }

  public void serve(Ball b, int x, int y){
    b.setX(x);
    b.setXSpeed((-1+2*(int)(Math.random()*2))*2);
    b.setY(y);
    b.setYSpeed((-1+2*(int)(Math.random()*2))*2);
  }

  public String toString() {
    return "The Score is - " + p1score + " : "+p2score;
  }
}