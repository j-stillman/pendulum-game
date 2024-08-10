import javax.swing.Icon;
import javax.swing.JButton;

/**
 * @title DuplicatePendulumButton
 * @author Jeremiah Stillman
 * @version 07/24/24
 * @description This is a button that gets the properties of the pendulum that we're editing, and then 
 * adds another pendulum with the same properties.
 *
 */
public class DuplicatePendulumButton extends JButton 
{

	//======================================================================================================================================
	// Fields
	
	private Pendulum myPendulum;				// the reference for the pendulum that we're gonna duplicate
	
	//======================================================================================================================================
	// Constructor
	
	public DuplicatePendulumButton(String txt) 
	{
		// normal constructor. just makes a button with some text..
		super(txt);
		
	}// end DuplicatePendulumButton()
	
	public DuplicatePendulumButton(String txt, Icon img) 
	{
		// normal constructor. just makes a button with some text and an image
		super(txt, img);
		
	}// end DuplicatePendulumButton()
	
	//======================================================================================================================================
	// Behaviours
	
	public void setPendulum(Pendulum p) 
	{
		// method that sets the myPendulum
		this.myPendulum = p;
		
	}// end setPendulum()
	
	public Pendulum getPendulum() 
	{
		// method that returns our pendulum
		return this.myPendulum;
		
	}// end getPendulum()
	
	
}// end DuplicatePendulumButton
