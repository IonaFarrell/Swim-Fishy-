//Iona Farrell
import java.awt.*;
import javax.swing.*;
import java.lang.Math;

//creates a shark obstacle
public class Shark extends Obstacle{
   private double yPosHi;
   
   //uses random location and set width, height, hInc, and health impact 
   public Shark(int xLocation, int yLocation){
      super(xLocation, yLocation, 25, 25, 5, -15);
      yPosHi = (double) yLocation;
   }
   
   //paints "shark" - grey circle
   public void paintObstacle(Graphics g){
      g.setColor(Color.GRAY);
      g.fillOval(getPoint().x, getPoint().y, getWidth(), getHeight());
   }
   
   //updates shark position (both vertical and horizontal)
   public int updateObstacle(Point pPos, double gamespeed){
      //tracks heroic fishy vertically
      yPosHi += (double)(pPos.y - getPoint().y) / 13.0;
      //stops tracking after passes heroic fishy
      if(!getPassed()){
         getPoint().y = (int) yPosHi;
      }
      return super.updateObstacle(pPos, gamespeed);
   }
}