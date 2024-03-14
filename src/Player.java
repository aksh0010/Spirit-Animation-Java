import java.awt.*;
import java.awt.image.*;

public class Player extends GameObject {

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
	
		// array of left frames from playersheet 
		BufferedImage[] leftFrames = Resources.playerSheet.getImagesFrom(8, 31);
		leftAnimator = new Animator(leftFrames, 0, leftFrames.length-1);
		// array of right frames from playersheet 
		BufferedImage[] rightFrames = Resources.playerSheet.getImagesFrom(8, 31);
		rightAnimator= new Animator(rightFrames, 0, rightFrames.length-1);
	
		// array of up frames from playersheet 
		BufferedImage[] upFrames = Resources.playerSheet.getImagesFrom(40, 47);
		upAnimator = new Animator(upFrames, 0, upFrames.length-1);
	
		// array of down frames from playersheet 
		BufferedImage[] downFrames = Resources.playerSheet.getImagesFrom(32, 39);
		downAnimator = new Animator(downFrames, 0, downFrames.length-1);
	
	
	
	}
	
	
	//  collision detection  between player and all powerups
	public void collision_detection() {
		
		Rectangle player_rectangle = new Rectangle((int)x, (int)y, player_width-ERROR, player_height-ERROR);
		
		  for (Rectangle powerUpBound : GameCanvas.arraylist_powerUps_Bounds) {
		        if (player_rectangle.intersects(powerUpBound)) {
		            // Handle collision (e.g., power-up collected, player takes damage, etc.)
		       
		        	System.out.println("Collision detected");
		        	System.out.println("bound of player "+ player_rectangle);
		        	
		        	System.out.println("Bounds of powerup "+powerUpBound);
		        	// You can add your custom logic here
		        }
		    }
		
	}
//	private boolean checkCollision(Rectangle rect1, Rectangle rect2) {
//	    return rect1.getX() < rect2.getX() + rect2.getWidth() &&
//	           rect1.getX() + rect1.getWidth() > rect2.getX() &&
//	           rect1.getY() < rect2.getY() + rect2.getHeight() &&
//	           rect1.getY() + rect1.getHeight() > rect2.getY();
//	}
	
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
		
		
	}
}
