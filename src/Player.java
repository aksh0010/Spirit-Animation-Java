import java.awt.*;
import java.awt.image.*;
import java.util.Iterator;
import java.util.Map.Entry;

public class Player extends GameObject {
	public static int PLAYER_SCORE=0;
	private final int player_width = 50;
	private final int player_height = 50;
	private final int ERROR=20;
	private Animator idleAnimator;
	private Animator leftAnimator;
	private Animator rightAnimator;
	private Animator upAnimator;
	private Animator downAnimator;
	public Player(float x, float y) {
		super(x, y);
		// array of Idle frames from the playersheet
		BufferedImage[] idleFrames = Resources.playerSheet.getImagesFrom(0, 6);
		idleAnimator = new Animator(idleFrames, 0, idleFrames.length-1);
//idleFrames.length-1
	
		// array of left frames from playersheet 
		BufferedImage[] leftFrames = Resources.playerSheet.getImagesFrom(8, 31);
		leftAnimator = new Animator(leftFrames, 0,1 );
		
//		leftFrames.length-1
		// array of right frames from playersheet 
		BufferedImage[] rightFrames = Resources.playerSheet.getImagesFrom(8, 31);
		rightAnimator= new Animator(rightFrames, 0,1 );
	//rightFrames.length-1
		// array of up frames from playersheet 
		BufferedImage[] upFrames = Resources.playerSheet.getImagesFrom(40, 47);
		upAnimator = new Animator(upFrames, 0, upFrames.length-1);
//upFrames.length-1
		// array of down frames from playersheet 
		BufferedImage[] downFrames = Resources.playerSheet.getImagesFrom(32, 39);
		downAnimator = new Animator(downFrames, 0, downFrames.length-1);
		//downFrames.length-1
	
	
	
	}
	
	public void collision_detection() {
	    Rectangle player_rectangle = new Rectangle((int)x, (int)y, player_width-ERROR, player_height-ERROR);
	    
	    Iterator<Entry<PowerUp, Rectangle>> iterator = GameCanvas.hashmap_powerup.entrySet().iterator();
	    while (iterator.hasNext()) {
	        Entry<PowerUp, Rectangle> entry = iterator.next();
	        Rectangle local_powerUpBound = entry.getValue();
	        
	        if (player_rectangle.intersects(local_powerUpBound)) {
	            // Handle collision (with, power-up collected)
	            System.out.println("Collision detected");
	            System.out.println("The key for value " + local_powerUpBound + " is " + entry.getKey());

	            // Remove from the hashmap
	            iterator.remove(); // Safe removal while iterating

	            // Remove from the GameManager
	            GameCanvas.removing_powerup_from_player(entry.getKey());
	            PLAYER_SCORE++;
	            System.out.println("Player Score : "+PLAYER_SCORE);
	            System.out.println("bound of player "+ player_rectangle);
	            System.out.println("Bounds of powerup "+local_powerUpBound);
	        }
	    }
	}


	
	@Override
	public void tick() {
		if(GameCanvas.keyboard.left) {
			x -= 2;
			if (x <0) {
				x = 0;
			}
			leftAnimator.tick();
		} else  if(GameCanvas.keyboard.right) {
			x += 2;
			
			if (x +player_width> GameCanvas.GAME_WIDTH ) {
				x = GameCanvas.GAME_WIDTH-player_width;
			}
			rightAnimator.tick();
		
		} else if(GameCanvas.keyboard.up){
			y -= 2;
			if ( y<0) {
				y=0;
			}
			upAnimator.tick();
		}else if(GameCanvas.keyboard.down) {
			
				y += 2;
			 if( y +player_height>GameCanvas.GAME_HEIGHT) {
				 y = GameCanvas.GAME_HEIGHT-player_height;
			 }
				downAnimator.tick();
				
		} else if (GameCanvas.keyboard.nokey){
				idleAnimator.tick();
			}
//		System.out.println("X:" + x + " Y : "+y );
		collision_detection();
	}

	@Override
	public void render(Graphics2D g2d) {
		
//		getScaledInstance(player_width, player_height, Image.SCALE_SMOOTH)
//		  g2d.setColor(Color.YELLOW);
//		   g2d.fillRect((int)x, (int)y, player_width, player_height);
		if(GameCanvas.keyboard.left) {
//			Checking for keyboard left key
			g2d.drawImage(leftAnimator.currentFrame, (int) x, (int) y, player_width, player_height, null);
		} else  if(GameCanvas.keyboard.right) {
//			Checking for keyboard right key
//			rightAnimator.flipFrameHorizontally();
			g2d.drawImage(rightAnimator.currentFrame, (int) x, (int) y, player_width, player_height, null);
		
		} else if(GameCanvas.keyboard.up){
//			Checking for keyboard up key
			g2d.drawImage(upAnimator.currentFrame, (int) x, (int) y, player_width, player_height, null);
			
			}else if(GameCanvas.keyboard.down) {
//				Checking for keyboard down key
				g2d.drawImage(downAnimator.currentFrame, (int) x, (int) y, player_width, player_height, null);
				
				
			} else if (GameCanvas.keyboard.nokey){
				g2d.drawImage(idleAnimator.currentFrame, (int) x, (int) y, player_width, player_height, null);
			}
		 // Draw the player score with yellow color
	    g2d.setColor(Color.YELLOW);
	    g2d.setFont(new Font("Arial", Font.BOLD, 20));
	    g2d.drawString("Score: " + PLAYER_SCORE, 10, 20);
		
	}
}
