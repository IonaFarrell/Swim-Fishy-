 //Iona Farrell
import java.awt.*;
import java.util.*;

//client code, runs game and implements obstacles and GameFrame
public class SwimFishy{
   //size of game surface
   public static int XFRAME = 1200;
   public static int YFRAME = 600;
   //delay between obstacles
   public static int OBSTACLEDELAY = 0;
 	 
   public static void main(String[] args) {
      boolean gameover = false;
      boolean playGame = true;
      boolean cleanUp = false;
      double gamespeed = 1.0;
      int counter = 0;
      int nInitNumObs = 5;
      int index = 0;
      int health = 100;
      int maxHealth = 500;
      PossibleKeys keyPressed = PossibleKeys.NONE; 	 
      PossibleKeys keyPressedOuter;
      PossibleKeys keyPressedLast = PossibleKeys.NONE;
      int vInc = 3;
      int hInc = 3;
      int xMax = XFRAME / 3;
      int xMin = XFRAME / 10;
      int maxObsDelay = XFRAME / 5;
      GameFrame frame = new GameFrame(XFRAME, YFRAME);
      Graphics g = frame.getGraphics();
   //point for the heroic fishy   
      Point pPos = new Point (XFRAME / 5, YFRAME / 2);
      ArrayList<Obstacle> obsList = new ArrayList<Obstacle>();
   
      introMessage(g);
   
      while(playGame){
         gameover = false;
         keyPressedOuter = frame.getKeyPressed();
         if(keyPressedOuter != keyPressedLast){//STOPS UNNECESSARY REDRAWS
            keyPressedLast = keyPressedOuter;
            introMessage(g);
         } // end of menu redraw if
         pause(25);
       
         switch (keyPressedOuter) {
            case PLAY:  //user starts the game
               g.setColor(Color.CYAN);
               g.fillRect(0, 0, XFRAME, YFRAME);
               g.setColor(Color.YELLOW);
               g.setFont(new Font("Monospaced", Font.BOLD, XFRAME / 15));
               g.drawString("SWIM, FISHY, SWIM!", XFRAME / 10, YFRAME / 2);
            //set or reset game parameters (so game can be played multiple times in succession)
               health = 100;
               gameover = false;
               playGame = true;
               cleanUp = false;
               gamespeed = 1.0;
               counter = 0;
               pPos.x = XFRAME / 5;
               pPos.y = YFRAME / 2;
               pause(1000);
            //while user has not died, play the game
               while(!gameover){
                  cleanUp = false;
                  obsList = manageObstacles (obsList, nInitNumObs, XFRAME, YFRAME, maxObsDelay, counter);//may ned to pass in counter to wait min number cycles
               //check key input
                  keyPressed = frame.getKeyPressed();
                  counter++;
                  if(counter % (XFRAME * 2) == 0){
                     gamespeed += 0.1;
                  }
                
                  switch (keyPressed) {
                     case NONE: //redraw obstacles one step to the left
                        break;
                     case UP: //redraw obstacles one step to the left and heroic fishy one step up
                        if(pPos.y > 35){
                           pPos.y -= vInc;
                        }
                        break;
                     case DOWN: //redraw obstacles one step to the left and heroic fishy one step down
                        if(pPos.y < YFRAME - 20){
                           pPos.y += vInc;
                        }
                        break;
                     case RIGHT: //redraw obstacles one step to the left and heroic fishy one step to the right
                        if(pPos.x < xMax){
                           pPos.x += hInc;
                        }
                        break;
                     case LEFT: //redraw obstacles one step to the left and heroic fishy one step to the left
                        if(pPos.x > xMin){
                           pPos.x -= hInc;
                        }
                        break;
                     case QUIT: //end of game  
                        gameover = true;
                        cleanUp = true;
                        break;
                     default:
                        break;
                  }
                
               //update obstacle positions and get health impact
                  for(index = 0; index < obsList.size(); index++){
                     health += obsList.get(index).updateObstacle(pPos, gamespeed);
                     if(health > maxHealth){
                        health = maxHealth;
                     }
                  }
               
               //update screen
                  frame.paint(g, pPos, counter);
                  g.setColor(Color.RED);
               //update health
                  if(health > 0){
                     g.fillRect(0, YFRAME - 30, XFRAME / (maxHealth / health), 30);
                  }
               //update score
                  g.setColor(Color.BLACK);
                  String score = "Score: " + counter / 35;
                  g.setFont(new Font("Monospaced", Font.BOLD, 20));
                  g.setColor(Color.GREEN);
                  g.drawString(score, 15, YFRAME - 35);
               //update obstacles
                  for(index = 0; index < obsList.size(); index++){
                     obsList.get(index).paintObstacle(g);
                  }
                  pause(25);
               //if user dies print end message             	 
                  if (health <= 0){
                     gameover = true;
                     cleanUp = true;
                     g.setFont(new Font("Goudy Stout", Font.BOLD, XFRAME / 13));
                     g.setColor(Color.YELLOW);
                     g.drawString("YOU DIED!", XFRAME / 10, YFRAME / 2 - 50);
                     pause(5000);
                  }
                
               }  
            //removes obstacles at end of game
               if(cleanUp){
                  while(obsList.size() > 0){
                     obsList.remove(0);
                  }
               }
             
               break;
            case CLOSE: //user has quit the game
               playGame = false;
         }  
      }
   //disposes gameFrame when user closes (presses x)
      frame.setVisible(false);
      frame.dispose();
   }
   
