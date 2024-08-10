import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

/**
 * @title ContentPanel
 * @author Jeremiah Stillman
 * @version 07/17/24
 * @description This class is basically a panel inside ControlPanel that holds all the buttons...
 * I have to do this so that it recognizes everything as a group and does not decide that ControlPanel is the whole container.
 * Otherwise, using BoxLayout will spread it across the whole left side, which looks wrong and ugly.
 */

public class ContentPanel extends JPanel 
{
	
	//=======================================================================================================
	// Fields
	
	private PendulumSystem pendulumSystem;
	private PendulumControlPanel pendulumControlPanel;
	private PlayPauseButton playPauseButton;
	private AddPendulumButton addPendulumButton;
	private JCheckBox showPendulums;
	
	//=======================================================================================================
	// Constructor
	
	public ContentPanel(PendulumSystem ps) 
	{
		// Constructor that takes in and sets pendulumSystem to ps.
		
		// Call JPanel super constructor
		super();

		// set the target pendulum system
		this.pendulumSystem = ps;
		
		// set the background
		setBackground(ControlPanel.CONTROL_PANEL_COLOR);
		
		// set the layout
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		// create the pendulum control panel within this panel
		pendulumControlPanel = new PendulumControlPanel(this.pendulumSystem);
		
		add(pendulumControlPanel);
		
		// Create the JPanel that holds the bottom two buttons play/pause and add pendulum
		JPanel simulationControl = new JPanel();
		simulationControl.setBackground(ControlPanel.CONTROL_PANEL_COLOR);
		simulationControl.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		// Add the "Add Pendulum" button.
		Icon plusIcon = new ImageIcon(getClass().getResource("plus.png"));
		
		addPendulumButton = new AddPendulumButton("Add Pendulum", plusIcon);
		addPendulumButton.addActionListener(new MyActionListener(addPendulumButton) {
			// create an action listener that listens for clicks of the add pendulum button
			
			public void actionPerformed(ActionEvent ae) 
			{
				// have the PendulumSystem add a new Pendulum and PendulumPropertiesPanel to its list
				
				PendulumSystem masterSystem = Client.pendSystem;	// get a reference for the main pendulum system
				
				masterSystem.add();					 				// The PendulumPropertiesPanel has been added, but it is not visible.
				pendulumControlPanel.refreshPropertiesPanels();	 	// call this to go through the list, remove them all, then get them all back.
				
			}// end actionPerformed()
			
		});

		// Next, add the play button
		
		Icon playButtonIcon = new ImageIcon(getClass().getResource("play.png"));
		Icon stopButtonIcon = new ImageIcon(getClass().getResource("stop.png"));
		playPauseButton = new PlayPauseButton("Play", playButtonIcon, stopButtonIcon);
		playPauseButton.addActionListener(new MyActionListener(playPauseButton) {
			// create an action listener for the play/pause button that starts/stops 
			// the simulation
			
			public void actionPerformed(ActionEvent ae) 
			{
				// toggle running/not running
				
				PendulumSystem masterSystem = Client.pendSystem;
				
				if (masterSystem.isRunning()) {
					masterSystem.setRunning(false);
				}else{
					masterSystem.setRunning(true);
				}
				
			}// end actionPerformed()
			
		});
		
		pendulumSystem.setVisible(true);
		showPendulums = new JCheckBox("Show pendulums");
		showPendulums.setSelected(true);
		showPendulums.setBackground(ControlPanel.CONTROL_PANEL_COLOR);
		showPendulums.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent ie) {
				
				if (ie.getSource() == showPendulums) {
					PendulumSystem masterSystem = Client.pendSystem;
					masterSystem.setVisible(showPendulums.isSelected());
				}
				
				
			}
		});
		
		
		// here, i kinda gotta deviate from neatness (apologies) and add the playPauseButton instance to pendulumSystem.
		pendulumSystem.setPlayPauseButton(playPauseButton);
		
		// another instance of me doing the same thing as above, but with the check box
		pendulumSystem.setShowPendulumsBox(showPendulums);
		
		// set these buttons visible
		addPendulumButton.setVisible(true);
		playPauseButton.setVisible(true);
		showPendulums.setVisible(true);

		// add these buttons to their panels
		simulationControl.add(addPendulumButton);
		simulationControl.add(playPauseButton);
		//simulationControl.add(showPendulums);
		
		// set simulationControl visible and add it!
		simulationControl.setVisible(true);
		
		add(simulationControl);
		add(showPendulums);
		
	}// end ContentPanel()
	
	//=======================================================================================================
	// Behaviours
	
	
}// end ContentPanel
