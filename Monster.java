import java.awt.Graphics;
import java.awt.Color;
import java.util.Random;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
//this is a subclass of the original platform class that creates a monster 
//if the monster comes in contact with the mammoth, the game ends
//though monster may not seem like a platform, it has all of the same parts except the contact function and graphics
public class Monster extends Platform{ 
	boolean item;
    static BufferedImage cow;

    //same constructor of platform class but resets dimensions 
    public Monster(){
    	super();
    	item = true;
    	width = 50;
    	height = 50;
	}//Monster

	//same update function as platform class (moves on screen in the same way)
    public void update(World w, double time){
        super.update(w, time);
    }//update
    
	//draws scary purple cow
	@Override
    public void draw(Graphics g){ 
		g.drawImage(cow, (int)(position.x), (int)(position.y), null);
    }//draw

    
}//class Monster

