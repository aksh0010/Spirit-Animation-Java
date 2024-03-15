import java.awt.*;
import java.awt.image.*;
import java.util.Iterator;
import java.util.Map.Entry;
/*Player
 *Component in a game world
 * */
public class Player extends GameObject {
	
	//Creating static score meaning only one instance of score in whole game world
	public static int PLAYER_SCORE=0;
	
	//setting width and height of our component
	private final int player_width = 50;
	private final int player_height = 50;
	
	private final int ERROR=20;
	
	//Creating the Animators for our component
	private transient Animator idleAnimator; // for idle position
	private transient Animator leftAnimator;// for moving left
	private transient Animator rightAnimator;// for moving right
	private transient Animator upAnimator;// for moving up
	private transient  Animator downAnimator;// for moving down
	private transient BufferedImage[] idleFrames ;
	private transient BufferedImage[] leftFrames ;
	private transient BufferedImage[] rightFrames ;
	private transient BufferedImage[] upFrames ;
	private transient BufferedImage[] downFrames ;
	// _____________________________________________________________________
	// _____________________________________________________________________
	public Player(float x, float y) {
		super(x, y);
		
		
		// array of Idle frames from the SpiritSheet
		idleFrames = Resources.playerSheet.getImagesFrom(0, 6);
		idleAnimator = new Animator(idleFrames, 0, idleFrames.length-1);
	
		// array of left frames from SpiritSheet 
		leftFrames = Resources.playerSheet.getImagesFrom(8, 31);
		leftAnimator = new Animator(leftFrames, 0,1 );
	
		// array of right frames from SpiritSheet 
		 rightFrames = Resources.playerSheet.getImagesFrom(8, 31);
		rightAnimator= new Animator(rightFrames, 0,1 );

		// array of up frames from SpiritSheet 
		upFrames = Resources.playerSheet.getImagesFrom(40, 47);
		upAnimator = new Animator(upFrames, 0, upFrames.length-1);

		// array of down frames from SpiritSheet 
		downFrames = Resources.playerSheet.getImagesFrom(32, 39);
		downAnimator = new Animator(downFrames, 0, downFrames.length-1);
	
	}
	
	// _____________________________________________________________________
	// _____________________________________________________________________
	// Method to detect collision of Player with PowerUp compoment
	public void collision_detection() {
		
		//Creating rectangle of current player
	    Rectangle player_rectangle = new Rectangle((int)x, (int)y, player_width-ERROR, player_height-ERROR);
	    
	    //As we have more than 2 powerup and I have created hashmap to store 
	    // PowerUp as key and its Rectangle as valu in GameCanvas
	    // we would need iterator to iterator over all rectangles 
	    // to detect collision with player object and then we can
	    // later fetch values
	    Iterator<Entry<PowerUp, Rectangle>> iterator = GameCanvas.hashmap_powerup.entrySet().iterator();
	    while (iterator.hasNext()) {
	        Entry<PowerUp, Rectangle> entry = iterator.next();
	        Rectangle local_powerUpBound = entry.getValue();
	        // if the player hits anyone of the powerup rectangle then we remove 
	        // it from the Gamemanager and also update the score
	        if (player_rectangle.intersects(local_powerUpBound)) {
	            // Handle collision (with, power-up collected)
	            System.out.println("Collision detected");
	            System.out.println("The key for value " + local_powerUpBound + " is " + entry.getKey());

	            // Remove from the local hashmap iterator
	            iterator.remove(); // Safe removal while iterating

	            // Remove from the GameManager ( global)
	            GameCanvas.removing_powerup_from_player(entry.getKey());
	            PLAYER_SCORE++; // Static score update
	            
	            // some sysout for debugging
	            System.out.println("Player Score : "+PLAYER_SCORE);
	            System.out.println("Bounds of Player :"+ player_rectangle);
	            System.out.println("Bounds of Powerup :"+local_powerUpBound);
	        }
	    }
	}

	// _____________________________________________________________________
	// _____________________________________________________________________

	// In this tick method we check of keyboard movements
	// and call animators accordingly
	@Override
	public void tick() {
		
		// if the keyboard key left is pressed the we 
		// update the x co-ordinate and call left animator
		if(GameCanvas.keyboard.left) {
			x -= 2;
			if (x <0) {// Also we check if the player is within the bounds of screen
				
				x = 0;
			}
			leftAnimator.tick();
		} else  if(GameCanvas.keyboard.right) {
			
			// if the keyboard right is pressed the we 
			// update the x co-ordinate and call right animator
					x += 2;
					if (x +ERROR+player_width> GameCanvas.GAME_WIDTH )// Also we check if the player is within the bounds of screen
						
					{
						x = GameCanvas.GAME_WIDTH-player_width-ERROR;
					}
					rightAnimator.tick();
		
		} else if(GameCanvas.keyboard.up){
			
			// if the keyboard up is pressed the we 
			// update the y co-ordinate and call up animator
					
				y -= 2;
				if ( y<0)// Also we check if the player is within the bounds of screen
				{
					y=0;
				}
				upAnimator.tick();
		}else if(GameCanvas.keyboard.down) {
			// if the keyboard down is pressed the we 
			// update the y co-ordinate and call down animator
					
				y += 2;
				 if( y +ERROR+player_height>GameCanvas.GAME_HEIGHT)// Also we check if the player is within the bounds of screen
						
				 {
					 y = GameCanvas.GAME_HEIGHT-player_height-ERROR;
				 }
				downAnimator.tick();
		// 	if no key if pressed we simply call idle animator	
		} else if (GameCanvas.keyboard.nokey){
				idleAnimator.tick();
			}
// Ir-respective of which key if played, I call and check of collision by calling the function
		collision_detection();
	}

	// _____________________________________________________________________
	// _____________________________________________________________________
	
	
// Overriding the Render method and drawing based on which key was pressued
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
			g2d.drawImage(rightAnimator.currentFrame, (int) (x + player_width), (int) y, -player_width, player_height, null);
//			g2d.drawImage(rightAnimator.currentFrame, (int) x, (int) y, player_width, player_height, null);
		
		} else if(GameCanvas.keyboard.up){
//			Checking for keyboard up key
			g2d.drawImage(upAnimator.currentFrame, (int) x, (int) y, player_width, player_height, null);
			
			}else if(GameCanvas.keyboard.down) {
//				Checking for keyboard down key
				g2d.drawImage(downAnimator.currentFrame, (int) x, (int) y, player_width, player_height, null);
				
// If no key is pressed then we draw idle 
// Note : I have created that variable inside keyboard class.
			} else if (GameCanvas.keyboard.nokey){
				g2d.drawImage(idleAnimator.currentFrame, (int) x, (int) y, player_width, player_height, null);
			}
 // Simply Drawing the player score with yellow color
		// on top left
	    g2d.setColor(Color.YELLOW);
	    g2d.setFont(new Font("Arial", Font.BOLD, 20));
	    g2d.drawString("Score: " + PLAYER_SCORE, 10, 20);
		
	}
}
