package de.thelearningtriangle.core;

import java.util.List;
import java.util.Random;

import javax.swing.JOptionPane;

import de.thelearningtriangle.classifier.LinearDirectionClassifier;
import de.thelearningtriangle.core.overworld.Direction;
import de.thelearningtriangle.core.overworld.TriangleDeathException;
import de.thelearningtriangle.core.overworld.TriangleOverworld;
import de.thelearningtriangle.core.overworld.TriangleOverworldFactory;
import de.thelearningtriangle.core.overworld.TriangleOverworldFileLoader;
import de.thelearningtriangle.core.overworld.TrianglePosition;
import de.thelearningtriangle.ui.ImageLoader;
import de.thelearningtriangle.ui.MainWindow;

public class Application
{

	static Random random = new Random(System.currentTimeMillis());

	public static void main(String[] args) throws Exception
	{
		@SuppressWarnings("unused")
		ImageLoader imageLoader = new ImageLoader(System.getProperty("user.dir").replace('\\', '/'), "Classic");

		// 1=random, 2=file
		int mode = 1;
		int worldSize = 25;
		int windowSize = 900;
		TriangleOverworld overworld;

		if (mode == 1)
		{
			overworld = TriangleOverworldFactory.generateOverworld(worldSize, random);
		} else
		{
			List<String[]> mapData = TriangleOverworldFileLoader.parseMapFromFile();
			overworld = TriangleOverworldFactory.loadOverworld(mapData, random);
			worldSize = mapData.size();
		}

		// ensure that windowSite divided through worldSize is zero (necessary
		// for UI)
		windowSize = windowSize + (worldSize - (windowSize % worldSize));

		overworld.setTriangle(overworld.getRandomSpawningPoint());

		MainWindow mainW = new MainWindow(overworld, windowSize);

		LinearDirectionClassifier classifier = new LinearDirectionClassifier();

		while (true)
		{

			TrianglePosition trianglePosition = overworld.getTrianglePositions().get(0);
			List<Integer> vv = overworld.getVisionVectorFor(trianglePosition.getPoint());
			Direction predicted = classifier.predict(vv.toArray(new Integer[0]));
			overworld.moveTriangle(trianglePosition, predicted);

			try
			{
				trianglePosition.getLearningTriangle().cycle();
			} catch (TriangleDeathException e)
			{
				overworld.getTrianglePositions().clear();
				overworld.setTriangle(overworld.getRandomSpawningPoint());
			}

			mainW.getOverworldPanel().repaint();
			Thread.sleep(300);
		}
	}
}