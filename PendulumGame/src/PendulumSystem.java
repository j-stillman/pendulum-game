import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JCheckBox;

/**
 * @title PendulumSystem
 * @author Jeremiah Stillman
 * @version 07/17/24
 * @description This class is a manager that controls a list of Pendulum objects, as well as controlling the
 * PendulumPropertiesPanels.
 */

public class PendulumSystem implements Entity 
{

	
	//=========================================================================================================
	// Fields

	private MyFrame frame;												// a source frame that it adds pendulum properties panels to.
	
	private int systemSize;												// the size of our list. It shouldn't ever be less than 1.
	private ArrayList<PendulumPropertiesPanel> pendulumPropertiesList;	// The list of properties panels that we have.
	private ArrayList<Pendulum> pendulumList;							// the list of pendulums that appear in the game world
	
	private PendulumControlPanel pendulumControlPanel;					// reference for the control panel
	private PlayPauseButton playPauseButton;							// reference for the play/pause button that is on the control panel.
	private JCheckBox showPendulumsBox;									// reference for the check box that sets all the pendulums visible from the control panel.
	private boolean running;											// if the simulation is actually running.
																		// we check for this in the update method. otherwise, it'll just
																		// draw it.

	private boolean visible;											// boolean to make it visible or not.
	
	private Color[] cols = {
		Color.RED,
		new Color(255, 120, 0),
		Color.YELLOW,
		new Color(51, 216, 36),
		new Color(0, 161, 255),
		new Color(255, 0, 255),
		new Color(0, 255, 160),
		new Color(255, 50, 255)
	};
	
	//=========================================================================================================
	// Constructor
	
	public PendulumSystem() 
	{
		// Constructor. Must pass in the frame that we will be adding all this stuff to.
		
		setRunning(false);
		//this.frame = frame;													// set the frame source to be the passed in MyFrame
		
		// Create the array list of pendulums, and an array list of pendulum properties panels
		pendulumList = new ArrayList<Pendulum>();								// instantiate the list
		pendulumPropertiesList = new ArrayList<PendulumPropertiesPanel>();		// instantiate the list
		
		add();																	// use our add method to add a regular pendulum to the system
		
		this.visible = true;
		
		// make it visible
		setVisible(true);
		
	}// end PendulumSystem()
	
	//=========================================================================================================
	// Behaviours
	
	public void add(double rad, double spd, double ang) 
	{
		
		// create a reference for the anchor that we might already have. 
		Pendulum anchor;
		
		try{
			// if we do have an anchor instance in this list coming before the one we're adding, use that.
			anchor = pendulumList.get(pendulumList.size() - 1);
		}catch(IndexOutOfBoundsException | NullPointerException e) {
			// otherwise, it will not have an anchor.
			anchor = null;
		}
		
		// create the pendulum we're gonna add
		Pendulum pend = new Pendulum(anchor, rad, spd, ang);
		
		// give the pendulum a line system to draw to
		LineSystem ls = new LineSystem(pend);										// create instance
		//WorldPanel.addEntity(ls);													// add it to the world panel
		pend.setLineSystem(ls);														// set the line system
		
		pendulumList.add(pend);														// add the actual pendulum
		
		PendulumPropertiesPanel pendControl = new PendulumPropertiesPanel(this, pend);	// create a properties panel to control this pendulum
		pendulumPropertiesList.add(pendControl);									// add that panel to our list of properties panels.
		pendControl.setVisible(true);
		
		// reset the pendulum's placement in the list. (PendulumSystem.listIndex)	
		setPendulumPlacement();		
		
		systemSize = pendulumList.size();									// refresh our size
		
		// reset the visibility so all the pendulums are in key with eachother
		setVisible(true);					// will go through and set it again
		
	}// end add()
	
	public void add() 
	{
		
		// Method that performs the same function as add() but without arguments. Fills in global defaults
		this.add(Client.DEFAULT_RADIUS, Client.DEFAULT_SPEED, Client.DEFAULT_ANGLE);
		
	}// end add()
	
