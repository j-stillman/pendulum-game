import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @title PendulumPropertiesPanel
 * @author Jeremiah Stillman
 * @version 07/17/24
 * @description This class is a JPanel that contains the actual properties for a pendulum. NOT the entire
 * list of control panels. Just a single one.
 *
 */

public class PendulumPropertiesPanel extends JPanel 
{
	
	//=================================================================================================
	// Fields
	
	private int listIndex;			// the index within the list of our pendulums that this control panel is. if it's 0,
									// it'll be impossible to delete it
	
	private PendulumControlPanel myController;	// the control panel that this properties panel is contained within
	private Pendulum myPendulum;	// The target pendulum that we are to affect when we press the buttons and change values
	private PendulumSystem myPendulumSystem;	// The system that this panel is being controlled by. used for removing a panel by the system.
	
	private JPanel namePanel,
				   radiusPanel,
				   speedPanel,
				   anglePanel,
				   deletePanel;		// These panels are what hold each pendulum's speed, angle, etc. so that it keeps its
									// structure. Otherwise, if they were added normally, they would be in a gross line that you can't see well.
	
	private JLabel name,
				   radius, 
				   speed,
				   angle;			// These labels appear before the text fields so you know which controller does what.
	
	private PendulumTextField enterName, 
					   		  enterRadius, 
					   		  enterSpeed, 
					   		  enterAngle;  // These text fields are for properties of the pendulum we edit.
									
	private DeleteButton delete;	// This button is pressed to delete this panel from the list.
	private DuplicatePendulumButton duplicate;	// The button that can be pressed to make a copy of the pendulum.
	private JCheckBox makeVisible;	// check box that will make a pendulum's line visible.
	
	//=================================================================================================
	// Constructor
	
