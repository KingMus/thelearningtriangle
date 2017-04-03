package de.thelearningtriangle.core.overworld;

import de.thelearningtriangle.core.overworld.field.AbstractField;
import de.thelearningtriangle.core.overworld.field.FieldType;
import de.thelearningtriangle.opengl.core.Game;
import de.thelearningtriangle.opengl.figure.DrawableFigure;
import de.thelearningtriangle.opengl.figure.FieldFigure;
import de.thelearningtriangle.opengl.figure.LearningTriangleFigure;

public class DrawableOverworld extends Overworld
{
    private Game game;
    private final float constantBorder = 0.95f;
    private final float twoConstantBorder = 2 * constantBorder;
    
    public DrawableOverworld(int size)
    {
        super(size);
        initializeOverworldRowsFor(getFieldMatrix());
        addTrianlge();
    }
    
    private void initializeOverworldRowsFor(FieldMatrix fieldMatrix)
    {
        game = new Game();
        AbstractField[][] overworldMatrix = fieldMatrix.getMatrix();
        for (int rowNumber = 0; rowNumber < overworldMatrix.length; rowNumber++)
        {
            AbstractField[] overworldFieldRow = overworldMatrix[rowNumber];
            initializeOverworldCellsFor(rowNumber, overworldFieldRow);
        }
    }
    
    private void initializeOverworldCellsFor(int rowNumber, AbstractField[] overworldFieldRow)
    {
        float fieldPosY = constantBorder - ((twoConstantBorder * (float) rowNumber) / (float) getSize());
        
        for (int cellNumber = 0; cellNumber < overworldFieldRow.length; cellNumber++)
        {
            FieldType cellFieldType = overworldFieldRow[cellNumber].getFieldType();
            
            float fieldPosX = -constantBorder + ((twoConstantBorder * (float) cellNumber) / (float) getSize());
            
            float size = constantBorder / (float) getSize();
            DrawableFigure figure = new FieldFigure(fieldPosX, fieldPosY, size, cellFieldType);
            game.registerDrawableFigure(figure);
        }
    }
    
    private void addTrianlge()
    {
        float sizeOfSquare = 1.0f / (float) getSize();
        LearningTriangleFigure triangleFigure = new LearningTriangleFigure(3f * sizeOfSquare, -3f * sizeOfSquare, sizeOfSquare);
        game.registerDrawableFigure(triangleFigure);
    }
}