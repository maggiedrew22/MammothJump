import java.awt.Graphics;
import java.awt.Color;

//this is a subclass of the original platform class that creates platforms that break when jumped upon
public class BrokenPlatform extends Platform{

    //same constructor as platform class
    public BrokenPlatform(){
        super();
    }//BrokenPlatform

    //same update function as platform class (moves on screen in the same way)
    public void update(World w, double time){
        super.update(w, time);
    }//update


    //different contact function, does not advance new level or flip mammoth's velocity
    @Override
    public void contactFunction(World w, Mammoth tuskboi){
    	w.newlevel = false;
	}//contactFunction

    //draws broken platforms in red with a cracked line down the middle
    @Override
    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect((int)(position.x), (int)(position.y), (int)(super.width), (int)(super.height));
        int[] xcracks = new int[] {(int)position.x+40, (int)position.x+45, (int)position.x+35, (int)position.x+40};
        int[] ycracks = new int[] {(int)position.y, (int)position.y+3, (int)position.y+12, (int)(position.y+super.height)};
        g.setColor(Color.BLACK);
        g.drawPolyline(xcracks, ycracks, 4);

    }//draw

}//class BrokenPlatform