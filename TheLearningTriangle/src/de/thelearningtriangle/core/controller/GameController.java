package de.thelearningtriangle.core.controller;

import java.util.List;

import de.thelearningtriangle.core.exceptions.FieldAccessException;
import de.thelearningtriangle.core.exceptions.NoMapException;
import de.thelearningtriangle.core.exceptions.TriangleDeathException;
import de.thelearningtriangle.core.model.overworld.Direction;
import de.thelearningtriangle.core.model.overworld.TriangleOverworld;
import de.thelearningtriangle.core.model.triangle.TrianglePosition;

public class GameController {

	public static void runTheGame(TriangleOverworld overworld)
			throws NoMapException, FieldAccessException, InterruptedException {
		TrianglePosition trianglePosition = overworld.getTrianglePositions().get(0);
		List<Integer> vv = overworld.getVisionVectorFor(trianglePosition.getPoint());
		overworld.moveTriangle(trianglePosition, Direction.SOUTH);

		try {
			trianglePosition.getLearningTriangle().cycle();
		} catch (TriangleDeathException e) {
			setNewTriangleSpawnPoint(overworld);
		}

		Thread.sleep(300);
	}

	private static void setNewTriangleSpawnPoint(TriangleOverworld overworld)
			throws NoMapException, FieldAccessException {
		overworld.getTrianglePositions().clear();

		if (TriangleOverworldFactory.getMode() == 0) {
			overworld.setTriangle(overworld.getRandomSpawningPoint());
		} else {
			overworld.setTriangle(TriangleOverworldFactory.getTriangleX(), TriangleOverworldFactory.getTriangleY());
		}
	}

}
