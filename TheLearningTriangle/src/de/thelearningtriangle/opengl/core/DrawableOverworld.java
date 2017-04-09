package de.thelearningtriangle.opengl.core;

import de.thelearningtriangle.core.overworld.TriangleOverworld;
import de.thelearningtriangle.core.overworld.field.FieldType;
import de.thelearningtriangle.opengl.figure.DrawableFigure;
import de.thelearningtriangle.opengl.figure.FieldFigure;

public class DrawableOverworld
{
	private Game game;
	private final float constantBorder = 0.95f;
	private final float twoConstantBorder = 2 * constantBorder;
	
	private TriangleOverworld triangleOverworld;
	
	public DrawableOverworld(TriangleOverworld triangleOverworld)
	{
		this.triangleOverworld = triangleOverworld;
		game = new Game();
		
		initializeOverworldRows();
	}
	
	private void initializeOverworldRows()
	{
		int size = triangleOverworld.getSize();
		float border_size = constantBorder / (float) size;
		
		for (int rowNumber = 0; rowNumber < size; rowNumber++)
		{
			float fieldPosY = constantBorder - ((twoConstantBorder * (float) rowNumber) / (float) size);
			for (int columnNumber = 0; columnNumber < size; columnNumber++)
			{
				FieldType cellFieldType = triangleOverworld.getField(rowNumber, columnNumber).getFieldType();
				
				float fieldPosX = -constantBorder + ((twoConstantBorder * (float) columnNumber) / (float) size);
				
				DrawableFigure figure = new FieldFigure(fieldPosX, fieldPosY, border_size, cellFieldType);
				game.registerDrawableFigure(figure);
			}
		}
	}
}