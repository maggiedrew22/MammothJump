import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.Graphics2D;
import java.awt.GradientPaint;
import java.awt.Dimension;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.swing.*;
import java.awt.*;

//this class defines the game over state referenced in the main class, which is called when the user loses the game (mammoth dies)
public class GameOver extends JPanel implements KeyListener{
    public static final int WIDTH = 1024;
    public static final int HEIGHT = 768;
    int score;
    int highscore;
    boolean best;
    static Font bigdoodlejump; //large doodlejump font
    static Font smalldoodlejump; //small doodlejump font

    //sets up the game over screen with same dimensions and adds keylistener functionality
    public GameOver(int newscore, int high, boolean newhigh){
    	this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        addKeyListener(this);

        //establishes most recent score, high score, and best score
        score = newscore;
        highscore = high;
        best = newhigh;
    }//GameOver

    //controls the graphics for the game over screeen
    public void paintComponent(Graphics g) {
        //same light grey to blue gradient background as game
        Graphics2D gnew = (Graphics2D) g;
        GradientPaint background = new GradientPaint(0, 0, Color.BLUE, 00, HEIGHT, Color.WHITE);
        gnew.setPaint(background);
        gnew.fill(new Rectangle2D.Double(0, 0, WIDTH, HEIGHT));

        //sets font to large doodlejump in yellow
        g.setColor(Color.YELLOW);
        g.setFont(bigdoodlejump);

        //if recent score is higher than high score, draw new high score
        if (best){
            String best = "New High Score!";
            g.drawString(best, 300, 200);
        }

        //writes game over, most recent score, and high score in white
        g.setColor(Color.WHITE);
    	String title = "Game Over!";
    	g.drawString(title, 360, 300);
        String scoreline = "Score: " + Integer.toString(score);
        g.drawString(scoreline, 400, 450);
        String highscoreline = "High Score: " + Integer.toString(highscore);
        g.drawString(highscoreline, 350, 550);

        //asks user if they want to return to menu to play again
        g.setColor(Color.BLACK);
        g.setFont(smalldoodlejump);
        String platforms = "Do you want to try again? If so, press 'm' to return to the menu.";
        g.drawString(platforms, 150, 650);
    }//paintComponent

    //if user presses m on keyboard, switch state in main class to MENU to return to menu screen
    public void keyPressed(KeyEvent e) {
    char c = e.getKeyChar();
        if (c == 'm'){
            Main.actualstate = Gamestates.MENU;
            System.out.println("returned to menu");
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
    }//addNotify


}//class GameOver