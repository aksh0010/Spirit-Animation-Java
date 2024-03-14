import java.awt.event.*;

public class Keyboard extends KeyAdapter {

	public boolean left = false, right = false, up = false, down = false;
	public boolean nokey= true;
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_LEFT) left = true;
		else if(e.getKeyCode() == KeyEvent.VK_RIGHT) right = true;
		else if(e.getKeyCode() == KeyEvent.VK_UP) up = true;
		else if(e.getKeyCode() == KeyEvent.VK_DOWN) down = true;
		
		nokey = false;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_LEFT) left = false;
		else if(e.getKeyCode() == KeyEvent.VK_RIGHT) right = false;
		else if(e.getKeyCode() == KeyEvent.VK_UP) up = false;
		else if(e.getKeyCode() == KeyEvent.VK_DOWN) down = false;
	
		nokey = true;
	}
}
