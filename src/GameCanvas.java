import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.awt.image.*;
/*GameCanvas
 * Here we are drawing everything and putting them together inside GameManager
 * */
public class GameCanvas extends Canvas implements Runnable {

//Getting width and height of powerup object	
	private static int powerup_error_width = PowerUp.power_up_width;
	private static int powerup_error_heigth = PowerUp.power_up_height;
//thread to run 
	private Thread thread;
	private BufferStrategy bs;
// Setting Final initial position of Player 
	public static final int PLAYER_INITIAL_X = 0 , PLAYER_INITIAL_Y=0;
// Creating Variable to store total number of power ups in game
	public static int total_powerup=50;
// creating Vars to store width and height of game	
	public static final int GAME_WIDTH = 960, GAME_HEIGHT = 540;  
//Gameobject background , player and camera are created	
	private Background background = new Background(0, 0);
	private Player player = new Player(PLAYER_INITIAL_X, PLAYER_INITIAL_Y);
//	Creating hashmap to store Newly created powerup objects and their Rectangle for collision detection
	public static HashMap<PowerUp, Rectangle> hashmap_powerup = new HashMap<>() ;
	
// GameManager object making it static to ensure only copy
	private static GameManager gameManager = new GameManager();

	public static Keyboard keyboard = new Keyboard();
	public static Camera camera;
// Using random to have powerups object at random place	
	Random random = new Random();
	
// _____________________________________________________________________
// _____________________________________________________________________	
	public GameCanvas() {
		
		// Focusing camera on background so it doesnot move around
		camera = new Camera(background);
	
		// Adding  gameobjects to game manager
		gameManager.addGameObject(background);
		gameManager.addGameObject(player);
		gameManager.addGameObject(camera);

		
		// Here creating powerups objects and storing them in hashmap
		for (int i =0 ; i <total_powerup;i++) {
		   
			//For x,y co-ordiants of powerup as we want them scattered around the place on screen
			int randomX = random.nextInt(GAME_WIDTH-(powerup_error_width*2)); // Generates a random x between 0 and 760
            int randomY = random.nextInt(GAME_HEIGHT-(powerup_error_heigth*2)); // Generates a random y between 0 and 340
            // local instances to store them inside hashmap
            PowerUp powerup = new PowerUp(randomX, randomY);
            Rectangle powerUpBounds = new Rectangle((int) powerup.getX(), (int) powerup.getY(), powerup.getPowerUpWidth(), powerup.getPowerUpHeight());
          
            // if does not already exists we simply put it inside hashmap
           if( ! hashmap_powerup.containsKey(powerup)) {
        	   hashmap_powerup.put(powerup, powerUpBounds);
           }
            System.out.println(hashmap_powerup);
            
//            arraylist_powerUps_Bounds.add(powerUpBounds);
			gameManager.addGameObject(powerup);
		}
		
	// Also adding keylistener to our canvas 
		this.addKeyListener(keyboard);
	}
	// _____________________________________________________________________
	// _____________________________________________________________________
	// Method remove object from game manager
	public static void removing_powerup_from_player(PowerUp local) {
		
		gameManager.removeGameObject(local);
		
	}
	// _____________________________________________________________________
	// _____________________________________________________________________
	//method to set total number of power ups
	public void set_total_powerups(int total) {
		total_powerup = total;
		gameManager.tick();
	}
	// _____________________________________________________________________
	// _____________________________________________________________________
	public void start() {
		this.requestFocus();
		this.createBufferStrategy(2);
		bs = this.getBufferStrategy();
		thread = new Thread(this, "Game Thread");
		thread.start();
	}
	// _____________________________________________________________________
	// _____________________________________________________________________
	@Override
	public void run() {
		long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        double delta = 0;
		
		final int UPS_CAP = 60;

        while (true) {
            long now = System.nanoTime();
            delta += (now - lastTime) / (double) (1000000000 / UPS_CAP);
            lastTime = now;

            while (delta >= 1) {
                tick();
                delta--;
            }

            render();

            if (System.currentTimeMillis() - timer > 1000) timer += 1000;
        }
	}
	// _____________________________________________________________________
	// _____________________________________________________________________
	private void tick() {
		gameManager.tick();
	}
	// _____________________________________________________________________
	// _____________________________________________________________________
	public void render() {
		Graphics2D g2d = (Graphics2D) bs.getDrawGraphics();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g2d.setColor(Color.black);
		g2d.fillRect(0, 0, this.getWidth(), this.getHeight());

		gameManager.render(g2d);

		g2d.dispose();
		bs.show();
	}

}

