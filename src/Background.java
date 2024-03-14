import java.awt.*;
import java.awt.image.*;

public class Background extends GameObject {

	private final int background_width = GameCanvas.GAME_WIDTH;
	private final int background_height = GameCanvas.GAME_HEIGHT;
    private Animator background_Animator;
    private BufferedImage frameToDraw;
    private int columns = 5; // Number of columns to draw
    private int rows = 5; // Number of rows to draw

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
        background_Animator = new Animator(idleFrames, 0, 10);
    }
    
    @Override
    public void tick() {
    	background_Animator.tick();
    }

    @Override
    public void render(Graphics2D g2d) {
        int frameWidth = frameToDraw.getWidth();
        int frameHeight = frameToDraw.getHeight();

        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                int drawX = (int) x + (i * frameWidth);
                int drawY = (int) y + (j * frameHeight);
                g2d.drawImage(frameToDraw, drawX, drawY, frameWidth, frameHeight, null);
            }
        }
    }
}
