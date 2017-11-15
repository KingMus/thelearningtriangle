package de.thelearningtriangle.core;

import java.util.List;
import java.util.Random;

import javax.swing.JFileChooser;

import de.thelearningtriangle.core.overworld.TriangleDeathException;
import de.thelearningtriangle.core.overworld.TriangleOverworld;
import de.thelearningtriangle.core.overworld.TriangleOverworldFactory;
import de.thelearningtriangle.core.overworld.TriangleOverworldFileLoader;
import de.thelearningtriangle.core.overworld.TrianglePosition;
import de.thelearningtriangle.ui.ImageLoader;
import de.thelearningtriangle.ui.MainWindow;

public class Application {

	static Random random = new Random(System.currentTimeMillis());

	public static void main(String[] args) throws Exception {
		@SuppressWarnings("unused")
		ImageLoader imageLoader = new ImageLoader(System.getProperty("user.dir").replace('\\', '/'), "Classic");

		// some variables to set
		int windowSize = 900;
		int threadTime = 300;

		TriangleOverworld overworld;

		JFileChooser fc = new JFileChooser();
		fc.showOpenDialog(null);

		List<String[]> mapData = TriangleOverworldFileLoader.parseMapFromFile(fc.getSelectedFile());
		overworld = TriangleOverworldFactory.loadOverworld(mapData);
		overworld.setTriangle(TriangleOverworldFactory.getTriangleX(), TriangleOverworldFactory.getTriangleY());
		int worldSize = mapData.size();

		// ensure that windowSize divided through worldSize is even (necessary
		// for UI). If it is, keep everything the same. If it is not, make it
		// even
		windowSize = windowSize % worldSize == 0 ? windowSize : windowSize + (worldSize - (windowSize % worldSize));

		MainWindow mainW = new MainWindow(overworld, windowSize);

		while (true) {

			TrianglePosition trianglePosition = overworld.getTrianglePositions().get(0);
			List<Integer> vv = overworld.getVisionVectorFor(trianglePosition.getPoint());

			try {
				trianglePosition.getLearningTriangle().cycle();
			} catch (TriangleDeathException e) {
				overworld.getTrianglePositions().clear();

				overworld.setTriangle(TriangleOverworldFactory.getTriangleX(), TriangleOverworldFactory.getTriangleY());

			}
			mainW.getOverworldPanel().repaint();
			Thread.sleep(threadTime);
		}
	}

}