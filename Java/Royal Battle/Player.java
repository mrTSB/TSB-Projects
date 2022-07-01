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

public class Player {
  private double elixir;
  private ArrayList<Card> cards;

  public Player() {
    elixir = 10;
  }
  
  public Player(ArrayList<Card> xd){
    elixir = 10;
    cards = xd;
  }

  public double getElixir(){
    return elixir;
  }

  public void setElixir(double e){
    if (e>10){
      elixir = 10;
    } else{
      elixir = e;
    }
  }

  public void addElixir(double e){
    elixir += e;
    if (elixir>10){
      elixir = 10;
    }
  }

  public void drawElixir(Graphics graphToBack){
    graphToBack.setColor(Color.WHITE);
		graphToBack.fillRect(50, (int) (370), (int) (700), (int)(10));
		Color purink = new Color(255,0,255);
    graphToBack.setColor(purink);
    graphToBack.fillOval(10,360,30,30);
    double percent = (getElixir())/10;
		graphToBack.fillRect((int) (50), (int) (370), (int)(percent*700), (int)(10));
    graphToBack.setColor(Color.WHITE);
    graphToBack.drawString(""+(int) getElixir(), 18,380);
    graphToBack.setColor(Color.BLACK);
    for (int i=0;i<11;i++){
      graphToBack.drawLine(50+70*i,370,50+70*i,380);
    }
  }

  public void notEnoughElixir(Graphics graphToBack){
    System.out.println("Not enough elixir");
  }
}