	public void remove(int index) 
	{
		// method that removes a pendulum from the list. 

		if (index < 0 || index >= pendulumList.size()) {
			// make sure the index passed in is legal.
			throw new IllegalArgumentException();
		}
		
		// create new array lists to be filled and to replace our old pendulum lists.
		ArrayList<Pendulum> newPendulumList = new ArrayList<Pendulum>();
		ArrayList<PendulumPropertiesPanel> newPendulumPropertiesList = new ArrayList<PendulumPropertiesPanel>();
		
		for(int i = 0; i < pendulumList.size(); i++) {
			
			// get the references for the pendulums we're looking at for this iteration
			Pendulum p = pendulumList.get(i);
			PendulumPropertiesPanel ppp = pendulumPropertiesList.get(i);
			
			if (i != index) {
				// only add the "new" pendulums if it isn't the one that we're trying to delete.
				
				newPendulumList.add(p);								// add the pendulum to the list
				newPendulumPropertiesList.add(ppp);					// add the pendulum properties to the list
				
			}
			
		}
		
		// make our pendulumList and propertiesList the new ones created above.
		pendulumList = newPendulumList;
		pendulumPropertiesList = newPendulumPropertiesList;
		
		setPendulumPlacement();
		
		// shift the pendulum anchors down a bit
		for(int i = 0; i < pendulumList.size(); i++) {
			Pendulum p = pendulumList.get(i);
			
			try{
				// set our anchor to be the one that comes before it in the list if there is one
				p.setAnchor(pendulumList.get(i - 1));
			}catch(ArrayIndexOutOfBoundsException | NullPointerException e) {
				// otherwise, this one must be in the center, so set its anchor to null
				p.setAnchor(null);
			}
			
			// refresh the pendulum's position by having them each check for the position of their anchor and setting that position accordingly.
			p.setAnchorPosition();
			p.setPosition();
		}
		
		// reset the visibility so all the pendulums are in key with eachother
		setVisible(true);
		
	}// end remove()
	
	public Pendulum get(int index) 
	{
		// method that returns the index of the pendulum in our list.
		
		if (index < 0 || index >= systemSize) {
			// make sure the index passed in is legal.
			throw new IllegalArgumentException();
		}
		
		return pendulumList.get(index);
		
	}// end get()
	
	@Override
	public void create() 
	{
		// TODO Auto-generated method stub
		// Method that refreshes all the pendulum positions
		
		for(int i = 0; i < pendulumList.size(); i++) {
			// loop through our list of pendulums
			
			Pendulum p = pendulumList.get(i); 				// get the pendulum instance from the list
			p.create();										// have that pendulum perform its create event
			
		}
		
	}// end create()

	@Override
	public void update() 
	{
		// TODO Auto-generated method stub
		// Loop through our list of pendulums and have them call THEIR update method
		
		if (running) {
			// if this is running, we will call the update function.
			
			for(int i = 0; i < pendulumList.size(); i++) {
				// loop through our list of pendulums
				
				Pendulum p = pendulumList.get(i); 				// get the pendulum instance from the list
				p.update();										// have that pendulum perform its update event
				
			}
			
		}else{
			// otherwise, call the create method to constantly keep it in a default state.
			
			for(int i = 0; i < pendulumList.size(); i++) {
				// loop through our list of pendulums
				
				Pendulum p = pendulumList.get(i); 				// get the pendulum instance from the list
				p.create();										// have that pendulum perform its create event
				
			}
			
		}

	}// end update()

	@Override
	public void draw(Graphics g) 
	{
		// TODO Auto-generated method stub
		// Go through and call our pendulum's draw method, only if we are indeed visible
		
		if (isVisible()) {
			
			for(int i = 0; i < pendulumList.size(); i++) {
				// loop through the list of pendulums, drawing their lines first
				
				Pendulum p = pendulumList.get(i);				// get the pendulum instance from the list
				p.drawLineSystem(g);							// have it draw its own lines
				
			}
			
			// now that lines are drawn first, pendulums will appear on top.
			
			for(int i = 0; i < pendulumList.size(); i++) {
				// loop through our list of pendulums
				
				Pendulum p = pendulumList.get(i); 				// get the pendulum instance from the list
				p.draw(g);										// have that pendulum perform its draw event
				
			}	
			
		}
		
	}// end draw()
	
