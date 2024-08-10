import java.awt.Graphics;

public interface Entity 
{

	public void create();				// creation code. run at the start of the object's life.
	public void update();				// frame code. run every single frame.
	public void draw(Graphics g);		// drawing method. run in order to display the object.
		
}// end Entity
