package de.thelearningtriangle.core;

import java.util.List;
import java.util.Random;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

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

		// some variables to set
		int mode = defineMode();
		int worldSize;
		int windowSize = 900;
		int threadTime = 300;
		
		
		TriangleOverworld overworld;

		if (mode == 0)
		{
			worldSize = Integer.parseInt(JOptionPane.showInputDialog("Size of Map:"));
			overworld = TriangleOverworldFactory.generateOverworld(worldSize, random);
			overworld.setTriangle(overworld.getRandomSpawningPoint());
		} else
		{
			JFileChooser fc = new JFileChooser();
			fc.showOpenDialog(null);
			
			List<String[]> mapData = TriangleOverworldFileLoader.parseMapFromFile(fc.getSelectedFile());
			overworld = TriangleOverworldFactory.loadOverworld(mapData);
			overworld.setTriangle(TriangleOverworldFactory.getTriangleX(), TriangleOverworldFactory.getTriangleY());
			worldSize = mapData.size();
		}

		// ensure that windowSize divided through worldSize is even (necessary
		// for UI). If it is, keep everything the same. If it is not, make it
		// even
		windowSize = windowSize % worldSize == 0 ? windowSize : windowSize + (worldSize - (windowSize % worldSize));

		MainWindow mainW = new MainWindow(overworld, windowSize);


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
			mainW.getOverworldPanel().repaint();
			Thread.sleep(threadTime);
		}
	}
	
	private static int defineMode() {
		Object[] options = { "Random!", "Load..." };
		return JOptionPane.showOptionDialog(null, "Random map or load map?", "TLT",
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, // do not use a custom Icon
				options, // the titles of buttons
				options[0]);
	}
	
}