	public PendulumPropertiesPanel(PendulumSystem myPendulumSystem, Pendulum myPendulum) 
	{
		// Regular constructor. must pass in a pendulum to target.
		super();
		
		// set the system controller
		setPendulumSystem(myPendulumSystem);
		
		// set the target pendulum
		setPendulum(myPendulum);
		
		// set the layout so that it doesn't look ugly
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		// Get the main color for the background
		Color c = ControlPanel.CONTROL_PANEL_COLOR;
		
		// set background color
		setBackground(c);
		
		// Create the panel for each of them
		namePanel = new JPanel();
		radiusPanel = new JPanel();
		speedPanel = new JPanel();
		anglePanel = new JPanel();
		deletePanel = new JPanel();
		
		// set the alignment for these panels
		FlowLayout panelAlign = new FlowLayout(FlowLayout.RIGHT);
		
		namePanel.setLayout(panelAlign);
		radiusPanel.setLayout(panelAlign);
		speedPanel.setLayout(panelAlign);
		anglePanel.setLayout(panelAlign);
		deletePanel.setLayout(panelAlign);
		
		// set these panels' background colors
		namePanel.setBackground(c);
		radiusPanel.setBackground(c);
		speedPanel.setBackground(c);
		anglePanel.setBackground(c);
		deletePanel.setBackground(c);
		
		// Create the components
		name = new JLabel("");
		radius = new JLabel("Radius");
		speed = new JLabel("Angular speed");
		angle = new JLabel("Initial Angle");
		
		//enterName = new JTextField("", 12);
		
		String rad, spd, ang;
		rad = Double.toString(this.myPendulum.getRadius());
		spd = Double.toString(this.myPendulum.getAngularSpeed());
		ang = Double.toString(this.myPendulum.getAngle());
		
		enterRadius = new PendulumTextField(rad, 10, this.myPendulum);
		enterSpeed = new PendulumTextField(spd, 10, this.myPendulum);
		enterAngle = new PendulumTextField(ang, 10, this.myPendulum);

		// Create action listeners for the text fields we created
		enterRadius.addActionListener(new MyActionListener(enterRadius) {
			// create a listener to reset the simulation and change myPendulum's values
			public void actionPerformed(ActionEvent ae) {
				// call the super action performed in order to stop the simulation.
				super.actionPerformed(ae);
				
				// now, affect the radius of our pendulum
				setRadius(ae, myPendulum);
				
			}
			
		});
		
		enterSpeed.addActionListener(new MyActionListener(enterSpeed){
			// Create a listener to reset the simulation and change myPendulum's values
			public void actionPerformed(ActionEvent ae) {
				// call the super action performed in order to stop the simulation.
				super.actionPerformed(ae);
				
				// now, affect the angular speed of our pendulum.
				setAngularSpeed(ae, myPendulum);
			}
		});
		
		enterAngle.addActionListener(new MyActionListener(enterAngle){
			// Create a listener to reset the simulation and change myPendulum's values
			public void actionPerformed(ActionEvent ae) {
				// call the super action performed in order to stop the simulation.
				super.actionPerformed(ae);
				
				// now, affect the starting angle of our pendulum.
				setOriginalAngle(ae, myPendulum);
			}
		});
		
		Icon xIcon = new ImageIcon(getClass().getResource("x.png"));
		delete = new DeleteButton("Delete", xIcon);
		delete.addActionListener(new MyActionListener(delete, this) {
			public void actionPerformed(ActionEvent ae) {
				
				//PendulumSystem ps = getMyPendulum().getSystem();

				getMyPendulumPanel().setVisible(false);
				getMyPendulumPanel().getMyPendulumSystem().remove(getMyPendulumPanel().getListIndex());
				
			}
		});
		
		
		Icon duplicateIcon = new ImageIcon(getClass().getResource("duplicate.png"));
		duplicate = new DuplicatePendulumButton("Duplicate", duplicateIcon);
		duplicate.addActionListener(new MyActionListener(duplicate, this.myPendulum) {
			public void actionPerformed(ActionEvent ae) {
				PendulumSystem masterSystem = Client.pendSystem;
				
				double rad, spd, ang;
				rad = myPendulum.getRadius();
				spd = myPendulum.getAngularSpeed();
				ang = myPendulum.getAngle();
				
				masterSystem.add(rad, spd, ang);
				masterSystem.getPendulumControlPanel().refreshPropertiesPanels();
			}
		});
		
		makeVisible = new JCheckBox("Show line");
		makeVisible.setBackground(c);
		makeVisible.setSelected(true);
		makeVisible.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent ie) {
				
				if (ie.getSource() == makeVisible) {
					// set the line system of the pendulum that this panel controls to be visible or not.
					myPendulum.getLineSystem().setVisible(makeVisible.isSelected());
				}
				
			}
		});
		
		// Set these components visible
		name.setVisible(true);
		radius.setVisible(true);
		speed.setVisible(true);
		angle.setVisible(true);
				
		//enterName.setVisible(true);
		enterRadius.setVisible(true);
		enterSpeed.setVisible(true);
		enterAngle.setVisible(true);
		
		delete.setVisible(true);
		makeVisible.setVisible(true);
		
		// add these components to their respective panels
		namePanel.add(name);
		//namePanel.add(enterName);
		radiusPanel.add(radius);
		radiusPanel.add(enterRadius);
		speedPanel.add(speed);
		speedPanel.add(enterSpeed);
		anglePanel.add(angle);
		anglePanel.add(enterAngle);
		deletePanel.add(makeVisible);
		deletePanel.add(duplicate);
		deletePanel.add(delete);
		
		
		// set the panels visible
		//namePanel.setVisible(true);
		radiusPanel.setVisible(true);
		speedPanel.setVisible(true);
		anglePanel.setVisible(true);
		deletePanel.setVisible(true);
		
		// add the panels to the parent panel of this class.
		add(namePanel);
		add(radiusPanel);
		add(speedPanel);
		add(anglePanel);
		
		add(deletePanel);

		// set all this visible
		setVisible(true);
		
	}// end PendulumPropertiesPanel()
	
	//=================================================================================================
	// Behaviours
	
	public void setPendulum(Pendulum p) 
	{
		// method that sets the pendulum that we are targeting.
		myPendulum = p;
		
	}// end setPendulum()
	
	public void setPendulumSystem(PendulumSystem ps) 
	{
		// method that sets what pendulum system is controlling the panel.
		this.myPendulumSystem = ps;
	}// end setPendulumSystem()
	
	public PendulumSystem getMyPendulumSystem() 
	{
		// method that returns what system is the controller
		return this.myPendulumSystem;
		
	}// end getMyPendulumSystem()
	
	public void setListIndex(int index) 
	{
		// method that sets the priority of the pendulum properties panel
		
		this.listIndex = index;
		this.delete.setListIndex(index);
		
	}// end setListIndex()
	
	public int getListIndex() 
	{
		// method that returns the priority of the pendulum properties panel.
		return listIndex;
		
	}// end getListIndex()
	
}// end PendulumPropertiesPanel()
