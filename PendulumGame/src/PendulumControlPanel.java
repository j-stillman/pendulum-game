import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 * @title PendulumControlPanel
 * @author Jeremiah Stillman
 * @version 07/17/24
 * @description This class is a JPanel that contains many panels that control the properties of each Pendulum in a 
 * PendulumSystem's list.
 */
public class PendulumControlPanel extends JPanel 
{
	
	//=================================================================================================
	// Fields
	
	private PendulumSystem pendulumSystem;			// the system that this control panel is tied to.
	
	//=================================================================================================
	// Constructor
	
	public PendulumControlPanel(PendulumSystem ps) 
	{
		// Regular constructor. must pass in a pendulum system to add each time.
		super();
		
		// set the layout to be vertical
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		// set the background color
		setBackground(ControlPanel.CONTROL_PANEL_COLOR);
		
		// set the pendulum system to be the given system.
		this.pendulumSystem = ps;
		
		this.pendulumSystem.setPendulumControlPanel(this);		// set the pendulum system to be tied to this panel.
		
		// loop through and add each of the properties panels to this control panel.
		refreshPropertiesPanels();
		
		// set it visible
		setVisible(true);
		
	}// end PendulumControlPanel()
	
	//=================================================================================================
	// Behaviours
	
	public void refreshPropertiesPanels() 
	{
		// method that removes all of our properties panels, then brings them back, which will show the new one
		// in the right circumstances.
		
		for(int i = 0; i < pendulumSystem.getSize(); i++) {
			
			PendulumPropertiesPanel panel = pendulumSystem.getPendulumPropertiesPanel(i);	// get the properties panel in the list
			
			panel.setVisible(true);
			add(panel);																		// add that properties panel onto THIS panel.
			
		}
		
		// sooooo for some reason it only truly adds the properties panel when i run the simulation, which is weird!
		// my best bet is to just run it and unrun it...........................
		pendulumSystem.setRunning(true);
		pendulumSystem.setRunning(false);
		
		System.out.println("called.");

	}// end refreshPropertiesPanels()
	
	
}// end PendulumControlPanel()
