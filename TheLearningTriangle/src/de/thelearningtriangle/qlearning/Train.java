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

public class Train {
	static File testFile = new File("test.csv");
	static File trainFile = new File("train.csv");

	public static Thread createRunner(File file) {
		return new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					TriangleOverworld overworld = TriangleOverworldFactory
							.generateOverworld(100, new Random(System.currentTimeMillis()));
					Point randomSpawningPoint;
					List<RecursiveQTriangle.TriangleMoveData> bestMoves = null;
					try {
						randomSpawningPoint = overworld.getRandomSpawningPoint();
						RecursiveQTriangle qTriangle = new RecursiveQTriangle(overworld);
						LearningTriangle triangle = new LearningTriangle();
						bestMoves = qTriangle.calculateBestMoves(randomSpawningPoint, triangle, 13);
					} catch (NoMapException e) {
						e.printStackTrace();
					}
					bestMoves.remove(0);
					System.out.println("- Finished with score: " + bestMoves.get(0).getScore());
					FileWriter writer;
					try {
						synchronized (file) {
							writer = new FileWriter(file, true);
							for (int i = 0; i < 4; i++) {
								TriangleMoveData triangleMoveData = bestMoves.get(i);
								writer.write(triangleMoveData.toString());
							}
							writer.flush();
							writer.close();
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}

		});
	}

	public static void main(String[] args) {
		createRunner(trainFile).start();
		createRunner(trainFile).start();

		createRunner(testFile).start();
		createRunner(testFile).start();
	}

}
