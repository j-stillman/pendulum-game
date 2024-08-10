import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

/**
 * @title MyActionListener
 * @author Jeremiah Stillman
 * @version 07/17/24
 * @description This class extends the ActionListener, and is used for the JTextFields that change
 * the pendulum properties. This class's ActionPerformed method first calls PendulumSystem's setRunning(false)
 * before performing any more actions
 */
public class MyActionListener implements ActionListener 
{

	//======================================================================================================================
	// Fields 
	
	private PendulumTextField myField;					// Reference for the field that we're running off of.
	private Pendulum myPendulum;						// Reference for the pendulum that a delete button will get the properties panel from
	private PendulumPropertiesPanel myPendulumPanel;	// Reference for the pendulum properties panel that is removed on clicking the delete button.
	private PendulumSystem myPendulumSystem;			// Reference for the pendulum system that we're calling remove from
	private DeleteButton deleteButton;					// Reference for the button that will delete a pendulum properties panel.
	private DuplicatePendulumButton myDuplicateButton;	// Reference for the button that duplicates the pendulum.
	private PlayPauseButton playPauseButton;			// Reference for the play pause button we press
	private AddPendulumButton addPendulumButton;		// Reference for the button that adds a pendulum to our system.
	
	//======================================================================================================================
	// Constructor
	public MyActionListener(PendulumTextField myField) 
	{
		// construct it like normal but set the text field
		super();
		
		setTextField(myField);
		
	}// end MyActionListener()
	
	public MyActionListener(DeleteButton deleteButton, PendulumPropertiesPanel ppp) 
	{
		// construct it like normal but set the delete button
		super();
		
//		setMyPendulumSystem(ps);
		setMyPendulumPanel(ppp);
		setDeleteButton(deleteButton);
		
	}// end MyActionListener()
	
	public MyActionListener(DuplicatePendulumButton b, Pendulum p) 
	{
		// construct it, setting a button, and a target pendulum
		
		setDuplicateButton(b);
		setMyPendulum(p);
		
	}// end MyActionListener()
	
	public MyActionListener(PlayPauseButton playPauseButton) 
	{
		// construct it like normal but set the play/pause button
		super();
		
		setPlayPauseButton(playPauseButton);
	}// end MyActionListener()
	
	public MyActionListener(AddPendulumButton addPendulumButton) 
	{
		// Construct it like normal but set an add pendulum button to listen for
		super();
		
		setAddPendulumButton(addPendulumButton);
	}// end MyActionListener()
	
	//======================================================================================================================
	// Behaviours

	public void actionPerformed(ActionEvent ae) 
	{
		// this is the super method, which will tell the PendulumSystem to stop running.
		Client.pendSystem.setRunning(false);
		
		// next, call the desired method from the PendulumProperties JTextFields.
	}// end actionPerformed()
	
	public void setRadius(ActionEvent ae, Pendulum p) 
	{
		// set the radius
		
		String txt = ae.getActionCommand();
		double rad;
		try{
			// if it's a valid entry, set rad to a double value.
			rad = Double.parseDouble(txt);	
		}catch(NumberFormatException e) {
			// otherwise, we'll have to just throw it away and set it back to what radius we did have.
			
			try{
				// if that doesn't work, maybe we entered an integer and should cast that to a double but i don't think its necessary.
				rad = (double) Integer.parseInt(txt);	
			}catch(NumberFormatException e2) {
				// otherwise, we'll just have to throw it away (FOR REAL)
				rad = p.getRadius();
			}
			
		}
		
		// now, set the textbox to have that value inside of it.
		myField.setText(Double.toString(rad));
		
		// finally, set the pendulum's radius
		p.setRadius(rad);
		
	}// end setRadius()
	
