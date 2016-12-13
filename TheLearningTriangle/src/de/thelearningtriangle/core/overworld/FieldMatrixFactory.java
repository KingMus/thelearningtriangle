package de.thelearningtriangle.core.overworld;

import java.util.Random;

import de.thelearningtriangle.core.overworld.field.AbstractField;
import de.thelearningtriangle.core.overworld.field.FieldType;

public class FieldMatrixFactory
{
    public static FieldMatrix generateOverworldMatixFor(long seed, int size)
    {
        Random random = new Random(seed);
        
        AbstractField[][] overworldMatrix = new AbstractField[size][size];
        
        for (int j = 0; j < overworldMatrix.length; j++)
        {
            for (int i = 0; i < overworldMatrix[j].length; i++)
            {
                FieldType randomFieldType = FieldType.getFieldTypeFor(random.nextInt(100));
                overworldMatrix[j][i] = randomFieldType.createNewFieldInstance();
            }
        }
        
        overworldMatrix[0] = getWallVector(size);
        overworldMatrix[size - 1] = getWallVector(size);
        
        setOuterFieldsToWallsWith(overworldMatrix);
        
        return new FieldMatrix(overworldMatrix, random);
    }
    
    private static void setOuterFieldsToWallsWith(AbstractField[][] overworldMatrix)
    {
        int size = overworldMatrix.length;
        for (int i = 1; i < size - 1; i++)
        {
            overworldMatrix[i][0] = FieldType.WALL.createNewFieldInstance();
            overworldMatrix[i][size - 1] = FieldType.WALL.createNewFieldInstance();
        }
    }
    
    private static AbstractField[] getWallVector(int size)
    {
        AbstractField[] wallVector = new AbstractField[size];
        for (int i = 0; i < wallVector.length; i++)
        {
            wallVector[i] = FieldType.WALL.createNewFieldInstance();
        }
        return wallVector;
    }
}
