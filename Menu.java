import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.Graphics2D;
import java.awt.GradientPaint;
import java.awt.image.BufferedImage;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.io.InputStream;
import javax.swing.*;
import java.awt.*;

//this class creates the menu state that is initially set to open when the user starts the game
public class Menu extends JPanel implements KeyListener{
    public static final int WIDTH = 1024;
    public static final int HEIGHT = 768;
    static Font bigdoodlejump; //large doodlejump font
    static Font smalldoodlejump; //small doodlejump font
    static BufferedImage plant; //imports plant image from main
    static BufferedImage snowflake; //imports snowflake image from main

    //sets up the menu screen with the same dimensions and adds keyListener for keyboard input
    public Menu(){
    	this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        addKeyListener(this);
    }//Menu

    //draws the background, text, and images in the menu screen
    public void paintComponent(Graphics g) {
        //creates the same light grey to blue gradient as game
        Graphics2D gnew = (Graphics2D) g;
        GradientPaint background = new GradientPaint(0, 0, Color.BLUE, 00, HEIGHT, Color.WHITE);
        gnew.setPaint(background);
        gnew.fill(new Rectangle2D.Double(0, 0, WIDTH, HEIGHT));

        //sets font to large doodlejump and writes welcome message
        g.setFont(bigdoodlejump);
    	String title = "Welcome to Mammoth Jump!";
    	g.setColor(Color.WHITE);
    	g.drawString(title, 150, 200);

        //sets font to small doodlejump and writes instructions for gameplay
        g.setFont(smalldoodlejump);
        g.setColor(Color.BLACK);
        String storyI = "The ice age is ending.";
        String storyII = "A mammoth runs to higher altitudes to avoid cataclysmic floods and hot weather...";
        g.drawString(storyI, 400, 75);
        g.drawString(storyII, 50, 125);
        String move = "To control the mammoth, press the 'a' and 'd' keys to move left and right.";
        g.drawString(move, 70, 400);
        String temp = "Be sure to jump on snow platforms to stop the body temperature from rising.";
        g.drawString(temp, 100, 475);
        String plants = "Look for plants to eat and boost your score.";
        g.drawString(plants, 260, 550);
        String platforms = "Avoid strange monsters from northwest Massachusetts.";
        g.drawString(platforms, 220, 625);
        String enter = "To start the game, press 'g'. Press 'm' to return to this menu. Good luck!";
        g.drawString(enter, 100, 700);
        
        //draws images by calling .png files uploaded in main, and drawing next to specific instructions
        g.drawImage(SnowPlatform.snowflake, 25, 440, null); 
        g.drawImage(GrassPlatform.plant, 180, 515, null); 
        g.drawImage(Monster.cow, 140, 590, null);
        g.drawImage(Mammoth.mammo, 487, 250, null);
        g.setColor(Color.CYAN);
        g.fillRect((int) 472, (int) 330, (int) 80, (int) 15);
    }//paintComponent

    //if user presses g on keyboard, switches state in main class to game to start gameplay
    public void keyPressed(KeyEvent e) {
    char c = e.getKeyChar();
        if (c == 'g'){
            Main.actualstate = Gamestates.GAME;
            System.out.println("start");
        }   
    }//keyPressed

    //initializes keyReleased method in keyListener interface 
    public void keyReleased(KeyEvent e) {
        char c=e.getKeyChar();
    }//keyReleased

    //initializes keyTyped method in keyListener interface
    public void keyTyped(KeyEvent e) {
        char c = e.getKeyChar();
    }//keyTyped

    //initializes addNotify method in keyListener interface
	public void addNotify() {
        super.addNotify();
        requestFocus();
    } //addNotify


}//class Menu