import java.awt.Dimension;
import java.awt.Toolkit;

public class Client {
	
	public static final int WIDTH = 1920;
	public static final int HEIGHT = 1080;
	public static final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
	public static final int FPS = 45;
	public static final int DEFAULT_ANGLE = 0;
	public static final int DEFAULT_RADIUS = 128;
	public static final int DEFAULT_SPEED = 5;
	
	public static PendulumSystem pendSystem;
	
	public static void main(String[] args) {
		
		pendSystem = new PendulumSystem();
		
		MyFrame frame = new MyFrame("Pendulum is such a stupid word.", SCREEN_SIZE, pendSystem);
		
		WorldPanel gameWorld = frame.getWorldPanel();
		ControlPanel controlPanel = frame.getControlPanel();
		
		// START THE SIMULATION
		if (WorldPanel.isRunning()) {
			// if the panel is running frames
			
			while(true) {
				
				// have the gameWorld instance act.
				gameWorld.act();
				
				// have the gameWorld instance redraw the screen
				gameWorld.repaint();
				
				// stall for the amount of frames before restarting this loop
				try{
					Thread.sleep(1000 / FPS);
				}catch(InterruptedException e) {
					System.out.println(e);
				}
				
			}
			
		}
		
		
	}
	
}
