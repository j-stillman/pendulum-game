import javax.swing.JTextField;

/**
 * @title PendulumTextField
 * @author Jeremiah Stillman
 * @version 07/17/24
 * @description This class is just a JTextField, but it has its own reference for a pendulum object,
 * which we need in order to actually set the properties when entering stuff in.
 */

public class PendulumTextField extends JTextField 
{
	
	//======================================================================================================================
	// Fields
	
	private Pendulum myPend;						// Pendulum reference for which one we're changing
	
	//======================================================================================================================
	// Constructor
	
	public PendulumTextField(String txt, int len, Pendulum myPend) 
	{
		// Construct it as you would a normal JTextField
		super(txt, len);
		
		// next, add the Pendulum reference.
		setPendulum(myPend);
		
	}// set PendulumTextField()
	
	//======================================================================================================================
	// Behaviours
	
	public void setPendulum(Pendulum myPend) 
	{
		// method that sets the Pendulum reference.
		this.myPend = myPend;
		
	}// end setPendulum()
	
	public Pendulum getPendulum() 
	{
		// method that returns our pendulum
		
		return myPend;
	}// end getPendulum()
	
}// end PendulumTextField
