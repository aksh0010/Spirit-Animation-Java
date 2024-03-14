import java.awt.image.*;
/*Animator
 * Animator class to animate our frames from spiritsheet
 * */
public class Animator {
	// Vars to store the images and frames of respective game object's frames passed to it
	public BufferedImage[] frames;
	public BufferedImage currentFrame;
//time var
	private int time = 0;
	private int currentIndex;
// Duration we need for each frame
	private int durationPerFrame;
	// _____________________________________________________________________
	 // _____________________________________________________________________
	  
	public Animator(BufferedImage[] frames, int startIndex, int durationPerFrame) {
		this.frames = frames;
		this.currentIndex = startIndex;
		this.durationPerFrame = durationPerFrame;

		this.currentFrame = frames[currentIndex];
	}
	// _____________________________________________________________________
	 // _____________________________________________________________________
	  
	public void tick() {
		time++;
		if(time >= durationPerFrame) {
			time = 0;
			currentIndex++;
			if(currentIndex > frames.length - 1)
				currentIndex = 0;
		}

		currentFrame = frames[currentIndex];
	}

}
