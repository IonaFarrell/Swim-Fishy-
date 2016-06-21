//Iona Farrell
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//creates the JFrame, keyListener, and heroic fishy
public class GameFrame extends JFrame {
   //creates overall game surface
   private JPanel p = new JPanel();
   Graphics g = getGraphics();
   private int gameSpaceX_px;
   private int gameSpaceY_px;
   //enumeration for the keyListener
   private PossibleKeys keyPressed = PossibleKeys.NONE;
   //ocean background
   BufferedImage image;
   private int imageWidth;
   private int imageHeight;

   public GameFrame(int xSize, int ySize) {
      gameSpaceX_px = xSize;
      gameSpaceY_px = ySize;
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setTitle("Swim Fishy!");
   //reads in ocean background
      try {           	 
         image = ImageIO.read(new File("SwimFishyBackground.jpg"));
      }
      catch (IOException ex) {}
      imageWidth = image.getWidth();
      imageHeight = image.getHeight();
      setSize(gameSpaceX_px, gameSpaceY_px);
      setVisible(true);
      p.setBackground(Color.CYAN);
      add(p);
   //adds in the keyListener
      addKeyListener(keyListener);
   }
   
   //creates keyListener
   KeyAdapter keyListener =
      new KeyAdapter() {
         public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_UP) {
               keyPressed = PossibleKeys.UP;
            }
            else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
               keyPressed = PossibleKeys.DOWN;
            }
            else if (e.getKeyCode() == KeyEvent.VK_Q) {
               keyPressed = PossibleKeys.QUIT;
            }
            else if(e.getKeyCode() == KeyEvent.VK_P){
               keyPressed = PossibleKeys.PLAY;
            }
            else if(e.getKeyCode() == KeyEvent.VK_X){
               keyPressed = PossibleKeys.CLOSE;
            }
            else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
               keyPressed = PossibleKeys.RIGHT;
            }
            else if(e.getKeyCode() == KeyEvent.VK_LEFT){
               keyPressed = PossibleKeys.LEFT;
            }
            else {
               keyPressed = PossibleKeys.NONE;
            }
         }
      
         public void keyTyped(KeyEvent e) {}   
      
         public void keyReleased(KeyEvent e) {
            keyPressed = PossibleKeys.NONE;
         }
      };
 	 
   //paints the ocean background and heroic fishy   
   public void paint(Graphics g, Point pPos, int gameCounter){
   //ocean background
      g.drawImage(image, 0, 0, gameSpaceX_px, gameSpaceY_px, 0, 0, imageWidth, imageHeight, null);
   //heroic fishy
      g.setColor(Color.ORANGE);
      g.fillOval(pPos.x, pPos.y - 10, 40, 20);
      switch(gameCounter % 4){
         case 0:  //tail position 1
            g.fillRect(pPos.x - 20, pPos.y - 10, 20, 20);
            break;
         case 1:  //tail position 2
            g.fillRect(pPos.x - 15, pPos.y - 12, 15, 24);
            break;
      }
   //draws eye
      g.setColor(Color.BLACK);
      g.fillOval(pPos.x + 30, pPos.y - 5, 7, 7);
   }
    
   //returns keyPressed to be used in SwimFishy  
   public PossibleKeys getKeyPressed(){
      return keyPressed;
   }
}