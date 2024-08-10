import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 * @title LineSystem
 * @author Jeremiah Stillman
 * @version 07/19/24
 * @description This class holds a list of lines that appear on the screen as a result of the pendulums
 * moving and drawing their paths
 */
public class LineSystem implements Entity 
{

	//=============================================================================================================
	// Fields
	
	private int x, y;							// position
	private ArrayList<Line> lineSystem;			// list of lines that will draw
	private Pendulum myPendulum;				// pendulum that this is connected to
	private Color myColor;						// color that the lines are drawn in
	private boolean visible;					// boolean to check if visible. if not, then it will not draw.
	
	
	//=============================================================================================================
	// Constructor
	
	public LineSystem(Pendulum myPendulum) 
	{
		// Construct the system
		
		myColor = Color.RED;
		
		// set the pendulum
		setPendulum(myPendulum);
		
		// set the center right away.
		x = WorldPanel.getCenterX();
		y = WorldPanel.getCenterY();
		
		// set the list
		lineSystem = new ArrayList<Line>();		// create the array list of lines
		
		// is visible.
		setVisible(true);
		
	}// end LineSystem()
	
	//=============================================================================================================
	// Behaviours
	
	public void create() {
		
	}// end create()
	
	public void update() 
	{
		
		// set the position to whatever the center of the screen is
		x = WorldPanel.getCenterX();
		y = WorldPanel.getCenterY();
		
		// have our lines refresh their position
		for(int i = 0; i < lineSystem.size(); i++) {
			lineSystem.get(i).update();
		}
		
	}// end update()
	
	public void draw(Graphics g) 
	{
		// go through our list and draw the lines
		
		if (isVisible()) {
			// only run this if visible
			
			x = WorldPanel.getCenterX();
			y = WorldPanel.getCenterY();
			
			// set our color
			g.setColor(myColor);
			
			// start going through the list. color will be passed through from that above.
			for(int i = 0; i < lineSystem.size(); i++) {
				
				lineSystem.get(i).draw(g);
				
			}
			
		}
		
	}// end draw()
	
	public void setPendulum(Pendulum myPendulum) 
	{
		// method that sets the pendulum to the parameter
		
		this.myPendulum = myPendulum;
		
	}// end setPendulum()
	
	public Pendulum getPendulum() 
	{
		// method that returns what pendulum we have
	
		return this.myPendulum;
		
	}// end getPendulum()
	
	public void addLine(Line line) 
	{
		// method that adds a new line to the list with its coordinates.
		
		line.setLineSystem(this);
		lineSystem.add(line);							// add the line to our system.
		
	}// end addLine()
	
	public void clear() 
	{
		// method that gets rid of all the lines.
		
		lineSystem = new ArrayList<Line>();				// create a new, blank list
		
	}// end clear()
	
	public int getX() 
	{
		// method that returns our x value.
		
		return x;
		
	}// end getX()
	
	public int getY() 
	{
		// method that returns our y values
		
		return y;
		
	}// end getY()
	
	public void setColor(Color c) 
	{
		// method that sets the color for the line system.
		
		this.myColor = c;
		
	}// end setColor()
	
	public Color getColor() 
	{
		// method that returns our color
		
		return this.myColor;
		
	}// end getColor()
	
	public void setVisible(boolean vis) 
	{
		// method to set it visible or not
		this.visible = vis;
	}// end setVisible()
	
	public boolean isVisible() 
	{
		// method that checks if it is visible
		return this.visible;
	}// end isVisible()
	
}// end LineSystem
