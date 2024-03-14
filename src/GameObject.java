import java.awt.Graphics2D;

public class GameObject {
	
	public float x, y;

	public GameObject(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public void tick() {}
	public void render(Graphics2D g2d) {}
}
