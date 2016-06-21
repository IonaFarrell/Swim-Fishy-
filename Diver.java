//Iona Farrell
import java.awt.*;
import javax.swing.*;

//creates a diver obstacle
public class Diver extends Obstacle{
   private Graphics g;
   private double yPosHi;

   //uses random location and set width, height, hInc, and health impact    
   public Diver(int xLocation, int yLocation){
      super(xLocation, yLocation, 20, 10, 2, -5);
   }
   
   //paints "diver" - yellow rectangle   
   public void paintObstacle(Graphics g){
      g.setColor(Color.YELLOW);
      g.fillRect(getPoint().x, getPoint().y - (getHeight() /2), getWidth(), getHeight());
   }
}