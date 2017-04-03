package de.thelearningtriangle.core.overworld;

import java.util.ArrayList;
import java.util.List;

import de.thelearningtriangle.core.LearningTriangle;

public abstract class Overworld
{
    private int size;
    private FieldMatrix overworldFieldMatrix;
    private List<LearningTriangle> learningTriangles;
    
    public Overworld(int size)
    {
        this.size = size;
        this.overworldFieldMatrix = FieldMatrixFactory.generateOverworldMatrixFor(14L, size);
        this.learningTriangles = new ArrayList<LearningTriangle>();
    }
    
    public int getSize()
    {
        return this.size;
    }
    
    protected FieldMatrix getFieldMatrix()
    {
        return this.overworldFieldMatrix;
    }
}