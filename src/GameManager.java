import java.awt.Color;
import java.awt.Graphics2D;
import java.util.LinkedList;

public class GameManager {
	
	private LinkedList<GameObject> gameObjects = new LinkedList<GameObject>(); 

	public void tick() {
//		gameObjects.forEach(gameObject -> gameObject.tick());
		// created linkedlist to have latest objects
		LinkedList<GameObject> gameObjectsCopy = new LinkedList<>(gameObjects);
		    gameObjectsCopy.forEach(gameObject -> gameObject.tick());
	
	}
	public void render(Graphics2D g2d) {
	    g2d.translate(-GameCanvas.camera.x, -GameCanvas.camera.y);

	    // drawing a temporary background to make the camera movement explicitly visible
	    g2d.setColor(Color.darkGray);
	    for(int i = 0; i < 100; i++) {
	        for(int j = 0; j < 100; j++) {
	            g2d.fillRect(i*64, j*64, 60, 60);
	        }   
	    }
// created linkedlist to have latest objects
	    LinkedList<GameObject> gameObjectsCopy = new LinkedList<>(gameObjects);
	    gameObjectsCopy.forEach(gameObject -> gameObject.render(g2d));
	}


	public void addGameObject(GameObject gameObject) {
		gameObjects.add(gameObject);
	}

	public void removeGameObject(GameObject gameObject) {
		gameObjects.remove(gameObject);
	}
}
