package de.thelearningtriangle.core.overworld;

import java.util.Random;

import de.thelearningtriangle.core.overworld.field.AbstractField;
import de.thelearningtriangle.core.overworld.field.FieldType;

public abstract class Overworld
{
	private int size;
	private AbstractField[][] overworldMatrix;
	
	public Overworld(int size)
	{
		this.size = size;
		this.overworldMatrix = generateOverworldMatixFor(System.currentTimeMillis());
	}
	
	public int getSize()
	{
		return this.size;
	}
	
	protected AbstractField[][] getOverworldMatrix()
	{
		return this.overworldMatrix;
	}
	
	private AbstractField[][] generateOverworldMatixFor(long seed)
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
		
		overworldMatrix[0] = getWallVector();
		overworldMatrix[size - 1] = getWallVector();
		
		setOuterFieldsToWallsWith(overworldMatrix);
		
		return overworldMatrix;
	}
	
	private void setOuterFieldsToWallsWith(AbstractField[][] overworldMatrix)
	{
		for (int i = 0; i < overworldMatrix.length; i++)
		{
			overworldMatrix[i][0] = FieldType.WALL.createNewFieldInstance();
			overworldMatrix[i][size - 1] = FieldType.WALL.createNewFieldInstance();
		}
	}
	
	private AbstractField[] getWallVector()
	{
		AbstractField[] wallVector = new AbstractField[size];
		for (int i = 0; i < wallVector.length; i++)
		{
			wallVector[i] = FieldType.WALL.createNewFieldInstance();
		}
		return wallVector;
	}
}