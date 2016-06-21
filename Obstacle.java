//Iona Farrell
import java.awt.*;
import javax.swing.*;
import java.lang.Math;

//superclass for all obstacles, base for creating and updating them
public class Obstacle extends JPanel{

   private Graphics g;
   private int width;
   private int height;
   private Point oPos = new Point();
   private int hInc;
   private int xStart;
   private boolean active = true;
   private int healthImpact = -5;
   private int maxObs = 10;
   private boolean collided = false;
   private boolean passed = false;
   
   public Obstacle(int xLocation, int yLocation){
      oPos.x = xLocation;
      xStart = xLocation;
   }

   //makes the obstacle
   public Obstacle(int xLocation, int yLocation, int inWidth, int inHeight, int newHInc, int newHealthImpact){
      oPos.x = xLocation;
      xStart = xLocation;
      oPos.y = yLocation;
      width = inWidth;
      height = inHeight;
      hInc = newHInc;
      healthImpact = newHealthImpact;
   }
   
   //returns whether obstacle has passed protagonist
   public boolean getPassed(){
      return passed;
   }
   
   //returns point of obstacle
   public Point getPoint(){
      return oPos;
   }

   //returns width of obstacle
   public int getWidth(){
      return width;
   }
   
   //returns height of obstacle
   public int getHeight(){
      return height;
   }
   
   //returns horizontal speed of obstacles (number of pixels at which the obstacle increments)
   public int getHInc(){
      return hInc;
   }
   
   //returns whether obstacle is onscreen
   public boolean getActive(){
      return active;
   }
   
   //returns whether obstacle has collided with heroic fishy
   public boolean getCollided(){
      return collided;
   }
   
   //paints the obstacle
   public void paintObstacle(Graphics g){
   }
   
   //updates collision status and location
   public int updateObstacle(Point pPos, double gamespeed){
      boolean collision = false;
   //updates obstacle position
      oPos.x -= (int) (Math.floor(hInc * gamespeed));
      collision = (Math.abs(oPos.x - pPos.x) < height) && (Math.abs(oPos.y - pPos.y) < width);
      if(oPos.x < 0){
         oPos.x = xStart;
      //destroys obstacle
         active = false;
         return 0; //returns no impact on health
      }
      else if(collision && !collided){
         collided = true;
         return healthImpact; //returns minus health
      }
      if(oPos.x < pPos.x){
         passed = true;
      } 
      else{
         passed = false;
      }
      return 0; //returns no impact on health
   }
}