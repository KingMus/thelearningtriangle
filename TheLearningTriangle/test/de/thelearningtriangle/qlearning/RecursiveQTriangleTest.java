package de.thelearningtriangle.qlearning;

import java.util.List;
import java.util.Random;

import org.junit.Test;

import com.jogamp.nativewindow.util.Point;

import de.thelearningtriangle.core.overworld.NoMapException;
import de.thelearningtriangle.core.overworld.TriangleOverworld;
import de.thelearningtriangle.core.overworld.TriangleOverworldFactory;
import de.thelearningtriangle.core.triangle.LearningTriangle;

public class RecursiveQTriangleTest
{
	
	@Test
	public void basicMovementCalculation() throws NoMapException
	{
		TriangleOverworld overworld = TriangleOverworldFactory.generateOverworld(100, new Random(System.currentTimeMillis()));
		Point randomSpawningPoint = overworld.getRandomSpawningPoint();
		RecursiveQTriangle qTriangle = new RecursiveQTriangle(overworld);
		LearningTriangle triangle = new LearningTriangle();
		List<RecursiveQTriangle.TriangleMoveData> bestMoves = qTriangle.calculateBestMoves(randomSpawningPoint, triangle, 14);
		bestMoves.stream().forEach(System.out::print);
	}
}
