package de.thelearningtriangle.core;

import java.util.List;
import java.util.Random;

import com.jogamp.nativewindow.util.Point;

import de.thelearningtriangle.classifier.LinearDirectionClassifier;
import de.thelearningtriangle.core.overworld.Direction;
import de.thelearningtriangle.core.overworld.TriangleDeathException;
import de.thelearningtriangle.core.overworld.TriangleOverworld;
import de.thelearningtriangle.core.overworld.TriangleOverworldFactory;
import de.thelearningtriangle.core.overworld.TrianglePosition;
import de.thelearningtriangle.ui.ImageLoader;
import de.thelearningtriangle.ui.MainWindow;

public class Application
{

	static Random random = new Random(System.currentTimeMillis());

	public static void main(String[] args) throws Exception
	{
		@SuppressWarnings("unused")
		ImageLoader imageLoader = new ImageLoader(System.getProperty("user.dir").replace('\\', '/'), "Steven");

		int size = 25;
		TriangleOverworld overworld = TriangleOverworldFactory.generateOverworld(size, random);
		overworld.setTriangle(overworld.getRandomSpawningPoint());

		MainWindow mainW = new MainWindow(overworld);

		LinearDirectionClassifier classifier = new LinearDirectionClassifier();

		while (true)
		{
			TrianglePosition trianglePosition = overworld.getTrianglePositions().get(0);
			List<Integer> vv = overworld.getVisionVectorFor(trianglePosition.getPoint());
			Direction predicted = classifier.predict(vv.toArray(new Integer[0]));
			overworld.moveTriangle(trianglePosition, predicted);
			
			mainW.getOverworldPanel().repaint();
			
			Thread.sleep(700);
			
			try
			{
				trianglePosition.getLearningTriangle().cycle();
				Point point = trianglePosition.getPoint();
				System.out.printf("CurrentField: %s\n", overworld.getField(point));
			} catch (TriangleDeathException e)
			{
				overworld.getTrianglePositions().clear();
				overworld.setTriangle(overworld.getRandomSpawningPoint());
			}
			System.out.printf("Energy: %s Distance: %s\n", trianglePosition.getLearningTriangle().getEnergy(),
					trianglePosition.getLearningTriangle().getDistance());
		}
	}
}