	public void setAngularSpeed(ActionEvent ae, Pendulum p) 
	{
		// set the angular speed for the pendulum
		
		String txt = ae.getActionCommand();
		double angSpd;
		try{
			// if it's a valid entry, set angSpd to a double value.
			angSpd = Double.parseDouble(txt);	
		}catch(NumberFormatException e) {
			// otherwise, we'll have to just throw it away and set it back to what angular speed we did have.
			
			try{
				// if that doesn't work, maybe we entered an integer and should cast that to a double but i don't think its necessary.
				angSpd = (double) Integer.parseInt(txt);	
			}catch(NumberFormatException e2) {
				// otherwise, we'll just have to throw it away (FOR REAL)
				angSpd = p.getAngularSpeed();
			}
			
		}
		
		// now, set the textbox to have that value inside of it.
		myField.setText(Double.toString(angSpd));
		
		// finally, set the pendulum's angular speed
		p.setAngularSpeed(angSpd);
		
	}// end setAngularSpeed()
	
	public void setOriginalAngle(ActionEvent ae, Pendulum p) 
	{
		// set the starting angle for the pendulum
		
		String txt = ae.getActionCommand();
		double ang;
		try{
			// if it's a valid entry, set ang to a double value.
			ang = Double.parseDouble(txt);	
		}catch(NumberFormatException e) {
			// otherwise, we'll have to just throw it away and set it back to what angular speed we did have.
			
			try{
				// if that doesn't work, maybe we entered an integer and should cast that to a double but i don't think its necessary.
				ang = (double) Integer.parseInt(txt);	
			}catch(NumberFormatException e2) {
				// otherwise, we'll just have to throw it away (FOR REAL)
				ang = p.getAngularSpeed();
			}
			
		}
		
		// now, set the textbox to have that value inside of it.
		myField.setText(Double.toString(ang));
		
		// finally, set the pendulum's starting angle
		p.setOriginalAngle(ang);
		
	}// setOriginalAngle()
	
	public void setTextField(PendulumTextField myField) 
	{
		// method that sets the text field of the event handler
		
		this.myField = myField;
		
	}// setTextField()
	
	public void setMyPendulum(Pendulum ps) 
	{
		// method that sets the myPendulum reference.
		this.myPendulum = ps;
		
	}// end setMyPendulum()
	
	public Pendulum getMyPendulum() 
	{
		// method that returns our pendulum object
		return this.myPendulum;
		
	}// end getMyPendulum()
	
	public void setDuplicateButton(DuplicatePendulumButton dp) 
	{
		// method that sets our duplicate pendulum button
		this.myDuplicateButton = dp;
		
	}// setDuplicateButton()
	
	public PendulumPropertiesPanel getMyPendulumPanel() 
	{
		// method that returns this listener's pendulum properties panel
		return myPendulumPanel;
		
	}// end getMyPendulumPanel()
	
	public void setMyPendulumPanel(PendulumPropertiesPanel p) 
	{
		// method that sets the listener's pendulum properties panel
		this.myPendulumPanel = p;
		
	}// end setMyPendulumPanel()
	
	public void setMyPendulumSystem(PendulumSystem ps) 
	{
		// method that sets the target pendulum system to call remove()
		this.myPendulumSystem = ps;
	}// end setMyPendulumSystem()
	
	public PendulumSystem getMyPendulumSystem() 
	{
		// method that returns our pendulum system
		return this.myPendulumSystem;
	}// end getMyPendulumSystem()
	
	public void setDeleteButton(DeleteButton deleteButton) 
	{
		// method that sets the delete button for the event handler
		
		this.deleteButton = deleteButton;
		
	}// end setDeleteButton()
	
	public DeleteButton getDeleteButton() 
	{
		// method that returns the reference for our delete button
		return deleteButton;
		
	}// end getDeleteButton()
	
	public void setPlayPauseButton(PlayPauseButton playPauseButton) 
	{
		// method that sets the play pause button of the event handler
		
		this.playPauseButton = playPauseButton;
		
	}// end setPlayPauseButton()
	
	public void setAddPendulumButton(AddPendulumButton addPendulumButton) 
	{
		// method that sets the add pendulum button for the listener
		
		this.addPendulumButton = addPendulumButton;
	
	}// end setAddPendulumButton()
	
}// end MyActionListener()
