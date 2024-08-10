import java.awt.Graphics;

/**
 * 
 * @author Jeremiah Stillman
 * @version 07/19/24
 * @description This class is an object that draws a line segment.
 *
 */
public class Line implements Entity 
{

	//=============================================================================================================
	// Fields
	
	private LineSystem myLineSystem;				// reference for the line system that we draw to.
	private int centerX, centerY;					// center of the screen variables
	private int x1, y1, x2, y2;						// coordinate variables.
	private int x1Dst, y1Dst, x2Dst, y2Dst;			// variables that represent the relative distance from the center of the screen.
	
	//=============================================================================================================
	// Constructor
	
	public Line(LineSystem myLineSystem, int x1, int y1, int x2, int y2) 
	{
		// construct it
		
		setLineSystem(myLineSystem);
		
		// get the center values
		centerX = this.myLineSystem.getX();
		centerY = this.myLineSystem.getY();
		
		// get the "absolute" coordinate.
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		
		// get the relative distance from the center
		this.x1Dst = this.x1 - centerX;
		this.y1Dst = this.y1 - centerY;
		this.x2Dst = this.x2 - centerX;
		this.y2Dst = this.y2 - centerY;
		
	}// end Line()
	
	//=============================================================================================================
	// Behaviours
	
	public void create() {
		
	}// end create()
	
	public void update() 
	{
		
		// set the position to the center
		centerX = myLineSystem.getX();
		centerY = myLineSystem.getY();
		
	}// end update()
	
	public void draw(Graphics g) 
	{
		
		// draw a line segment at our coordinates
		g.drawLine(centerX + x1Dst, centerY + y1Dst, centerX + x2Dst, centerY + y2Dst);
		
	}// end draw()
	
	public void setLineSystem(LineSystem myLineSystem) 
	{
		// method that sets our line system
		
		this.myLineSystem = myLineSystem;
		
	}// end setLineSystem()
	
	
}// end Line
