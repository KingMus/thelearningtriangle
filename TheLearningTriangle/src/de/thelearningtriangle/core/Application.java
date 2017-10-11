package de.thelearningtriangle.core;

import java.util.List;
import java.util.Random;

import de.thelearningtriangle.classifier.LinearDirectionClassifier;
import de.thelearningtriangle.core.overworld.Direction;
import de.thelearningtriangle.core.overworld.TriangleDeathException;
import de.thelearningtriangle.core.overworld.TriangleOverworld;
import de.thelearningtriangle.core.overworld.TriangleOverworldFactory;
import de.thelearningtriangle.core.overworld.TriangleOverworldFileLoader;
import de.thelearningtriangle.core.overworld.TrianglePosition;
import de.thelearningtriangle.ui.ImageLoader;
import de.thelearningtriangle.ui.MainWindow;

/*TODO General: 
	
		-	MapBuilder
		-	MapSaver
		-	Settings-Menu and Restart in the same application
		-	More than one Triangle
		-	More Fields (Spawn, Goal) (for evolutionary algorithms)
		-	Comments and Java-Doc

 */

public class Application
{

	static Random random = new Random(System.currentTimeMillis());

	public static void main(String[] args) throws Exception
	{
		@SuppressWarnings("unused")
		ImageLoader imageLoader = new ImageLoader(System.getProperty("user.dir").replace('\\', '/'), "Classic");

		// 1=random, 2=file
		int mode = 2;
		int worldSize = 20;
		int windowSize = 600;
		TriangleOverworld overworld;

		if (mode == 1)
		{
			overworld = TriangleOverworldFactory.generateOverworld(worldSize, random);
			overworld.setTriangle(overworld.getRandomSpawningPoint());
		} else
		{
			List<String[]> mapData = TriangleOverworldFileLoader.parseMapFromFile();
			overworld = TriangleOverworldFactory.loadOverworld(mapData, random);
			overworld.setTriangle(TriangleOverworldFactory.getTriangleX(), TriangleOverworldFactory.getTriangleY());
			worldSize = mapData.size();
		}

		// ensure that windowSite divided through worldSize is zero (necessary
		// for UI)
		windowSize = windowSize + (worldSize - (windowSize % worldSize));

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

				if (mode == 1)
				{
					overworld.setTriangle(overworld.getRandomSpawningPoint());
				} else
				{
					overworld.setTriangle(TriangleOverworldFactory.getTriangleX(),
							TriangleOverworldFactory.getTriangleY());
				}
			}
			mainW.getOverworldPanel().repaint();
			Thread.sleep(300);
		}
	}
}