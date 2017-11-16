package de.thelearningtriangle.core;

import java.util.List;
import java.util.Random;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import de.thelearningtriangle.core.controller.TriangleOverworldFactory;
import de.thelearningtriangle.core.controller.TriangleOverworldFileLoader;
import de.thelearningtriangle.core.exceptions.FieldAccessException;
import de.thelearningtriangle.core.exceptions.NoMapException;
import de.thelearningtriangle.core.exceptions.TriangleDeathException;
import de.thelearningtriangle.core.model.overworld.Direction;
import de.thelearningtriangle.core.model.overworld.TriangleOverworld;
import de.thelearningtriangle.core.model.triangle.TrianglePosition;
import de.thelearningtriangle.ui.ImageLoader;
import de.thelearningtriangle.ui.MainWindow;

/*TODO General: 
	
		-	Settings-Menu and Restart in the same application
		-	More than one Triangle

 */

public class Application
{

	static Random random = new Random(System.currentTimeMillis());

	public static void main(String[] args) throws Exception
	{
		@SuppressWarnings("unused")
		ImageLoader imageLoader = new ImageLoader(System.getProperty("user.dir").replace('\\', '/'), "Classic");

		TriangleOverworld overworld = TriangleOverworldFactory.getOverworldMap();

		MainWindow mainW = new MainWindow(overworld);

		while (true)
		{

			TrianglePosition trianglePosition = overworld.getTrianglePositions().get(0);
			List<Integer> vv = overworld.getVisionVectorFor(trianglePosition.getPoint());
			overworld.moveTriangle(trianglePosition, Direction.SOUTH);

			try
			{
				trianglePosition.getLearningTriangle().cycle();
			} catch (TriangleDeathException e)
			{
				setNewTrianlgeSpawnPoint(0, overworld);
			}
			mainW.getOverworldPanel().repaint();
			Thread.sleep(300);
		}
	}


	private static void setNewTrianlgeSpawnPoint(int mode, TriangleOverworld overworld)
			throws NoMapException, FieldAccessException {
		overworld.getTrianglePositions().clear();

		if (mode == 0)
		{
			overworld.setTriangle(overworld.getRandomSpawningPoint());
		} else
		{
			overworld.setTriangle(TriangleOverworldFactory.getTriangleX(),
					TriangleOverworldFactory.getTriangleY());
		}
	}
	

	
}