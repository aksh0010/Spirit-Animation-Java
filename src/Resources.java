
/*Resources
 * Class to load our sprite sheet in to one place and making one copy through out the game world using static
 * */
public class Resources {
	// Here I created 3 spritesheet objects
	// we could have used only but we need to make 3 so we
	// so we can make it easy if we have multiple images in future
	public static SpriteSheet playerSheet = new SpriteSheet("res/SpriteSheet.png", 8, 8, 64, 64);
	public static SpriteSheet power_upSheet = new SpriteSheet("res/SpriteSheet.png", 8, 8, 64, 64);
	public static SpriteSheet background = new SpriteSheet("res/SpriteSheet.png", 8, 8, 64, 64);
}
