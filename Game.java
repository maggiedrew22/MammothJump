import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.util.Random;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.Graphics2D;
import java.awt.GradientPaint;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.Font;
import java.io.File;
import java.io.IOException;

//this class generates the "GAME" state and runs the game by creating a world class and calling upon world's methods
public class Game extends JPanel implements KeyListener{  
    public static final int WIDTH = 1024;
    public static final int HEIGHT = 768;
    public static final int FPS = 60;
    World world;
    int gamescore;
    double tempheight;
    static Font smalldoodlejump;
    
    public Game(){
    //set up the world
	world = new World(WIDTH-100, HEIGHT);
	gamescore = 0;
	tempheight = 0;

	//set up features of the panel
	addKeyListener(this);
	this.setPreferredSize(new Dimension(WIDTH, HEIGHT));

	//start running the game
	Thread mainThread = new Thread(new Runner());
	mainThread.start();
	}	
    
    //=================================Create the Graphics============================\\
    public void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	gamescore = world.tuskboi.score; //finding the score
    	tempheight = world.tuskboi.temperature; //finding the temperature
    	Font scorefont = new Font(Font.SERIF, Font.PLAIN, 30);
    	Font tempfont = new Font(Font.SERIF, Font.PLAIN, 20);

	    background(g); //draw the background

		world.drawMammoth(g);
		world.drawPlatforms(g);

		//draws the sidebar using the score and the mammoth's temperature
		String scorelabel = "SCORE:";
    	String printscore = Integer.toString(gamescore);
		g.setColor(Color.GREEN);
	    g.fillRect(world.width, 0, WIDTH-world.width, 100);
	    g.setColor(Color.BLACK);
	    g.setFont(smalldoodlejump);
	    g.drawString(scorelabel, 955, 25);	    
	    g.setFont(scorefont);
	    g.drawString(printscore, 975, 75);
	    drawPlant(g);
	   	g.setColor(Color.BLACK);
	    g.fillRect(world.width, 100, WIDTH-world.width, HEIGHT-100);
	    g.setColor(Color.YELLOW);
	    g.setFont(tempfont);
	    String note = "Body Temp";
	    g.drawString(note, 930, 400);
	   	note ="Monitor";
	    g.drawString(note, 940, 430);
	    drawTemperature(g, tempheight);
    }//paintComponent

    //draws the backgorund
    public void background(Graphics g){
    	Graphics2D gnew = (Graphics2D) g;
	    GradientPaint background = new GradientPaint(0, 0, Color.BLUE, 00, HEIGHT, Color.WHITE);
	    gnew.setPaint(background);
	    gnew.fill(new Rectangle2D.Double(0, 0, world.width, HEIGHT));
    }//background

    //draws the little plant symbol
    public void drawPlant(Graphics g){
    	g.drawImage(GrassPlatform.plant, (int)(930), (int)(25), null);
    }//drawPlant

    //draw the rising temperature
    public void drawTemperature(Graphics g, double tempheight){
    	g.setColor(Color.RED);
    	if (tempheight >= HEIGHT-100){
	    	g.fillRect(world.width, (int)HEIGHT - 100, WIDTH-world.width, HEIGHT);
	    }
	    g.fillRect(world.width, (int)(HEIGHT-tempheight), WIDTH-world.width, HEIGHT);
    }//drawTemperature

    //================================================================================\\



    //===========================Continually Update the Screen========================\\
    class Runner implements Runnable{ 
   	private boolean running = true;
	public void run()
	{
	    while(running){ 
	   	world.checkPlatforms(1.0 / (double)FPS);
	    world.updatePlatforms(1.0 / (double)FPS);
		world.updateMammoth(1.0 / (double)FPS);
		if (world.tuskboi.death){ //only let the thread continue if the mammoth is not dead (otherwise the game will continue to run in the nethervers)
			running = false;
		}
		repaint(); //call on paintComponent to repaint the screen with updated objects
		try{
		    Thread.sleep(1000/FPS);
		}
		catch(InterruptedException e){}
	    }
	}//run
    }//class Runner
    //================================================================================\\



    //===========================Detecting the User Input=============================\\

    public void keyPressed(KeyEvent e) {
	    char c=e.getKeyChar();

	    //move the mammoth left
		if (c == 'a'){
			Pair left = world.tuskboi.getVelocity();
			world.tuskboi.setVelocity(new Pair(-200, left.y));
		}

		//move the mammoth right
		if (c == 'd'){
			Pair right = world.tuskboi.getVelocity();
			world.tuskboi.setVelocity(new Pair(200, right.y));
		}

		//return to the main menu
		if (c == 'm'){
			Main.actualstate = Gamestates.MENU;
            System.out.println("returned to menu");
		}
    }//keyPressed

    public void keyReleased(KeyEvent e) {
        char c=e.getKeyChar();

        //stop the mammoth moving laterally once the key is released
		Pair stop = world.tuskboi.getVelocity();
		world.tuskboi.setVelocity(new Pair(0, stop.y));

    }//keyReleased

    public void keyTyped(KeyEvent e) {
    	char c = e.getKeyChar();
    }//keyTyped

    public void addNotify() {
        super.addNotify();
        requestFocus();
    }//addNotify
    //===============================================================================\\

}//class Game
