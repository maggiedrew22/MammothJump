import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.FontFormatException;
import java.awt.Font;

 public class Main{  
 public static Gamestates actualstate; //the called on state of the game
 private static Gamestates screenstate; //the state the screen is in
 public static int score;
 public static int highscore;

    public static void main(String[] args){

   	//This code imports all the image and font files and sends them to the classes in which they are used
    //=========================================FILE IMPORTS=======================================\\
    File plantfile = new File("plant.png");
    try{
      GrassPlatform.plant = ImageIO.read(plantfile);
      } catch(IOException e) { 
      System.out.println("ERROR: plant.png not found");   
      }

    File mammofile = new File("mammoth.png");
  	try{
  		Mammoth.mammo = ImageIO.read(mammofile);
  		} catch(IOException e) {
  		System.out.println("ERROR: mammoth.png not found");	
  		}

    File snowfile = new File("snowflake.png");
    try{
      SnowPlatform.snowflake = ImageIO.read(snowfile);
    } catch(IOException e) {
      System.out.println("ERROR: snowflake.png not found");
    }

    File monsterfile = new File("monster.png");
    try{
      Monster.cow = ImageIO.read(monsterfile);
      } catch(IOException e) {  
      System.out.println("ERROR: monster.png not found");  
      }

    Font doodlejump;
    File fontfile = new File("DoodleJump.ttf");
    try{
      Menu.bigdoodlejump = Font.createFont(Font.TRUETYPE_FONT, fontfile).deriveFont(72f);
      Menu.smalldoodlejump = Font.createFont(Font.TRUETYPE_FONT, fontfile).deriveFont(30f);
      Game.smalldoodlejump = Font.createFont(Font.TRUETYPE_FONT, fontfile).deriveFont(22f);
      GameOver.bigdoodlejump = Font.createFont(Font.TRUETYPE_FONT, fontfile).deriveFont(72f);
      GameOver.smalldoodlejump = Font.createFont(Font.TRUETYPE_FONT, fontfile).deriveFont(30f);
    } catch(IOException | FontFormatException e){
    	System.out.println("ERROR: could not find font");
    }
    //=====================================END FILE IMPORTS======================================\\


    //Create a JPanel for everything to be in
	 JFrame frame = new JFrame("Mammoth Jump");
   frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	 //Start the game on the menu screen and initialize scores
    actualstate = Gamestates.MENU; 
    screenstate = null;
    Game jumper;
    Menu menu;
    GameOver lose;
    score = 0;
    highscore = 0;
    boolean best = false;
    
    //setting up the called upon state
    while (true){
    //if the state is menu
    if (actualstate == Gamestates.MENU && screenstate != Gamestates.MENU){
    	System.out.println("changed to menu");
    	jumper = null; //allow garbage collector to remove the game if it was running before
    	menu = new Menu();
    	frame.setContentPane(menu);
		  frame.pack();
		  frame.setVisible(true);
		  screenstate = Gamestates.MENU;		
    }

    //if the state is the actual game
   	if (actualstate == Gamestates.GAME && screenstate != Gamestates.GAME){
   		System.out.println("changed to game");
		  jumper = new Game();         
      frame.setContentPane(jumper);
      frame.pack();
      frame.setVisible(true);
      screenstate = Gamestates.GAME;
   	}

   	//if the state is game over
   	if (actualstate == Gamestates.GAMEOVER && screenstate != Gamestates.GAMEOVER){
   		best = false;
   		System.out.println("changed to gameover");
   		jumper = null; //allow garbage collector to remove the game if it was running before

      //changing the highscore and sending scores to the game over screen
   		System.out.println("Score: " + score);
   		if (score > highscore){
   			System.out.println("new highscore!");
   			highscore = score;
   			best = true;
   		}
   		lose = new GameOver(score, highscore, best);
   		frame.setContentPane(lose);
      frame.pack();
      frame.setVisible(true);
      screenstate = Gamestates.GAMEOVER; 
   	}

   	//thread allows the while loop to keep running even if the actualstate does not change
   	try{
		Thread.sleep(100); 
	}
		catch(InterruptedException e){}	
	}
	} //main  
} //class Main

//the possible states
enum Gamestates{
	MENU, GAME, GAMEOVER;
} //Gamestates
