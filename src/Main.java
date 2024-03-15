import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.LinkedList;

import javax.swing.*;

public class Main {

	public static void main(String[] args) {
		JFrame mainWindow = new JFrame("Sprite Animation");
		mainWindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // for saving mechanism
		mainWindow.setSize(GameCanvas.GAME_WIDTH, GameCanvas.GAME_HEIGHT);
		mainWindow.setLocationRelativeTo(null);
		mainWindow.setResizable(false);
//		System.out.println(GameCanvas.arraylist_powerUps_Bounds);
		GameCanvas gameCanvas = new GameCanvas();
		
		mainWindow.add(gameCanvas);
//		System.out.println(GameCanvas.arraylist_powerUps_Bounds);
		mainWindow.setVisible(true);
		 // Load the GameObjects when the application starts
		GameCanvas.resume_game();
     
		gameCanvas.start();
		 mainWindow.addWindowListener(new WindowAdapter() {
	            @Override
	            public void windowClosing(WindowEvent e) {
	                // Save your LinkedList of GameObject objects here
	                gameCanvas.save_game();
	                // Close the application
	                System.exit(0);
	            }
	        });
		
	}
}
