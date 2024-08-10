import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

/**
 * @title WorldPanel
 * @author Jeremiah Stillman
 * @version 07/16/24
 * @description A panel that runs the Game world so you can SEE what's going on.
 */

public class WorldPanel extends JPanel 
{
	
	//===================================================================================================================================================
	// Fields
	
	public static int panelW, panelH;							// These values indicate the current width and height of the screen.
	public static int centerX, centerY;							// These values indicate the coordinates of the center of the screen.

	private static ArrayList<Entity> gameWorld;					// The ArrayList that contains all the objects in the world.
	private static boolean running;								// the boolean that determines if we're running the simulation
	
	//===================================================================================================================================================
	// Constructors
	
	public WorldPanel() 
	{
		// Create it without the parameters
		super();			// Call JPanel's constructor.

		// Instantiate the gameWorld
		gameWorld = new ArrayList<Entity>();
		
		// Start off by leaving it NOT running.
		startSimulation();
		
		setBackground(Color.BLACK);
	
		setVisible(true);
		
		// set the coordinates of the center
		setCenter();
		
	}// end WorldPanel()
	
	//===================================================================================================================================================
	// Behaviours

	public void stopSimulation() 
	{
		// method that ends the animating, thus setting running to FALSE
		
		reset();
		repaint();
		
		running = false;
		
	}// end stopSimulation()
	
	public void startSimulation() 
	{
		// method that starts the animating, thus setting running to TRUE
		running = true;
		
	}// end startSimulation()
	
	public static boolean isRunning() 
	{
		// method that returns if we're running or not
		return running;
		
	}// end isRunning()
	
	public void reset() 
	{
		// method that loops through all the objects and calls their create methods to set them back to how they were.
		
		// find the center of the screen
		setCenter();
		
		for(int i = 0; i < gameWorld.size(); i++) {
			Entity obj = gameWorld.get(i);				// return the id for the object
			
			obj.create();								// have that object do its update function
		}
		
	}// end reset()
	
	public void act() 
	{
		// method that loops through and has the objects perform their actions
		
		// get the size of the panel and set centerX and centerY.
		setCenter();

		// retrieve each entity and call the update method
		for(Entity obj : gameWorld) {
			obj.update();								// have that object do its update function
		}
		
	}// end act()
	
	public void paint(Graphics g) 
	{
		// override the paint method.
		super.paint(g);				// call regular paint method
		
		// go through and draw the objects
		paintEntities(g);
		
	}// end paint()
	
	public void paintEntities(Graphics g) 
	{
		// Method that loops through our array list and draws them
		
		for(Entity obj : gameWorld) {
			
			obj.draw(g); 								// call the draw method for the object we've got.
 			
		}
		
	}// end paintEntities()
	
	public static void addEntity(Entity obj) 
	{
		// method that adds an entity to the list of objects in the gameWorld list
		
		System.out.println("about to add.");
		try{
			gameWorld.add(obj);		// add the entity
		}catch(NullPointerException e) {
			System.out.println("Exception caught: " + e);
		}
		
	}// end addEntity()
	
	public void setCenter() 
	{
		// method that finds the dimensions of the panel and sets the center coordinates.
		
		panelW = getWidth();			// find the width
		panelH = getHeight();			// find the height
		
		centerX = panelW / 2;			// set centerX as half the width.
		centerY = panelH / 2; 			// set centerY as half the height.
		
	}// end setCenter()
	
	public static int getCenterX() 
	{
		// method that returns the x coordinate of the center
		return centerX;
		
	}// end getCenterX()
	
	public static int getCenterY() 
	{
		// method that returns the y coordinate of the center
		return centerY;
		
	}// end getCenterY()
	
}// end WorldPanel()
