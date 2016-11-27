package de.thelearningtriangle.opengl.figure;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.fixedfunc.GLMatrixFunc;

import de.thelearningtriangle.core.overworld.field.FieldType;

public class FieldFigure extends AbstractFigure
{
	private FieldType fieldType;
	private float size;
	
	public FieldFigure(float posX, float posY, float size, FieldType fieldType)
	{
		super(GLMatrixFunc.GL_MODELVIEW, GL2.GL_QUADS);
		this.posX = posX;
		this.posY = posY;
		this.fieldType = fieldType;
		this.size = size;
		setFigureVectors(getBaseVectors());
		// TODO: Do something with the fieldType field
	}
	
	@Override
	protected float[][] getBaseVectors()
	{
		float[][] vectors = new float[4][3];
		float minusHalfSize = -size / 2f;
		float plusHalfSize = size / 2f;
		vectors[0] = new float[]
		{
			minusHalfSize, plusHalfSize, 0f
		};
		vectors[1] = new float[]
		{
			plusHalfSize, plusHalfSize, 0f
		};
		vectors[2] = new float[]
		{
			plusHalfSize, minusHalfSize, 0f
		};
		vectors[3] = new float[]
		{
			minusHalfSize, minusHalfSize, 0f
		};
		return vectors;
	}
}
