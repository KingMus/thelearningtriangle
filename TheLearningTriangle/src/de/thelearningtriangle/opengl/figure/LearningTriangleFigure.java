package de.thelearningtriangle.opengl.figure;

import com.jogamp.opengl.GL2ES3;
import com.jogamp.opengl.fixedfunc.GLMatrixFunc;

import de.thelearningtriangle.opengl.figure.AbstractFigure;

/**
 * @author Noixes This class is a implementation from {@code AbstractFigure} for
 *         drawing a Triangle it implements some transformation-features such as
 *         rotate, move in direction
 */
public class LearningTriangleFigure extends AbstractFigure
{
	private static final float _HEIGHT = .15f;
	private static final float _BASE = .1f;
	
	/**
	 * Initializes a drawable learning triangle with starting position of
	 * parameters posX and posY
	 * 
	 * @param posX
	 * @param posY
	 */
	public LearningTriangleFigure(float posX, float posY)
	{
		super(GLMatrixFunc.GL_MODELVIEW, GL2ES3.GL_TRIANGLES);
		this.setFigureVectors(getBaseLearningTriangleVectors());
		this.posX = posX;
		this.posY = posY;
	}
	
	/**
	 * @author Noixes This Method defines the shape of the learning triangle and
	 *         returns a vector with the standard x and y positions On position
	 *         (0|0) the nose-point is drawn (It is also the beginning of the
	 *         triangle)
	 */
	private float[][] getBaseLearningTriangleVectors()
	{
		// Initialize 3 Vectors of size 3 for the triangle
		float[][] learningTriangleVectors = new float[3][3];
		// Nose Point
		float halfHeight = _HEIGHT / 2f;
		float[] nosePoint = new float[]
		{
			halfHeight, 0.0f, 0.f
		};
		learningTriangleVectors[0] = nosePoint;
		// Right bottom Point
		float[] rightBottomPoint = new float[]
		{
			-halfHeight, -(_BASE / 2f), 0.f
		};
		learningTriangleVectors[1] = rightBottomPoint;
		// Left bottom Point
		float[] leftBottomPoint = new float[]
		{
			-halfHeight, (_BASE / 2f), 0.f
		};
		learningTriangleVectors[2] = leftBottomPoint;
		
		return learningTriangleVectors;
	}
}