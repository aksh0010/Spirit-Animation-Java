
/*Camera
 * Component in a game where it main objective is to focus on other component for view
 * */
public class Camera extends GameObject {
	
	public GameObject target;
	private int offsetX; // Offset to center player horizontally
	private int offsetY; // Offset to center player vertically

	public Camera(GameObject target) {
		super(target.x, target.y);
		this.target = target;
		offsetX = (int) ((GameCanvas.GAME_WIDTH - target.x) / 2);
        offsetY = (int) ((GameCanvas.GAME_HEIGHT - target.y) / 2);
	}
	// _____________________________________________________________________
	// _____________________________________________________________________
	@Override
	public void tick() {
		x = target.x - offsetX;
		y = target.y - offsetY;
		
		 // Ensure camera doesn't move out of the game world boundaries
        if (x < 0) {
            x = 0;
        } else if (x > GameCanvas.GAME_WIDTH - GameCanvas.GAME_WIDTH) {
            x = GameCanvas.GAME_WIDTH - GameCanvas.GAME_WIDTH;
        }
        if (y < 0) {
            y = 0;
        } else if (y > GameCanvas.GAME_HEIGHT - GameCanvas.GAME_HEIGHT) {
            y = GameCanvas.GAME_HEIGHT - GameCanvas.GAME_HEIGHT;
        }
	}
}
