package de.thelearningtriangle.opengl.core;

public class Application
{

	public static void main(String[] args)
	{
		Game game = new Game();
		game.registerDrawableFigure(new LearningTriangleFigure(0.4f, 0.3f));
		game.registerDrawableFigure(new LearningTriangleFigure(0.1f, -0.4f));
		game.registerDrawableFigure(new LearningTriangleFigure(-0.2f, -0.1f));
	}

}
