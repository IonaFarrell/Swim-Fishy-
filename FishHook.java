//Iona Farrell
import java.awt.*;
import javax.swing.*;
import java.lang.Math.*;

//creates a fish hook obstacle
public class FishHook extends Obstacle{
   private Graphics g;
   private double yPosHi;
   int counter = 0;
   int yOriginal;

   //uses random location and set width, height, hInc, and health impact 
   public FishHook(int xLocation, int yLocation){
      super(xLocation, yLocation, 10, 20, 1, -1000);
      yOriginal = yLocation;
   }

   //paints "fish hook" - blue oval
   public void paintObstacle(Graphics g){
      g.setColor(Color.BLUE);
      g.fillOval(getPoint().x, getPoint().y - (getHeight() /2), getWidth(), getHeight());
   }

   //updates fish hook position (both vertical and horizontal)   
   public int updateObstacle(Point pPos, double gamespeed){
      //creates "bobbing" effect
      yPosHi = Math.sin(Math.toRadians((double)counter)) * 50.0; //relates to ySize 
      getPoint().y = yOriginal + (int) yPosHi;
      counter += 5;
      if(counter > 359){
         counter = 0;
      }
      return super.updateObstacle(pPos, gamespeed);
   }  
}