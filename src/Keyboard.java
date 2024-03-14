import java.awt.event.*;
/*Keyboard
 * Keyboard for the gameworld
 * */
public class Keyboard extends KeyAdapter {

	public boolean left = false, right = false, up = false, down = false;
	public boolean nokey= true; 
	// createad no key variable for occation where nothing is  pressed
	// to avoid writing extra code
	
	// _____________________________________________________________________
	// _____________________________________________________________________
	// updating boolean values based on arrow key pressed
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_LEFT) left = true;
		else if(e.getKeyCode() == KeyEvent.VK_RIGHT) right = true;
		else if(e.getKeyCode() == KeyEvent.VK_UP) up = true;
		else if(e.getKeyCode() == KeyEvent.VK_DOWN) down = true;
		
		// also if any of them is pressed we need to make no key false
		nokey = false;
	}
	// _____________________________________________________________________
	// _____________________________________________________________________
	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_LEFT) left = false;
		else if(e.getKeyCode() == KeyEvent.VK_RIGHT) right = false;
		else if(e.getKeyCode() == KeyEvent.VK_UP) up = false;
		else if(e.getKeyCode() == KeyEvent.VK_DOWN) down = false;
	
		// now on release we make no key true
		nokey = true;
	}
}
