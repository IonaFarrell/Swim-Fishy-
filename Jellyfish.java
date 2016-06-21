//Iona Farrell
import java.awt.*;
import javax.swing.*;

//creates a diver obstacle
public class Jellyfish extends Obstacle{
   private double yPosHi = 0.0;
   
   //uses random location and set width, height, hInc, and health impact       
   public Jellyfish(int xLocation, int yLocation){
      super(xLocation, yLocation, 30, 30, 1, -5);
      yPosHi = (double) yLocation;
   }
   
   //paints "jellyfish" - pink square      
   public void paintObstacle(Graphics g){
      g.setColor(Color.PINK);
      g.fillRect(getPoint().x, getPoint().y - (getHeight() /2), getWidth(), getHeight());
   }
   
  //updates shark position (both vertical and horizontal) 
   public int updateObstacle(Point pPos, double gamespeed){
      //tracks heroic fishy vertically
      yPosHi += (double)(pPos.y - getPoint().y) / 26.0;
      //stops tracking after passes heroic fishy     
      if(!getPassed()){
         getPoint().y = (int) yPosHi;
      }
      return super.updateObstacle(pPos, gamespeed);
   }
}