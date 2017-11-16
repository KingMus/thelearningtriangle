package de.thelearningtriangle.core;

import de.thelearningtriangle.core.controller.GameController;
import de.thelearningtriangle.core.controller.TriangleOverworldFactory;
import de.thelearningtriangle.core.model.overworld.TriangleOverworld;
import de.thelearningtriangle.ui.ImageLoader;
import de.thelearningtriangle.ui.MainWindow;

/*TODO General: 
	
		-	Settings-Menu and Restart in the same application
		-	More than one Triangle

 */

public class Application {

	public static void main(String[] args) throws Exception {
		@SuppressWarnings("unused")
		ImageLoader imageLoader = new ImageLoader(System.getProperty("user.dir").replace('\\', '/'), "Classic");

		TriangleOverworld overworld = TriangleOverworldFactory.getOverworldMap();

		MainWindow mainW = new MainWindow(overworld);

		while (true) {
			GameController.runTheGame(overworld);
			mainW.getOverworldPanel().repaint();
		}
	}

}