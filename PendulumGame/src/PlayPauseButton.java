import javax.swing.Icon;
import javax.swing.JButton;

/**
 * @title PlayPauseButton
 * @author Jeremiah Stillman
 * @version 07/17/24
 * @description This class extends JButton, and helps MyActionListener distinguish it from the other buttons.
 */
public class PlayPauseButton extends JButton 
{

	//================================================================================================================
	// Fields
	
	private Icon myImage, playImage, stopImage;			// images that appear when you are to press the button to either play or pause.

	//================================================================================================================
	// Constructor
	
	public PlayPauseButton(String txt) 
	{
		// construct it like a regular button.
		super(txt);
	}// end PlayPauseButton()
	
	public PlayPauseButton(String txt, Icon img1, Icon img2) 
	{
		// construct it like a regular button, but it has two different images for playing and pausing.
		
		super(txt, img1);
		
		setImages(img1, img2);
		
	}// end PlayPauseButton()
	
	//================================================================================================================
	// Behaviors
	
	public void setText(boolean running) 
	{
		// method that sets the text for the button
		
		String txt;
		
		if (running) {
			txt = "Stop";
			setImage(this.stopImage);
		}else{
			txt = "Play";
			setImage(this.playImage);
		}
		
		super.setText(txt);
		
		setIcon(this.myImage);
		
		
	}// end setText()
	
	public void setImages(Icon img1, Icon img2) 
	{
		// method that sets the two buttons for the playing and pausing
		
		this.playImage = img1;
		this.stopImage = img2;
		
		setImage(this.playImage);		//  just a default
		
	}// end setImages()
	
	public void setImage(Icon img) 
	{
		// method that sets the actual image for the button.	
		this.myImage = img;
		
	}// end setImage()
	
}// end PlayPauseButton()
