package de.thelearningtriangle.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.jogamp.nativewindow.util.Point;

import de.thelearningtriangle.classifier.LinearDirectionClassifier;
import de.thelearningtriangle.core.overworld.Direction;
import de.thelearningtriangle.core.overworld.TriangleDeathException;
import de.thelearningtriangle.core.overworld.TriangleOverworld;
import de.thelearningtriangle.core.overworld.TriangleOverworldFactory;
import de.thelearningtriangle.core.overworld.TrianglePosition;
import de.thelearningtriangle.opengl.core.DrawableOverworldFactory;
import de.thelearningtriangle.opengl.core.Game;
import de.thelearningtriangle.opengl.figure.DrawableFigure;
import de.thelearningtriangle.opengl.figure.LearningTriangleFigure;
import de.thelearningtriangle.ui.MainWindow;

public class ApplicationSwing {

	static Random random = new Random(System.currentTimeMillis());

	public static void main(String[] args) throws Exception {

		MainWindow mainW = new MainWindow();
		
		LinearDirectionClassifier classifier = new LinearDirectionClassifier();
		Game game = new Game();
		int size = 20;
		TriangleOverworld overworld = TriangleOverworldFactory.generateOverworld(size, random);
		overworld.setTriangle(overworld.getRandomSpawningPoint());

		DrawableOverworldFactory drawableOverworldFactory = new DrawableOverworldFactory();
		List<DrawableFigure> overworldFigures = drawableOverworldFactory.getDrawableFiguresFor(overworld);
		overworldFigures.stream().forEach(game::registerDrawableFigure);

		while (true) {
			TrianglePosition trianglePosition = overworld.getTrianglePositions().get(0);
			List<Integer> vv = overworld.getVisionVectorFor(trianglePosition.getPoint());
			Direction predicted = classifier.predict(vv.toArray(new Integer[0]));
			overworld.moveTriangle(trianglePosition, predicted);
			List<LearningTriangleFigure> registeredFigs = new ArrayList<LearningTriangleFigure>();
			for (TrianglePosition position : overworld.getTrianglePositions()) {
				LearningTriangleFigure triangle = new LearningTriangleFigure(
						(position.getPoint().getX() * (1.9f / size)) - 0.95f,
						(position.getPoint().getY() * -(1.9f / size)) + 0.95f, 1.5f / size);
				registeredFigs.add(triangle);
				game.registerDrawableFigure(triangle);
			}

			game.canvas.display();
			Thread.sleep(2000);
			registeredFigs.stream().forEach(game::unregisterDrawableFigure);
			try {
				trianglePosition.getLearningTriangle().cycle();
				Point point = trianglePosition.getPoint();
				System.out.printf("CurrentField: %s\n", overworld.getField(point));
			} catch (TriangleDeathException e) {
				overworld.getTrianglePositions().clear();
				overworld.setTriangle(overworld.getRandomSpawningPoint());
			}
			System.out.printf("Energy: %s Distance: %s\n", trianglePosition.getLearningTriangle().getEnergy(),
					trianglePosition.getLearningTriangle().getDistance());
		}
	}
}