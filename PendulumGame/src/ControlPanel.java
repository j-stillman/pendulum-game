import java.awt.Color;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * @title ControlPanel
 * @author Jeremiah Stillman
 * @version 07/16/24
 * @description This is a JPanel child that has its own buttons and controls. It is mounted to the left side of the 
 * screen.
 */

public class ControlPanel extends JPanel 
{
	
	//===================================================================================================================================================
	// Fields
	
	public static final Color CONTROL_PANEL_COLOR = new Color(255, 120, 255); //Color.ORANGE;
	
	private PendulumControlPanel pendulumControl;
	private MyFrame frame;		
	private PendulumSystem pendulumSystem;
	private ContentPanel contentPanel;
	
	private JButton playPauseButton;
	
	//===================================================================================================================================================
	// Constructor
	
	public ControlPanel(PendulumSystem pendulumSystem) 
	{
		super();
		
		// Set the layout aligned left
		setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		// set background
		setBackground(CONTROL_PANEL_COLOR);
		
		// create the pendulum system that the pendulumcontrolpanel will be tied to.
		this.pendulumSystem = pendulumSystem;
		
		
		
		// Create a content panel that holds ALL buttons, which can be laid out without being spread.
		contentPanel = new ContentPanel(this.pendulumSystem);
		
		
		
		// Add that content panel
		add(contentPanel);
		
		// set it to be there
		setVisible(true);
		
	}// end ControlPanel()
	
	
	//===================================================================================================================================================
	// Behaviour
	
	public PendulumControlPanel getPendulumControlPanel() 
	{
		// method that returns our pendulum control panel
		return pendulumControl;
		
	}// end getPendulumControlPanel()
	
	
}// end ContentPanel
