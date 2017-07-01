package de.thelearningtriangle.opengl.core;

import java.util.ArrayList;
import java.util.List;

import de.thelearningtriangle.core.overworld.NoMapException;
import de.thelearningtriangle.core.overworld.TriangleOverworld;
import de.thelearningtriangle.core.overworld.field.FieldType;
import de.thelearningtriangle.opengl.figure.DrawableFigure;
import de.thelearningtriangle.opengl.figure.FieldFigure;

public class DrawableOverworldFactory {
	private final float constantBorder = 0.95f;
	private final float twoConstantBorder = 2 * constantBorder;

	public List<DrawableFigure> getDrawableFiguresFor(TriangleOverworld triangleOverworld) throws NoMapException {
		List<DrawableFigure> drawableFigures = new ArrayList<DrawableFigure>();
		int size = triangleOverworld.getSize();
		float border_size = constantBorder / size;

		for (int rowNumber = 0; rowNumber < size; rowNumber++) {
			float fieldPosY = constantBorder - ((twoConstantBorder * rowNumber) / size);
			for (int columnNumber = 0; columnNumber < size; columnNumber++) {
				FieldType cellFieldType = triangleOverworld.getField(columnNumber, rowNumber).getFieldType();

				float fieldPosX = -constantBorder + ((twoConstantBorder * columnNumber) / size);

				DrawableFigure figure = new FieldFigure(fieldPosX, fieldPosY, border_size, cellFieldType);
				drawableFigures.add(figure);
			}
		}
		return drawableFigures;
	}
}