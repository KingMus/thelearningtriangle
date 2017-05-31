package de.thelearningtriangle.core;

import java.util.List;
import java.util.Random;

import com.jogamp.nativewindow.util.Point;

import de.thelearningtriangle.core.overworld.FieldAccessException;
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
		Game game = new Game();
		int size = 30;
		
		for (int i = 0; i < 2000; i++)
		{
			try
			{
				TriangleOverworld overworld = TriangleOverworldFactory.generateOverworld(size, random);
				List<Integer> visionVectorFor = overworld.getVisionVectorFor(new Point(10, 10));
				
				visionVectorFor.stream()
						.map(x -> new StringBuilder().append(x).append(" ").toString())
						.forEach(System.out::print);
				
				System.out.println(visionVectorFor.size());
				
				for (int ix = 0; ix < 5; ix++)
				{
					overworld.setTriangle(overworld.getRandomSpawningPoint());
				}
				
				DrawableOverworldFactory drawableOverworldFactory = new DrawableOverworldFactory();
				List<DrawableFigure> overworldFigures = drawableOverworldFactory.getDrawableFiguresFor(overworld);
				overworldFigures.stream().forEach(game::registerDrawableFigure);
				
				for (TrianglePosition position : overworld.getTrianglePositions())
				{
					LearningTriangleFigure triangle = new LearningTriangleFigure(
							(position.getPoint().getX() * (1.9f / size)) - 0.95f,
							(position.getPoint().getY() * -(1.9f / size)) + 0.95f, 1.5f / size);
					game.registerDrawableFigure(triangle);
				}
				
				game.canvas.display();
				Thread.sleep(1000);
				
				overworldFigures.stream().forEach(game::unregisterDrawableFigure);
				
				for (DrawableFigure drawableFigure : overworldFigures)
				{
					game.unregisterDrawableFigure(drawableFigure);
				}
			}
			catch (FieldAccessException e)
			{
			}
		}
	}
}