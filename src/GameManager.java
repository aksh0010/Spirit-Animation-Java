import java.awt.Color;
import java.awt.Graphics2D;
import java.util.LinkedList;
/*GameManager
 * Gamemanager to add and remove gameobjects from
 * game world
 * */
public class GameManager {
	
	private LinkedList<GameObject> gameObjects = new LinkedList<GameObject>(); 
	// _____________________________________________________________________
	// _____________________________________________________________________
	public void tick() {
//		gameObjects.forEach(gameObject -> gameObject.tick());
		 // created linkedlist to have latest objects
	    // as we we dont we cant get the latest linked list
	    // after the removal of object and might runinto errors
	  	LinkedList<GameObject> gameObjectsCopy = new LinkedList<>(gameObjects);
		    gameObjectsCopy.forEach(gameObject -> gameObject.tick());
	
	}
	
	// _____________________________________________________________________
	// _____________________________________________________________________
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
	    // as we we dont we cant get the latest linked list
	    // after the removal of object and might runinto errors
	    LinkedList<GameObject> gameObjectsCopy = new LinkedList<>(gameObjects);
	    gameObjectsCopy.forEach(gameObject -> gameObject.render(g2d));
	    
//	    gameObjects = gameObjectsCopy;
	}

	// _____________________________________________________________________
	// _____________________________________________________________________
	public void addGameObject(GameObject gameObject) {
		System.out.println("Object added to Manager");
		gameObjects.add(gameObject);
	}

	// _____________________________________________________________________
	// _____________________________________________________________________
	public void removeGameObject(GameObject gameObject) {
		gameObjects.remove(gameObject);
	}
	
	// _____________________________________________________________________
	// _____________________________________________________________________

	public LinkedList<GameObject> getGameObjects() {
		return gameObjects;
	}
	// _____________________________________________________________________
	// _____________________________________________________________________

	public void setGameObjects(LinkedList<GameObject> gameObjects) {
		this.gameObjects = gameObjects;
	}
	
}
