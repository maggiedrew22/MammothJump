import java.awt.Graphics;
import java.awt.Color;
import java.util.Random;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

//this class creates the simple platform, and is the parent class for all platform subtypes and monsters
public class Platform extends MovingThing{ 
    double width;
    double height;
    double stopheight; //height where platforms stop moving to avoid continuous platform scrolling
    
    //sets up a platform with set width, height, velocity, and stopping height
    public Platform(){
    	width = 80;
    	height = 15;
		velocity = new Pair(0, 500); 
		stopheight = 700; //sets stopheight right above bottom of screen for mammoth to jump on
	}//Platform
	
	//update platform's position in world based on its velocity
	public void update(World w, double time){
		position = position.add(velocity.times(time));
	}//update

	//this calls the simplest "bounce" function called when the mammoth lands on platform
	public void contactFunction(World w, Mammoth m){ 
		bounce(w, m);
	}//contactFunction

	//resets velocity and sets a new level if platform is above mammoth's current active platform
	public void bounce(World w, Mammoth m){
		m.velocity.resetY();
		if (this.position.y <= stopheight && w.newlevel == false){
			w.newlevel = true;
		}
	}//bounce

	//returns stopheight based on platform's position
	public double getStopHeight(){
		return stopheight;
	}//getStopHeight

	//draws platforms in blue
    public void draw(Graphics g){ 
			g.setColor(Color.CYAN);
			g.fillRect((int)(position.x), (int)(position.y), (int)(width), (int)(height));
    }//draw


}//class Platform




