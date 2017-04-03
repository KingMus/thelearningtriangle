package de.thelearningtriangle.core.overworld;

import java.util.Random;

import de.thelearningtriangle.core.overworld.field.AbstractField;
import de.thelearningtriangle.core.overworld.field.FieldType;

public class OverworldFactory
{
	private static Random random;

	public static TriangleOverworld generateOverworld(int size)
	{
		return generateOverworld(size, 1);
	}

	public static TriangleOverworld generateOverworld(int size, int seed)
	{
		random = new Random(seed);
		TriangleOverworld triangleOverworld = new TriangleOverworld(size, random);

		triangleOverworld.setField(generateField(size));

		return triangleOverworld;
	}

	private static AbstractField[][] generateField(int size)
	{
		return generateField(size, 1);
	}

	private static AbstractField[][] generateField(int size, int seed)
	{

		AbstractField[][] field = new AbstractField[size][size];

		for (int j = 0; j < field.length; j++)
		{
			for (int i = 0; i < field[j].length; i++)
			{
				FieldType randomFieldType = FieldType.getFieldTypeFor(random.nextInt(100));
				field[j][i] = randomFieldType.createNewFieldInstance();
			}
		}

		field[0] = getWallVector(size);
		field[size - 1] = getWallVector(size);
		setOuterFieldsToWallsWith(field);

		return field;
	}

	private static void setOuterFieldsToWallsWith(AbstractField[][] field)
	{
		int size = field.length;
		for (int i = 1; i < size - 1; i++)
		{
			field[i][0] = FieldType.WALL.createNewFieldInstance();
			field[i][size - 1] = FieldType.WALL.createNewFieldInstance();
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