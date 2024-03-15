import java.awt.*;
import java.awt.image.*;

/*Background
 *Component in a game world
 * */
public class Background extends GameObject {
//setting width and height of our component
	private final int background_width = GameCanvas.GAME_WIDTH;
	private final int background_height = GameCanvas.GAME_HEIGHT;
//Creating animator for our background
	private transient Animator background_Animator;
    private transient BufferedImage frameToDraw;
// Rows and columns to repeat our drawing and making it look even 
    private int columns = 15; // Number of columns to draw
    private int rows = 8; // Number of rows to draw
 // _____________________________________________________________________
 // _____________________________________________________________________
    
    public Background(float x, float y) {
        super(x, y);

        BufferedImage[] idleFrames = Resources.background.getImagesFrom(7, 7);
        if (idleFrames.length > 9) { // Check if there are at least 10 frames
            frameToDraw = idleFrames[9]; // Let's say you want to draw the 10th frame
        } else {
            // Handle the case where there are not enough frames
            // For example, use the first frame or any other default
            frameToDraw = idleFrames[0];
        }
        background_Animator = new Animator(idleFrames, 0, 1);
    }
 // _____________________________________________________________________
 // _____________________________________________________________________
    @Override
    public void tick() {
    	// constantly rendering the background
    	background_Animator.tick();
    }

 // _____________________________________________________________________
 // _____________________________________________________________________
    @Override
    public void render(Graphics2D g2d) {
        int frameWidth = frameToDraw.getWidth();
        int frameHeight = frameToDraw.getHeight();
// we have only one image of background block so we need 
// to draw the image multiple times
// creating 2 for loops to do row and column of the screen,
        
// note row and column are hard coded for this which will be changed later
        
        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                int drawX = (int) x + (i * frameWidth);
                int drawY = (int) y + (j * frameHeight);
                // fetching the coordinates of old drawing and getting the new one
                // also drawing the new one
                g2d.drawImage(frameToDraw, drawX, drawY, frameWidth, frameHeight, null);
            }
        }
    }
}
