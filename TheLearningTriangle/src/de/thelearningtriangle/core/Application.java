package de.thelearningtriangle.core;

import java.util.List;
import java.util.Random;

import com.jogamp.nativewindow.util.Point;

import de.thelearningtriangle.classifier.LinearDirectionClassifier;
import de.thelearningtriangle.core.overworld.TriangleOverworld;
import de.thelearningtriangle.core.overworld.TriangleOverworldFactory;
import de.thelearningtriangle.core.overworld.TrianglePosition;
import de.thelearningtriangle.opengl.core.DrawableOverworldFactory;
import de.thelearningtriangle.opengl.core.Game;
import de.thelearningtriangle.opengl.figure.DrawableFigure;
import de.thelearningtriangle.opengl.figure.LearningTriangleFigure;

public class Application
{
	
	static Random random = new Random(System.currentTimeMillis());
	
	public static void main(String[] args) throws Exception
	{
		new LinearDirectionClassifier();
		Game game = new Game();
		int size = 30;
		
		for (int i = 0; i < 2000; i++)
		{
			
			TriangleOverworld overworld = TriangleOverworldFactory.generateOverworld(size, random);
			List<Integer> visionVectorFor = overworld.getVisionVectorFor(new Point(10, 10));
			
			visionVectorFor.stream().map(x -> new StringBuilder().append(x).append(" ").toString()).forEach(System.out::print);
			
			System.out.println(visionVectorFor.size());
			
			overworld.setTriangle(1,1);
			overworld.setTriangle(1,2);
			overworld.setTriangle(2,1);
			overworld.setTriangle(2,2);
			overworld.setTriangle(3,3);
			overworld.setTriangle(9,9);
			
			DrawableOverworldFactory drawableOverworldFactory = new DrawableOverworldFactory();
			List<DrawableFigure> overworldFigures = drawableOverworldFactory.getDrawableFiguresFor(overworld);
			overworldFigures.stream().forEach(game::registerDrawableFigure);
			
			for (TrianglePosition position : overworld.getTrianglePositions()) {
					LearningTriangleFigure triangle = new LearningTriangleFigure((position.getPoint().getX()* (1.9f/size)) - 0.95f, (position.getPoint().getY()*-(1.9f/size)) + 0.95f, 1.5f/size);
					game.registerDrawableFigure(triangle);
			}

			game.canvas.display();
			Thread.sleep(1000);
			
			overworldFigures.stream().forEach(game::unregisterDrawableFigure);
			
			for (DrawableFigure drawableFigure : overworldFigures) {
				game.unregisterDrawableFigure(drawableFigure);
			}
		}
	}
}