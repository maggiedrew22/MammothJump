import java.awt.Graphics;
import java.awt.Color;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

//this is a subclass of the original platform class that creates platforms with collectable snowflakes to stay cool/alive
//the SnowPlatform implements Collectable interface to gather snowflakes
public class SnowPlatform extends Platform implements Collectable{
	boolean item;
    File file;
    static BufferedImage snowflake;

    //same constructor as platform class but instantiates collectable item as true
    public SnowPlatform(){
        super();
        item = true;
    }//SnowPlatform

    //same update function as platform class (moves on screen in the same way)
    public void update(World w, double time){
        super.update(w, time);
    }//update

    //takes platform's contactFunction and implements collect method (see below) to pick up snowflake  
    @Override
    public void contactFunction(World w, Mammoth tuskboi){
    	super.contactFunction(w, tuskboi);
        if(item){
    	collect(tuskboi);
    	removeItem();
        }
	}//contactFunction

    //decreases the temperature to prevent death if snowflake is collected
	public void collect(Mammoth tuskboi){
		tuskboi.temperature = tuskboi.temperature - 200;
        if (tuskboi.temperature < 0){
            tuskboi.temperature = 0;
        }
	}//collect

    //removes snowflake from platform
	public void removeItem(){
		this.item = false;
	}//removeItem

    //draws white snowy platform 
    @Override
    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect((int)(position.x), (int)(position.y), (int)(super.width), (int)(super.height));
        for (int i = 0; i < 4; i++){
            g.fillOval((int)position.x+(i*20), (int)position.y-6, 20, 21);
        }
        if (item){
        	drawItem(g);
        }
    }//draw

    //draws "snowflake.png" if contact has not yet been made
    public void drawItem(Graphics g){
        g.drawImage(snowflake, (int)(this.position.x + 15), (int)(this.position.y - 50), null);
    }//drawItem
}//class SnowPlatform