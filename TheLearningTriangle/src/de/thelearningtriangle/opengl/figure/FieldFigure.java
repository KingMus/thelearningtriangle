package de.thelearningtriangle.opengl.figure;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.fixedfunc.GLMatrixFunc;

public class FieldFigure extends AbstractFigure
{
    public FieldFigure(float posX, float posY)
    {
        super(GLMatrixFunc.GL_MODELVIEW, GL2.GL_QUADS);
        this.posX = posX;
        this.posY = posY;
    }
    
    @Override
    protected float[][] getBaseLearningTriangleVectors()
    {
        float[][] vectors = new float[4][3];
        //TODO: Define Vectors
        return vectors;
    }
}
