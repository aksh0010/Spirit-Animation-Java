import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.awt.image.*;

public class GameCanvas extends Canvas implements Runnable {
	
		
	private static int powerup_error_width = PowerUp.power_up_width;
	private static int powerup_error_heigth = PowerUp.power_up_height;
	private Thread thread;
	private BufferStrategy bs;
	public static final int PLAYER_INITIAL_X = 0 , PLAYER_INITIAL_Y=0;
	public static int total_powerup=3;
	public static final int GAME_WIDTH = 960, GAME_HEIGHT = 540;  
	private Background background = new Background(GAME_WIDTH, GAME_HEIGHT);
	private Player player = new Player(PLAYER_INITIAL_X, PLAYER_INITIAL_Y);
//	public static ArrayList<Rectangle> arraylist_powerUps_Bounds = new ArrayList<>();
	public static HashMap<PowerUp, Rectangle> hashmap_powerup = new HashMap<>() ;
	
	// changed from private to public static
	private static GameManager gameManager = new GameManager();
	
	public static Keyboard keyboard = new Keyboard();
	public static Camera camera;
	Random random = new Random();
	public GameCanvas() {
		camera = new Camera(player);
		gameManager.addGameObject(background);
		gameManager.addGameObject(player);
		gameManager.addGameObject(camera);

		for (int i =0 ; i <total_powerup;i++) {
		    int randomX = random.nextInt(GAME_WIDTH-powerup_error_width); // Generates a random x between 0 and 760
            int randomY = random.nextInt(GAME_HEIGHT-powerup_error_heigth); // Generates a random y between 0 and 340

            PowerUp powerup = new PowerUp(randomX, randomY);
            Rectangle powerUpBounds = new Rectangle((int) powerup.getX(), (int) powerup.getY(), powerup.getPowerUpWidth(), powerup.getPowerUpHeight());
          
           if( ! hashmap_powerup.containsKey(powerup)) {
        	   
        	   hashmap_powerup.put(powerup, powerUpBounds);
        	   
           }
            System.out.println(hashmap_powerup);
            
//            arraylist_powerUps_Bounds.add(powerUpBounds);
			gameManager.addGameObject(powerup);
		}
		
		
		this.addKeyListener(keyboard);
	}
	public static void removing_powerup_from_player(PowerUp local) {
		
		gameManager.removeGameObject(local);
		
	}
	
	public void set_total_powerups(int total) {
		total_powerup = total;
		gameManager.tick();
	}
	public void start() {
		this.requestFocus();
		this.createBufferStrategy(2);
		bs = this.getBufferStrategy();
		thread = new Thread(this, "Game Thread");
		thread.start();
	}

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

	private void tick() {
		gameManager.tick();
	}
	
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

