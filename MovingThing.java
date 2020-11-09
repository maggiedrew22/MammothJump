//Class for anything in the game that moves
//Code adapted from KeyboardSpheres lab
public abstract class MovingThing{  
    //Calls on pair class so that each MovingThing tracks its own velocity, position, etc.
    Pair position; 
    Pair velocity;
    Pair acceleration;

    public abstract void update(World w, double time); //abstract update method serves as base for subclasses
    
    //methods to set and return position, velocity and acceleration
    public void setPosition(Pair p){
	position = p;
    }//set Position
    public void setVelocity(Pair v){
	velocity = v;
    }//setVelocity
    public void setAcceleration(Pair a){
	acceleration = a;
    } //setAcceleration
    public Pair getPosition(){
	return position;
    }//getPosition
    public Pair getVelocity(){
	return velocity;
    }//getVelocity
    public Pair getAcceleration(){
	return acceleration;
    }//getAcceleration   
}//class MovingThing

//class to define a coordinate pair of doubles
class Pair{ 
    public double x;
    public double y;
    
    public Pair(double initX, double initY){
	x = initX;
	y = initY;
    }//Pair

    //methods to make various changes to x and y
    public Pair add(Pair toAdd){
	return new Pair(x + toAdd.x, y + toAdd.y);
    }//add

    public Pair divide(double denom){
	return new Pair(x / denom, y / denom);
    }//divide

    public Pair times(double val){
	return new Pair(x * val, y * val);
    }//times
    
    public void resetY(){
	   y = -600;
    }//resetY

    public void zeroY(){ 
        y = 20; 
    }//zeroY

    public void flipx(){
        x = -x;
    }//flipx

    public void chooseY(double d){
        y = d;
    }//chooseY
}//class Pair