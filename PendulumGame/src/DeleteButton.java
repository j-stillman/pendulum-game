import javax.swing.Icon;
import javax.swing.JButton;

/**
 * @title DeleteButton
 * @author Jeremiah Stillman
 * @version 07/18/24
 * @description This class is the button that is pressed to delete a PendulumPropertiesPanel from the list inside PendulumSystem.
 * 
 */
public class DeleteButton extends JButton 
{
	
	//===============================================================================================================
	// Fields
	
	private int listIndex;					// integer value to represent the position in the pendulum list that it is.
	
	//===============================================================================================================
	// Constructor
	
	public DeleteButton(String txt) 
	{
		// regular constructor. just pass in text.
		
		super(txt);
		
	}// end DeleteButton()
	
	public DeleteButton(String txt, Icon img) 
	{
		// regular constructor. just pass in text and icon
		
		super(txt, img);
		
	}// end DeleteButton()
	
	//===============================================================================================================
	// Behaviours
	
	public void setListIndex(int listIndex) 
	{
		// method that sets our list index. for consistency when setting the indexes for the pendulum properties panels.
		
		this.listIndex = listIndex;
		
	}// end setListIndex()
	
	public int getListIndex() 
	{
		// method that returns our list index for the button
		return this.listIndex;
		
	}// end getListIndex()
	
	
}// end DeleteButton
