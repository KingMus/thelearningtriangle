package de.thelearningtriangle.opengl.figure;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.fixedfunc.GLMatrixFunc;

import de.thelearningtriangle.core.overworld.field.FieldType;

public class FieldFigure extends AbstractFigure
{
	private float size;
	
	public FieldFigure(float posX, float posY, float size, FieldType fieldType)
	{
		super(GLMatrixFunc.GL_MODELVIEW, GL2.GL_QUADS, fieldType.getColor());
		this.posX = posX;
		this.posY = posY;
		this.size = size;
		setFigureVectors(getBaseVectors());
		// TODO: Do something with the fieldType field
	}
	
	@Override
	protected float[][] getBaseVectors()
	{
		float[][] vectors = new float[4][3];
		float minusSize = -size;
		vectors[0] = new float[]
		{
			minusSize, size, 0f
		};
		vectors[1] = new float[]
		{
			size, size, 0f
		};
		vectors[2] = new float[]
		{
			size, minusSize, 0f
		};
		vectors[3] = new float[]
		{
			minusSize, minusSize, 0f
		};
		return vectors;
	}
}