	public void setRunning(boolean running) 
	{
		// method that sets if the simulation is running
		
		this.running = running;								// set running's value
		
		try{
			// only allow this to run when we have a playPauseButton instance
			playPauseButton.setText(isRunning());			// use this method to also set the play/pause button's text.	
		}catch(NullPointerException e) {
			// otherwise, move on but make note of it
			System.out.println("Play/Pause button has not yet been plugged into PendulumSystem. Error: " + e);
		}
		
		
		// now, here i will have the WorldPanel remove all the lines if running is true, to refresh it.
		if (isRunning()) {
			for(int i = 0; i < pendulumList.size(); i++) {
				pendulumList.get(i).getLineSystem().clear();
			}
		}
		
	}// end setRunning()
	
	public void setPlayPauseButton(PlayPauseButton playPauseButton) 
	{
		// method that gives a reference for the button we're using.
		this.playPauseButton = playPauseButton;
	}// end setPlayPauseButton()
	
	public void setShowPendulumsBox(JCheckBox showPendulums) 
	{
		// method that sets the reference for our check box that toggles showing the pendulums
		this.showPendulumsBox = showPendulums;
		
	}// end setShowPendulumsBox()
	
	public void setPendulumControlPanel(PendulumControlPanel control) 
	{
		// method that gives a reference for what pendulum control panel is using this instance.
		this.pendulumControlPanel = control;
		
	}// end setPendulumControlPanel()
	
	public void setPendulumPlacement() 
	{
		// method that goes through the list and sets the placement to be nice and linear.
		
		for(int i = 0; i < pendulumList.size(); i++) {
			// loop through the list, setting the pendulum's level in the list
			
			Pendulum p = pendulumList.get(i);
			PendulumPropertiesPanel ppp = pendulumPropertiesList.get(i);
			
			// set the order of our pendulum, and set the color of its line system.
			p.setListIndex(i);
			p.getLineSystem().setColor(getColorIndex(i));
			
			// set the order of our pendulum properties panel.
			ppp.setListIndex(i);
			
		}
	}// end setPendulumPlacement()

	
	//=========================================================================================================
	// Accessors
	
	public int getSize() 
	{
		// method that returns the systemSize variable.
		return systemSize;
	}// end getSize()
	
	public PendulumPropertiesPanel getPendulumPropertiesPanel(int index) 
	{
		// method that returns the properties panel in the given index
		return pendulumPropertiesList.get(index);
	}// end getPendulumPropertiesPanel()
	
	public PendulumControlPanel getPendulumControlPanel() 
	{
		// method that returns our control panel that contains the whole list...
		return pendulumControlPanel;	
	}// end getPendulumControlPanel()
	
	public Pendulum getPendulum(int index) 
	{
		// method that returns a pendulum at the given index
		return pendulumList.get(index);
	}// end getPendulum()
	
	public Color getColorIndex(int index) 
	{
		// method that returns our color at the given index from our array.
		
		int newIndex = index;				// a modifiable variable for getting the actual index we're on.
		
		// if the index is greater than the size of our total colors, loop around until its in "standard
		// position" i guess you could say.
		while(newIndex >= this.cols.length) {
			newIndex -= cols.length;
		}
		
		// return the colors at the new index.
		return cols[newIndex];
		
	}// end getColorIndex()
	
	public boolean isVisible() 
	{
		// method to return if visible or not
		return this.visible;
		
	}// end isVisible()
	
	public void setVisible(boolean vis) 
	{
		// method that sets the system visible
		// since pendulums have to draw their own line system, only set the pendulums in the list invisible.
		// in pendulum's draw code, you will see that drawing the line system is outside the isVisible() block.
		
		for(int i = 0; i < pendulumList.size(); i++) {
			// go through the list, setting each one invisible or not
			pendulumList.get(i).setVisible(vis);
			
		}
		
		// if we have an instance plugged into the showPendulums box, set that box's state.
		try{
			showPendulumsBox.setSelected(vis);
		}catch(NullPointerException e) {
			System.out.println("Show pendulums checkbox has not yet been plugged into PendulumSystem. " + e);
		}
		
		
	}// end setVisible()
	
	public void saveSystem(String filename) 
	{
		// method that saves the system into a text file
		
		
		
	}// end saveSystem()
	
	public boolean isRunning() 
	{
		// method that returns if the program is running
		return running;
		
	}// end isRunning()
	
	public String toString() 
	{
		return "This is a system";
	}// end toString()
	

}// end PendulumSystem
