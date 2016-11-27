package de.thelearningtriangle.core;

import de.thelearningtriangle.core.overworld.field.FieldType;
import de.thelearningtriangle.opengl.core.Game;
import de.thelearningtriangle.opengl.figure.FieldFigure;
import de.thelearningtriangle.opengl.figure.LearningTriangleFigure;

public class Application
{
	
	public static void main(String[] args)
	{
		Game game = new Game();
		game.registerDrawableFigure(new LearningTriangleFigure(0.4f, 0.3f));
		game.registerDrawableFigure(new LearningTriangleFigure(0.1f, -0.4f));
		game.registerDrawableFigure(new LearningTriangleFigure(-0.2f, -0.1f));
		game.registerDrawableFigure(new FieldFigure(-0.9f, 0.9f, 0.1f, FieldType.NORMAL));
		game.registerDrawableFigure(new FieldFigure(0.9f, 0.9f, 0.1f, FieldType.NORMAL));
		game.registerDrawableFigure(new FieldFigure(0.9f, -0.9f, 0.1f, FieldType.NORMAL));
		game.registerDrawableFigure(new FieldFigure(-0.9f, -0.9f, 0.1f, FieldType.NORMAL));
	}
	
}