import java.awt.Color;
import java.awt.Graphics;

/**
 * @title Pendulum
 * @author Jeremiah Stillman
 * @version 04/17/18
 * @description This class is a swinging arm with a center, radius, and speed. It swings around the center, 
 * marking its path by creating a Line instance each frame.
 */

public class Pendulum implements Entity {

	//=========================================================================================================
	// Fields
	
	private int hierarchy;									// the position in the group of pendulums it is in.
	private boolean visible;								// if we draw the pendulum or not
	private int listIndex;									// the position in the group of pendulums it is in.
	private int x, y;										// the point of the end of the pendulum
	private int anchorX, anchorY;							// the point that it rotates around
	private double radius;									// the length of the swinging arm
	private double angle, angularSpd, originalAngle;		// the angle and the speed that it rotates
	
	private PendulumSystem mySystem;						// the pendulum system that this pendulum is apart of. used for determining
															// which pendulum is the anchor.
	
	private Pendulum myAnchor;				// The pendulum that this instance rotates around.
											// if nothing is passed into the constructor, anchorx and y will be the center of the screen.
	private LineSystem lineSystem;							// the line system that it draws to.
	
	//=========================================================================================================
	// Constructor
	
	public Pendulum(Pendulum myAnchor, double radius, double angularSpd, double angle) {
		// Construct a pendulum with a center as another pendulum object.
		
		// set the anchor
		this.myAnchor = myAnchor;
		
		// set the radius, speed, starting angle
		this.radius = radius;
		this.angularSpd = angularSpd;
		this.originalAngle = angle;
		
		this.angle = this.originalAngle;
		
		// find the anchor position depending on the type of anchor we have, as well as setting the actual position
		setAnchorPosition();
		setPosition();
		
	}
	
	public Pendulum() {
		// No parameters in this constructor. Default.
		
		this(null, 128, 5, 0);
		
	}
	
	//=========================================================================================================
	// Behaviours
	
	public void create() {
		// In this method, it will create or recreate the pendulum at the given position in the constructor.
		
		angle = originalAngle;			// leave its angle as the starting angle
		
		// find and set the position of the anchor
		setAnchorPosition();
		
		// set position as a result
		setPosition();					// This is all we need to do. since these are called back to back,
										// the setAnchorPosition() function will get the anchorX and anchorY based off of
										// the pendulum before it in the list. 
		
	}
	
	public void update() {
		// method that is called every frame.
		
		// update the angle based off of speed
		/*angle += angularSpd;
		
		// restrain the angle between 0 and 360 inclusive (?) that doesn't seem mathematically
		// right for some reason but it should be fine hahahhahahhahahha
		if (angle > 360) {
			angle -= 360;
		}
		if (angle < 0) {
			angle += 360;
		}*/
		angle = (angle + angularSpd) % 360;
		
		// 
		int xPrev = x;
		int yPrev = y;
		
		// with that, get the position of our anchor and set the position of our pendulum end
		setAnchorPosition();
		setPosition();
		
		// draw a line between our last position and our new one
		try{
			// if there IS a lineSystem set
			lineSystem.addLine(new Line(lineSystem, xPrev, yPrev, x, y));
		}catch(NullPointerException e) {
			// otherwise, don't do anything
			System.out.println("Line system has not yet been set. " + e);
		}
		
		
	}
	
	public void draw(Graphics g) {
		
		if (isVisible()) {
			
			g.setColor(Color.WHITE);
			g.fillOval(anchorX - 4, anchorY - 4, 8, 8);
			g.drawLine(anchorX, anchorY, x, y);
			
			// match the color with that of the lines
			g.setColor(this.lineSystem.getColor());
			g.fillOval(x - 8, y - 8, 16, 16);
			
		}
		
	}
	
	public void drawLineSystem(Graphics g) {
		// separate action to draw the line system
		
		this.lineSystem.draw(g);
		
	}
	
	public void setAnchorPosition() {
		// method that finds the anchor for the pendulum and sets it.
		
		try{
			// if there IS a pendulum for an anchor, then set the position to that pendulum's x and y
			anchorX = myAnchor.getX();
			anchorY = myAnchor.getY();
			
		}catch(NullPointerException e) {
			// if the anchor pendulum isn't a thing, then the anchor is the center of the screen
			anchorX = WorldPanel.getCenterX();
			anchorY = WorldPanel.getCenterY();
			
		}
	}
	
	public void setPosition() {
		// method that sets the position based off of anchorX and anchorY, and angle
		
		x = (int) (anchorX + radius * Math.cos(angle * Math.PI / 180));
		y = (int) (anchorY - radius * Math.sin(angle * Math.PI / 180));
		
	}
	
	public void setRadius(double r) {
		// method that sets the radius of the pendulum
		
		this.radius = r;
		
	}
	
	public void setAngularSpeed(double as) {
		// method that sets the angular speed to as
		
		this.angularSpd = as;
		
	}
	
	public void setOriginalAngle(double ang) {
		// method that sets the original angle
		
		this.originalAngle = ang;
		this.angle = this.originalAngle;
		
	}
	
	public void setAnchor(Pendulum p) {
		// Method that sets the anchor Pendulum. pass in null to make the anchor the center.
		
		this.myAnchor = p;
		
	}
	
	public int getHierarchy() {
		// method that gets the position in the group of pendulums
		
		return hierarchy;
		
	}
	
	public void setHierarchy(int hierarchy) {
		// method that sets the hierarchy
		
		this.hierarchy = hierarchy;
		
	}
	
	public void setListIndex(int index) {
		// method that sets the list index
		
		this.listIndex = index;
		
	}
	
	public int getListIndex() {
		// method that returns our list index
		return this.listIndex;
		
	}
	
	public int getX() {
		// method that returns this pendulum's x value.
		return x;
	
	}
	
	public int getY() {
		// method that returns this pendulum's y value
		return y;
		
	}
	
	public PendulumSystem getSystem() {
		// method that returns this pendulum's system.
		return mySystem;
		
	}
	
	public double getRadius() {
		// method that returns the radius
		return this.radius;
		
	}
	
	public double getAngularSpeed() {
		// method that returns the angular speed
		return this.angularSpd;
		
	}
	
	public double getAngle() {
		// method that returns the default angle
		return this.originalAngle;
		
	}
	
	public boolean isVisible() {
		// method that returns if visible or not
		return this.visible;
		
	}
	
	public void setVisible(boolean vis) {
		// method that sets the pendulum visible or not
		this.visible = vis;
		
	}
	
	public void setLineSystem(LineSystem lineSystem) {
		// set the line system.
		this.lineSystem = lineSystem;
	}
	
	public LineSystem getLineSystem() {
		// return the line system
		
		return this.lineSystem;
		
	}
	
	public String toString() {
		return "this is a pendulum with x and y of (" + x + ", " + y + ")";
	}
	
}
