import java.awt.Graphics2D;
/* GameObject
 * Each elements or component is derrived from this class
 * */
public class GameObject {
	// Each gameobject has x,y coordinates 
	public float x, y;

	public GameObject(float x, float y) {
		this.x = x;
		this.y = y;
	}
	// Each gameobject also has tick and render method to override
	public void tick() {}
	public void render(Graphics2D g2d) {}
}
