import java.awt.Graphics;
import java.awt.Color;

//this is a subclass of the original platform class that creates platforms that continuously move horizontally
public class MovingPlatform extends Platform{
    public MovingPlatform(){
        super();
        this.velocity.x = 50;
    }//MovingPlatform

    //wall contact function that returns true if the platform contacts the wall
    public boolean contactwalls(World w, double time){ 
        if (this.position.x <= 0){
            return true;
        }
        if (this.position.x >= (w.width - this.width)){
            return true;
        }
        else{
            return false;
        }
    }//contactwalls

    //draws platform
    @Override   
    public void draw(Graphics g) {
        g.setColor(new Color(0, 12, 122));
        g.fillRect((int)(position.x), (int)(position.y), (int)(super.width), (int)(super.height));
    }//draw
}//class MovingPlatform