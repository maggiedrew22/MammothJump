
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Vector;
import java.util.Random;
import java.util.Iterator;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

//This class contains all the objects in the game (mammoth, platforms, monsters) and controls them
class World{  
    int height;
    int width;
    Mammoth tuskboi; 
    Vector<Platform> platforms; //contains all the platforms
    double measure;
    Platform activeplat;
    Platform removeplat;
    boolean newlevel; 
    Vector<Platform> allplatforms; //used to keep track of leveling system

    public World(int initWidth, int initHeight){
		width = initWidth;
		height = initHeight;
		tuskboi = new Mammoth(); 
		platforms = new Vector<Platform>();
		allplatforms = new Vector<Platform>();

		//Adding a single platform for the mammoth to start on
		Platform starting = new Platform();    
		starting.position = new Pair(500, 750); 
		platforms.add(starting); 
		activeplat = starting;

		measure = 750; //used to make sure platforms don't generate too far apart

		//Generating the first 1000 platforms
		for (int i = 0; i<1000; i++){
			generatePlatform();
		}
	}

	//==========================Methods to Control the Mammoth======================\\
    public void drawMammoth(Graphics g){
	    tuskboi.draw(g);
	}//drawMammoth
    
    public void updateMammoth(double time){
    	tuskboi.update(this, time);	
    }//updateMammoth
    //=============================================================================\\



    //=====================Methods to Control the Platforms========================\\
    public void drawPlatforms(Graphics g){
		for (Platform p: platforms){ 
			p.draw(g);
		} 
	}//drawPlatforms

	//checks each platform for contact with the mammoth and conducts the function of that platform
	public void checkPlatforms(double time){ 
		removeplat = null;
		for (Platform p : platforms){
			if (tuskboi.contact(p)){
				activeplat = p; 
				activeplat.contactFunction(this, tuskboi);				
				if (p instanceof BrokenPlatform){ //removing broken platforms the mammoth jumps on
					removeplat = p;
				}
			}
		}
		if (removeplat != null){
			platforms.remove(removeplat);
		}
	}//checkPlatforms

	//updates the platforms' position on the screen and their contact with the mammoth
	public void updatePlatforms(double time){
		removeplat = null;

		//moves the moving platforms side to side
		for (Platform p : platforms){ 
			if (p instanceof MovingPlatform){ 
				p.position.x += p.velocity.x * time;
    			if (p.position.x <= 0 || p.position.x >= (this.width - p.width)){
		        	p.velocity.flipx();
		        }
			}
		}

		//moving the platforms downward
		double stoppoint = activeplat.getStopHeight(); //retrieves stopping point for the jumped-on platform
		if (newlevel){	//if the mammoth reached a new (higher) platform
			if(activeplat.position.y <= stoppoint){  //move the platforms downward until they reach the stopping point
				for (Platform p : platforms){
					p.update(this, time);
				}
			}else{
				newlevel = false; //the platform is no longer a new (higher) platform
			}
		}

		//removes all platforms that are off the screen
		for (Platform p : platforms){	
			if (p.position.y > height){ 
				removeplat = p;
			}
		}
		if (removeplat != null){        
			platforms.remove(removeplat); 
		}

		//if the number of platforms is running low, generate another 1000 platforms
		if (platforms.size() < 100){
			measure = 0;
			for (int i = 0; i <1000; i++){
			generatePlatform();
			}
		}
	}//updatePlatforms
	//===================================================================================\\



	//============================Methods to Generate Platforms==========================\\
	public void generatePlatform(){
		Random rand = new Random();

		//Stage 1 of 3 stages of difficulty (up until the 50th platform)
		if (allplatforms.size() < 101){ 
			int determine = rand.nextInt(100);

			//the chances for developing different types of platforms at this stage
			if (determine < 30){
				Platform p = new Platform();
				measure = addToPlatforms(p, measure);
			}
			if (determine > 29 && determine < 50){
				Platform p = new GrassPlatform();
				measure = addToPlatforms(p, measure);
			}
			if (determine > 49 && determine < 75){
				Platform p = new SnowPlatform();
				measure = addToPlatforms(p, measure);
			}
			if (determine < 85 && determine > 74){
				Platform p = new BrokenPlatform();
				addToPlatforms(p, measure);
			}
			if (determine > 84 && determine < 100){
				Platform p = new MovingPlatform();
				measure = addToPlatforms(p, measure);
			}
		}
		
		//Stage 2 of 3 stages of difficulty (50th to the 100th platform)
		if (allplatforms.size() > 100 && allplatforms.size() <201){ 
		 	int determine = rand.nextInt(100);
		 	
		 	//the chances for developing different types of platforms at this stage
			if (determine < 20){
				Platform p = new Platform();
				measure = addToPlatforms(p, measure);
			}
			if (determine > 19 && determine < 40){
				Platform p = new GrassPlatform();
				measure = addToPlatforms(p, measure);
			}
			if (determine > 39 && determine < 60){
				Platform p = new SnowPlatform();
				measure = addToPlatforms(p, measure);
			}
			if (determine < 70 && determine > 59){
				Platform p = new BrokenPlatform();
				addToPlatforms(p, measure);
			}			
			if (determine > 69 && determine < 90){
				Platform p = new MovingPlatform();
				measure = addToPlatforms(p, measure);
			}
			if (determine > 89){
				Platform p = new Monster();
				addToPlatforms(p, measure);
			} 
		 }

		 //Stage 3 of 3 stages of difficulty (above the 100th platform)
		 if (allplatforms.size() > 200){
		 	int determine = rand.nextInt(100);

		 	//the chances for developing different types of platforms at this stage
			if (determine < 10){
				Platform p = new Platform();
				measure = addToPlatforms(p, measure);
			}
			if (determine > 9 && determine < 30){
				Platform p = new GrassPlatform();
				measure = addToPlatforms(p, measure);
			}
			if (determine > 29 && determine < 50){
				Platform p = new SnowPlatform();
				measure = addToPlatforms(p, measure);
			}
			if (determine < 65 && determine > 49){
				Platform p = new BrokenPlatform();
				addToPlatforms(p, measure);
			}
			if (determine > 64 && determine < 90){
				Platform p = new MovingPlatform();
				measure = addToPlatforms(p, measure);
			}
			if (determine > 89){
				Platform p = new Monster();
				addToPlatforms(p, measure);
			}
		}
	}//generatePlatform

	//This method actually adds the platform to the platforms Vector
	public double addToPlatforms (Platform p, Double measure){
		Random rand = new Random();

		//increases spacing of the platforms at the different stages of difficulty
		int distance = 50; 
		if (allplatforms.size() > 100 && allplatforms.size() <201){
			distance= 100;
		}
		if (allplatforms.size()>200){
			distance= 150;
		}

		//Regulates positioning of platforms, so they can't be too far apart vertically (using measure variable)
		p.position = new Pair(rand.nextInt((int)(this.width-p.width)), measure-distance-rand.nextInt(50)); 

		//adds the platform to platforms
		platforms.add(p);
		allplatforms.add(p); //used to determine the total number of platforms created
		measure = p.position.y; //record the platform's y-position
		return measure;
	}//addToPlatforms
	//==========================================================================================\\

}//class World