   //creates and returns an ArrayList of obstacles
   public static ArrayList manageObstacles(ArrayList<Obstacle> obsList, int nInitNumObs, int xSize, int ySize, int maxObsDelay, int counter){
      int index = 0;
      int obsType = 0;  	 
      int obsHeight = 0;
      Obstacle newObstacle;
   //randomizes entrance time, location, and kind of obstacle
      if(OBSTACLEDELAY <= 0){
         OBSTACLEDELAY = (int) (Math.random() * maxObsDelay);
         obsType = (int) (Math.random() * (nInitNumObs));
         obsHeight = (int) (Math.random() * ySize);
      
         if(obsType == 0){
            newObstacle = new Jellyfish (xSize, obsHeight);
         }
         else if(obsType == 1){
            newObstacle = new FishHook (xSize, obsHeight);
         }
         else if(obsType == 2){
            newObstacle = new Shark (xSize, obsHeight);
         }
         else if(obsType == 3){
            newObstacle = new Diver (xSize, obsHeight);
         }
         else if (obsType == 4){
            newObstacle = new Health (xSize, obsHeight);
         }
         else{
            newObstacle = new Diver (xSize, obsHeight);
         }
         obsList.add(newObstacle);   
      }
      OBSTACLEDELAY--;
   //removes inactive obstacles.
      for(index = 0; index < obsList.size(); index++){
         if (obsList.get(index).getActive() == false){
            obsList.remove(index);
            index--;//list gets shorter, have to decrease index to avoid skipping indexes
         }
      }
      return obsList;
   }
   
   //draws the intro/menu message
   public static void introMessage(Graphics g){
      g.setColor(Color.CYAN);
      g.fillRect(0, 0, XFRAME, YFRAME);
      g.setFont(new Font("Monospaced", Font.BOLD, XFRAME / 30));
      g.setColor(Color.MAGENTA);
      g.drawString("Press 'P' to Play", XFRAME / 10, YFRAME / 2);
      g.drawString("Press 'X' to Quit", XFRAME / 10, YFRAME / 2 + 25);
   }
   
   //pauses game for necessary amount of time
   public static void pause(int milliseconds){
      try {
         Thread.sleep(milliseconds);
      }
      catch(InterruptedException ex) {
         Thread.currentThread().interrupt();
      }
   }
}