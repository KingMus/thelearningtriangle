package de.thelearningtriangle.opengl.figure;

import java.awt.Color;

import com.jogamp.opengl.GL2ES3;
import com.jogamp.opengl.fixedfunc.GLMatrixFunc;

/**
 * @author Noixes This class is a implementation from {@code AbstractFigure} for
 *         drawing a Triangle it implements some transformation-features such as
 *         rotate, move in direction
 */
public class LearningTriangleFigure extends AbstractFigure
{
    private float _HEIGHT;
    private float _BASE;
    
    /**
     * Initializes a drawable learning triangle with starting position of
     * parameters posX and posY
     * 
     * @param posX
     * @param posY
     */
    public LearningTriangleFigure(float posX, float posY, float sizeOfSquare)
    {
        super(GLMatrixFunc.GL_MODELVIEW, GL2ES3.GL_TRIANGLES, Color.BLUE);
        this.posX = posX;
        this.posY = posY;
        
        this._BASE = sizeOfSquare * 0.8f;
        this._HEIGHT = this._BASE * 1.5f;
        
        setFigureVectors(getBaseVectors());
    }
    
    /**
     * @author Noixes This Method defines the shape of the learning triangle and
     *         returns a vector with the standard x and y positions On position
     *         (0|0) the nose-point is drawn (It is also the beginning of the
     *         triangle)
     */
    protected final float[][] getBaseVectors()
    {
        // Initialize 3 Vectors of size 3 for the triangle
        float[][] learningTriangleVectors = new float[3][3];
        // Nose Point
        float halfHeight = _HEIGHT / 2f;
        float[] nosePoint = new float[]{halfHeight, 0.0f, 0.f};
        learningTriangleVectors[0] = nosePoint;
        // Right bottom Point
        float[] rightBottomPoint = new float[]{-halfHeight, -(_BASE / 2f), 0.f};
        learningTriangleVectors[1] = rightBottomPoint;
        // Left bottom Point
        float[] leftBottomPoint = new float[]{-halfHeight, (_BASE / 2f), 0.f};
        learningTriangleVectors[2] = leftBottomPoint;
        
        return learningTriangleVectors;
    }
}