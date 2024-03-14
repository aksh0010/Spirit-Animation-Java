import java.awt.*;
import java.awt.image.*;
/*PowerUp
 *Component in a game where if player intersects it
 *score of players updates */
public class PowerUp extends GameObject {

	//Creating the Animator for our component
	private Animator powerAnimator;
	
	//setting width and height of our component
	public static final int power_up_width = 50;
	public static final int power_up_height = 50;
	
	// _____________________________________________________________________
	// _____________________________________________________________________
	// we are using parameterized constructor to initialize the location of this object
	public PowerUp(float x, float y) {
		super(x, y);

		// here we are simply loading the animator with the images after extracting with
		// help of Resources class where we have paths for ours spirit sheet
		BufferedImage[] idleFrames = Resources.power_upSheet.getImagesFrom(48, 48);
		// We need animation to begin with first index of array with duration to repaint as 10
		// as we only have one image it really doesnt matter to repaint much 
		powerAnimator = new Animator(idleFrames, 0, 10);
	}
	
	// _____________________________________________________________________
	// _____________________________________________________________________
	// Here in tick() we simply call the animator's tick method
	@Override
	public void tick() {
		powerAnimator.tick();
	}
	
	// _____________________________________________________________________
	// _____________________________________________________________________
	// For the rendering we here draw that image on screen over our background class and put it static meaning no movement.
	@Override
	public void render(Graphics2D g2d) {
//		 g2d.setColor(Color.YELLOW);
//		 g2d.fillRect((int)x, (int)y, power_up_width, power_up_height);
		g2d.drawImage(powerAnimator.currentFrame.getScaledInstance(power_up_width, power_up_height, Image.SCALE_SMOOTH), (int) x, (int) y, power_up_width, power_up_height, null);
	}
	
	// _____________________________________________________________________
	// _____________________________________________________________________
	// some methods created in case we need to assess x, y , width and height of object.
	public int getX() {
		return (int) x;
	}

	public int getY() {
		return (int)y;
	}

	public int getPowerUpWidth() {
		return power_up_width;
	}

	public int getPowerUpHeight() {
		return power_up_height;
	}
}
