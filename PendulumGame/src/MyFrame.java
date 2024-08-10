import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

public class MyFrame extends JFrame 
{

	//===================================================================================================================================================
	// Fields
	
	private WorldPanel gameWorld;
	private ControlPanel controlPanel;
	private PendulumSystem pendulumSystem;
	
	//===================================================================================================================================================
	// Constructor
	
	public MyFrame(String title, int w, int h, PendulumSystem pendulumSystem) 
	{
		// Construct it with a title and size
		
		super(title);									// call the super JFrame constructor
		setSize(w, h);									// set it to the width/height
		this.pendulumSystem = pendulumSystem;			// set the pendulum system for this frame
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	// set it to end the program once X is hit
		setResizable(false);							// no resizing so you don't break the line drawing..
		
		// Create two panels: A panel that represents the Game World, and a panel for the controls
		gameWorld = new WorldPanel();					// create the panel that contains the whole "world"
		
		WorldPanel.addEntity(this.pendulumSystem);		// add the pendulum system to the game world
		
		controlPanel = new ControlPanel(pendulumSystem);// create the control panel and map it to control this frame's pendulum system.
		
		// set the minimum size for the ControlPanel so you can't shrink it to be tiny
		controlPanel.setMinimumSize(new Dimension(256, 720));
		
		// put this control panel onto a scroll pane so that when you have a huge list of pendulums you can still navigate them.
		JScrollPane controlPanelScrolling = new JScrollPane(controlPanel);
		controlPanelScrolling.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		controlPanelScrolling.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		controlPanelScrolling.setMinimumSize(new Dimension(256, 720));
		controlPanelScrolling.setVisible(true);
		
		// Create a SplitPane to separate these two panels
		JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, controlPanelScrolling, gameWorld); 
		
		// prevent it from moving in any way...
		split.setEnabled(false);
		split.setOneTouchExpandable(false);

		
		// add this split panel thing to our window
		getContentPane().add(split);
		
		// set it visible
		setVisible(true);
		
	}// end MyFrame()
	
	public MyFrame(String title, Dimension dim, PendulumSystem pendulumSystem) 
	{
		// Construct with a title and size as a Dimension
		
		this(title, (int) dim.getWidth(), (int) dim.getHeight(), pendulumSystem);		// call the same constructor but with the dimension we set
		
	}// end MyFrame()
	
	//===================================================================================================================================================
	// Behaviours
	
	
	//===================================================================================================================================================
	// Accessors
	
	public ControlPanel getControlPanel() 
	{
		// method that gets our control panel for this window
		return controlPanel;
	}// end getControlPanel()
	
	public WorldPanel getWorldPanel() 
	{
		// method that gets our world panel for this window
		return gameWorld;
	}// end getWorldPanel()
 	
}// end MyFrame
