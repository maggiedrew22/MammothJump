import java.awt.Graphics;
import java.awt.Color;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

//this class creates the mammoth avatar and contains methods for updating position, contact with a platform or monster, movement along all edges
//of the screen, mammoth death function, and drawing methods
public class Mammoth extends MovingThing{
    double radius;
    double maxheight; //maximum height mammoth reaches while jumping to prevent from flying off the screen
    double dimension;
    int score;
    double temperature; //body temperature of mammoth
    boolean death;
    static BufferedImage mammo; //calls mammoth image file from main

    //creates instance of mammoth
    public Mammoth(){ 
    	position = new Pair(500.0, 500.0);
		velocity = new Pair(0, 200);
		acceleration = new Pair(0.0, 500.0);
		maxheight = 250; 
		dimension = 50; 
		score = 0;
		temperature = 0;
	}//Mammoth

	//updates mammoth's position, speed, and body temperature
	public void update(World w, double time){ 
		position = position.add(velocity.times(time));
		velocity = velocity.add(acceleration.times(time));
		temperature = temperature + 0.5; 
		edgeFunction(w);
	}//update

	//sets mammoth's behavior for edges of screen by implementing the kill mammoth function if it falls or reaches max body temp, prevents jumping
	//beyond top of screen, and wraps movement on L/R edges of screen 
	public void edgeFunction(World w){
		Boolean bounced = false;
		if (position.y + radius > w.height || temperature > w.height-100){ //kills the mammoth if it falls off the screen or body temperature is too high
			killMammo();
		}
		if (position.y - radius <= maxheight){ //keeps the mammoth from flying off the screen (jumping too high)
			velocity.zeroY(); 
			position.y = 250 + radius;
		}
		if (position.x + (radius*2) <= 0){ //wraps screen from L to R (mammoth reappears on right edge)
		    position.x = w.width -1;
		    bounced = true;
		}
		else if(position.x >= w.width){ //wraps screen from R to L (mammoth reappears on left edge)
		    position.x = 0;
		    bounced = true;
		}
	}//edgeFunction

	//function for contact with monsters (which implements kill mammoth method) and all platform types
	public boolean contact(Platform p){ 
		if (p instanceof Monster){
			if ((position.y <= p.position.y+p.height && (position.y + dimension) >= p.position.y && position.x <= (p.position.x+p.width) && position.x+dimension >= p.position.x) || (position.x + dimension >= p.position.x && position.x <= p.position.x + p.width && position.y >= p.position.y && position.y <= p.position.y + p.height)){
			killMammo();	
				return true; //if mammoth comes into contact with monster
			}
		}

		if (position.y+dimension >= p.position.y && position.y+dimension <= p.position.y+p.height && position.x <= (p.position.x+p.width) && position.x+dimension >= p.position.x && velocity.y > 0){
			return true; //if mammoth lands on a platform
		}else{
			return false;
		}	
    }//contact

    //changes death state to true, saves current score, changes state in main to game over to switch to game over screen
    public void killMammo(){
    	death = true;
    	System.out.println("tuskboi died :(");
    	Main.score = score;
    	Main.actualstate = Gamestates.GAMEOVER;
        System.out.println("gameover");
    }//killMammo

    //draws mammoth
    public void draw(Graphics g){ //draws mammoth
    	g.drawImage(mammo, (int)(position.x), (int)(position.y - radius), null);
    }//draw

}//class Mammoth
