import java.awt.Graphics;

//The collectable interface outlines required methods for platforms with an item
public interface Collectable{
	public void collect(Mammoth tuskboi);
	public void drawItem(Graphics g);
	public void removeItem();
}//interface Collectable