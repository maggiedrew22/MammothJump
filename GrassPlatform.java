import java.awt.Graphics;
import java.awt.Color;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

//this is a subclass of the original platform class that creates platforms with collectable plants to increase score
//the GrassPlatform implements Collectable interface to gather plants
public class GrassPlatform extends Platform implements Collectable{
	boolean item;
    static BufferedImage plant;

    //creates instance of grass platform and instantiates collectable item as true
    public GrassPlatform(){ 
        super();
        item = true;
    }//GrassPlatform

    //same update function as platform class (moves on screen in the same way)
    public void update(World w, double time){ 
        super.update(w, time);
    }//update

    //takes platform's contactFunction and implements collect method (see below) to pick up plant  
    @Override
    public void contactFunction(World w, Mammoth tuskboi){ 
    	super.contactFunction(w, tuskboi);
        if(item){
    	collect(tuskboi);
    	removeItem();
        }
	}//contactFunction

    //adds to score if plant is collected
	public void collect(Mammoth tuskboi){ 
        tuskboi.score++;
	}//collect

    //removes plant from platform
	public void removeItem(){ 
		this.item = false;
	}//removeItem

    //draws grass platform (with plant if mammoth has not yet made contact)
    @Override
    public void draw(Graphics g) { 
        g.setColor(new Color(0, 200, 0));
        g.fillRect((int)(position.x), (int)(position.y), (int)(super.width), (int)(super.height));
        if (item){
        	drawItem(g);
        }
    }

    //draws plant.png if contact has not yet been made
    public void drawItem(Graphics g){     
    	g.drawImage(plant, (int)(this.position.x + 15), (int)(this.position.y - 50), null);
    }//drawItem
}//class GrassPlatform