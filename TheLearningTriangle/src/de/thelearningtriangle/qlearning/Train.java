package de.thelearningtriangle.qlearning;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import com.jogamp.nativewindow.util.Point;

import de.thelearningtriangle.core.overworld.NoMapException;
import de.thelearningtriangle.core.overworld.TriangleOverworld;
import de.thelearningtriangle.core.overworld.TriangleOverworldFactory;
import de.thelearningtriangle.core.triangle.LearningTriangle;
import de.thelearningtriangle.qlearning.RecursiveQTriangle.TriangleMoveData;

public class Train
{
	
	public static void main(String[] args) throws NoMapException
	{
		int depth = 12;
		new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				File f = new File("train1.csv");
				while (true)
				{
					TriangleOverworld overworld = TriangleOverworldFactory.generateOverworld(100,
							new Random(System.currentTimeMillis()));
					Point randomSpawningPoint;
					List<RecursiveQTriangle.TriangleMoveData> bestMoves = null;
					try
					{
						randomSpawningPoint = overworld.getRandomSpawningPoint();
						RecursiveQTriangle qTriangle = new RecursiveQTriangle(overworld);
						LearningTriangle triangle = new LearningTriangle();
						bestMoves = qTriangle.calculateBestMoves(randomSpawningPoint, triangle, depth);
					}
					catch (NoMapException e)
					{
						e.printStackTrace();
					}
					bestMoves.remove(0);
					System.out.println("-");
					FileWriter writer;
					try
					{
						writer = new FileWriter(f, true);
						for (TriangleMoveData triangleMoveData : bestMoves)
						{
							writer.write(triangleMoveData.toString());
						}
						writer.flush();
					}
					catch (IOException e)
					{
						e.printStackTrace();
					}
				}
			}
			
		}).start();
		
		new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				File f = new File("train2.csv");
				while (true)
				{
					TriangleOverworld overworld = TriangleOverworldFactory.generateOverworld(100,
							new Random(System.currentTimeMillis()));
					Point randomSpawningPoint;
					List<RecursiveQTriangle.TriangleMoveData> bestMoves = null;
					try
					{
						randomSpawningPoint = overworld.getRandomSpawningPoint();
						RecursiveQTriangle qTriangle = new RecursiveQTriangle(overworld);
						LearningTriangle triangle = new LearningTriangle();
						bestMoves = qTriangle.calculateBestMoves(randomSpawningPoint, triangle, depth);
					}
					catch (NoMapException e)
					{
						e.printStackTrace();
					}
					bestMoves.remove(0);
					System.out.println("-");
					FileWriter writer;
					try
					{
						writer = new FileWriter(f, true);
						for (TriangleMoveData triangleMoveData : bestMoves)
						{
							writer.write(triangleMoveData.toString());
						}
						writer.flush();
					}
					catch (IOException e)
					{
						e.printStackTrace();
					}
				}
				
			}
			
		}).start();
		
		new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				File f = new File("test1.csv");
				while (true)
				{
					TriangleOverworld overworld = TriangleOverworldFactory.generateOverworld(100,
							new Random(System.currentTimeMillis()));
					Point randomSpawningPoint;
					List<RecursiveQTriangle.TriangleMoveData> bestMoves = null;
					try
					{
						randomSpawningPoint = overworld.getRandomSpawningPoint();
						RecursiveQTriangle qTriangle = new RecursiveQTriangle(overworld);
						LearningTriangle triangle = new LearningTriangle();
						bestMoves = qTriangle.calculateBestMoves(randomSpawningPoint, triangle, depth);
					}
					catch (NoMapException e)
					{
						e.printStackTrace();
					}
					bestMoves.remove(0);
					System.out.println("-");
					FileWriter writer;
					try
					{
						writer = new FileWriter(f, true);
						for (TriangleMoveData triangleMoveData : bestMoves)
						{
							writer.write(triangleMoveData.toString());
						}
						writer.flush();
					}
					catch (IOException e)
					{
						e.printStackTrace();
					}
				}
				
			}
			
		}).start();
		
		new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				File f = new File("test2.csv");
				while (true)
				{
					TriangleOverworld overworld = TriangleOverworldFactory.generateOverworld(100,
							new Random(System.currentTimeMillis()));
					Point randomSpawningPoint;
					List<RecursiveQTriangle.TriangleMoveData> bestMoves = null;
					try
					{
						randomSpawningPoint = overworld.getRandomSpawningPoint();
						RecursiveQTriangle qTriangle = new RecursiveQTriangle(overworld);
						LearningTriangle triangle = new LearningTriangle();
						bestMoves = qTriangle.calculateBestMoves(randomSpawningPoint, triangle, depth);
					}
					catch (NoMapException e)
					{
						e.printStackTrace();
					}
					bestMoves.remove(0);
					System.out.println("-");
					FileWriter writer;
					try
					{
						writer = new FileWriter(f, true);
						for (TriangleMoveData triangleMoveData : bestMoves)
						{
							writer.write(triangleMoveData.toString());
						}
						writer.flush();
					}
					catch (IOException e)
					{
						e.printStackTrace();
					}
				}
				
			}
			
		}).start();
	}
	
}
