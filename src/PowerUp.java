import java.awt.*;
import java.awt.image.*;

public class PowerUp extends GameObject {

	private Animator powerAnimator;

	public static final int power_up_width = 50;
	public static final int power_up_height = 50;
	public PowerUp(float x, float y) {
		super(x, y);

		BufferedImage[] idleFrames = Resources.power_upSheet.getImagesFrom(48, 48);
		powerAnimator = new Animator(idleFrames, 0, 10);
	}
	
	@Override
	public void tick() {
		powerAnimator.tick();
	}

	@Override
	public void render(Graphics2D g2d) {
		 g2d.setColor(Color.YELLOW);
		 g2d.fillRect((int)x, (int)y, power_up_width, power_up_height);
		g2d.drawImage(powerAnimator.currentFrame.getScaledInstance(power_up_width, power_up_height, Image.SCALE_SMOOTH), (int) x, (int) y, power_up_width, power_up_height, null);
	}

	public int getX() {
		// TODO Auto-generated method stub
		return (int) x;
	}

	public int getY() {
		// TODO Auto-generated method stub
		return (int)y;
	}

	public int getPowerUpWidth() {
		// TODO Auto-generated method stub
		return power_up_width;
	}

	public int getPowerUpHeight() {
		// TODO Auto-generated method stub
		return power_up_height;
	}
}
