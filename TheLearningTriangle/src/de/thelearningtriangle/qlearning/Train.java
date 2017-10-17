package de.thelearningtriangle.qlearning;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import javax.swing.JFileChooser;

import com.jogamp.nativewindow.util.Point;

import de.thelearningtriangle.core.overworld.FieldAccessException;
import de.thelearningtriangle.core.overworld.NoMapException;
import de.thelearningtriangle.core.overworld.TriangleOverworld;
import de.thelearningtriangle.core.overworld.TriangleOverworldFactory;
import de.thelearningtriangle.core.overworld.TriangleOverworldFileLoader;
import de.thelearningtriangle.core.triangle.LearningTriangle;
import de.thelearningtriangle.qlearning.RecursiveQTriangle.TriangleMoveData;

public class Train
{
	static File	testFile	= new File("test.csv");
	static File	trainFile	= new File("train.csv");

	public static Thread createRunner(File algorithmFile, File inputFile)
	{
		return new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				while (true)
				{

					List<String[]> mapData = TriangleOverworldFileLoader.parseMapFromFile(inputFile);
					TriangleOverworld overworld;
					try
					{
						overworld = TriangleOverworldFactory.loadOverworld(mapData);

						List<RecursiveQTriangle.TriangleMoveData> bestMoves = null;
						RecursiveQTriangle qTriangle = new RecursiveQTriangle(overworld);
						LearningTriangle triangle = new LearningTriangle();
						bestMoves = qTriangle.calculateBestMoves(new Point(TriangleOverworldFactory.getTriangleX(),
								TriangleOverworldFactory.getTriangleY()), triangle, 13);
						bestMoves.remove(0);
						
						System.out.println("- Finished with score: " + bestMoves.get(0).getScore());
						FileWriter writer;
						
						synchronized (algorithmFile)
						{
							writer = new FileWriter(algorithmFile, true);
							for (int i = 0; i < 5; i++)
							{
								TriangleMoveData triangleMoveData = bestMoves.get(i);
								writer.write(triangleMoveData.toString());
							}
							writer.flush();
							writer.close();
						}
					} catch (NoMapException | FieldAccessException | IOException e)
					{
						e.printStackTrace();
					}
				}
			}

		});
	}

	public static void main(String[] args)
	{
		JFileChooser fc = new JFileChooser();
		fc.showOpenDialog(null);
		
		File inputFile = fc.getSelectedFile();
		
		createRunner(trainFile, inputFile).start();
		createRunner(trainFile, inputFile).start();
		createRunner(trainFile, inputFile).start();
		createRunner(trainFile, inputFile).start();
		createRunner(trainFile, inputFile).start();
		createRunner(trainFile, inputFile).start();

		createRunner(testFile, inputFile).start();
		createRunner(testFile, inputFile).start();
		createRunner(testFile, inputFile).start();
		createRunner(testFile, inputFile).start();
		createRunner(testFile, inputFile).start();
		createRunner(testFile, inputFile).start();
	}

}
