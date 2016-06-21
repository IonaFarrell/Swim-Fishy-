//Iona Farrell
import java.awt.*;
import javax.swing.*;

//creates a health obstacle
public class Health extends Obstacle{
   private int origWidth, origHeight;
   private Graphics g;
   //arrays of x and y coordinates of heart shape
   private int [] yPoints = {2,3,2,1,-1,-3,-1,1,2,3}; //range = 6
   private int [] xPoints = {0,1,2,2,1,0,-1,-2,-2,-1}; // range = 4
   
   //uses random location and set width, height, hInc, and health impact 
   public Health(int xLocation, int yLocation){
      super(xLocation, yLocation, 20, 30, 4, 10);
   }
   
   //paints "health" - heart
   public void paintObstacle(Graphics g){
      int[] tempX = new int [10];
      int[] tempY = new int [10];
      //draws heart
      if(!getCollided()){
         origWidth = getWidth() / 4;
         origHeight = getHeight() / 6;
         g.setColor(Color.RED);
         for(int i = 0; i < 10; i++){
            tempX[i] = (xPoints[i] * origHeight) + getPoint().x;
            tempY[i] = (yPoints[i] * -origWidth) + getPoint().y;
         }
         g.fillPolygon(tempX, tempY, 10);
      } //shrinks heart when collides with heroic fishy
      else if(getCollided() && origWidth > 0){
         origWidth -= 3;
         origHeight -= 3;
         g.setColor(Color.RED);
         for(int i = 0; i < 10; i++){
            tempX[i] = (xPoints[i] * origHeight) + getPoint().x;
            tempY[i] = (yPoints[i] * -origWidth) + getPoint().y;
         }
         g.fillPolygon(tempX, tempY, 10);
      }
   }
}