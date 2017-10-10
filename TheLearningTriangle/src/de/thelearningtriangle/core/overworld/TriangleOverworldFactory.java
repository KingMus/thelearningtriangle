package de.thelearningtriangle.core.overworld;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import de.thelearningtriangle.core.overworld.field.AbstractField;
import de.thelearningtriangle.core.overworld.field.FieldType;

public class TriangleOverworldFactory
{
	private static Random random;
	
	public static TriangleOverworld generateOverworld(int worldSize, Random random)
	{
		TriangleOverworldFactory.random = random;
		
		TriangleOverworld triangleOverworld = new TriangleOverworld(random);
		triangleOverworld.setMap(generateField(worldSize));
		
		return triangleOverworld;
	}
	
	private static AbstractField[][] generateField(int worldSize)
	{
		AbstractField[][] field = generateRandomMapMatrixOf(worldSize);
		setBorder(field);
		return field;
	}
	
	private static AbstractField[][] generateRandomMapMatrixOf(int worldSize)
	{
		AbstractField[][] field = IntStream.range(0, worldSize)
				.mapToObj(unusedBuffer -> generateRandomMapVectorOf(worldSize))
				.collect(Collectors.toList())
				.toArray(new AbstractField[0][0]);
		return field;
	}
	
	private static AbstractField[] generateRandomMapVectorOf(int worldSize)
	{
		return IntStream.range(0, worldSize)
				.mapToObj(unusedBuffer -> FieldType.getRandomFieldType(random))
				.map(FieldType::newInstance)
				.collect(Collectors.toList())
				.toArray(new AbstractField[0]);
	}
	
	private static void setBorder(AbstractField[][] field)
	{
		int size = field.length;
		field[0] = getWallVector(size);
		field[size - 1] = getWallVector(size);
		setSideFieldsToWallsWith(field);
	}
	
	private static void setSideFieldsToWallsWith(AbstractField[][] field)
	{
		int size = field.length;
		for (int i = 1; i < size - 1; i++)
		{
			field[i][0] = FieldType.WALL.newInstance();
			field[i][size - 1] = FieldType.WALL.newInstance();
		}
	}
	
	private static AbstractField[] getWallVector(int size)
	{
		AbstractField[] wallVector = new AbstractField[size];
		for (int i = 0; i < wallVector.length; i++)
		{
			wallVector[i] = FieldType.WALL.newInstance();
		}
		return wallVector;
	}

	/**
	 * loads an overworld-String from an file and converts it into a map
	 * @param mapData
	 * @param random
	 * @author Marco Müller
	 * @return an usable TriangleOverworld triangleOverworld
	 */
	public static TriangleOverworld loadOverworld(List<String[]> mapData, Random random)
	{

		TriangleOverworldFactory.random = random;
		
		TriangleOverworld triangleOverworld = new TriangleOverworld(random);
		
		AbstractField[][] worldMap = convertStringMapToFieldMap(mapData);
		
		triangleOverworld.setMap(worldMap);
		
		return triangleOverworld;
	}

	/**
	 * converts the String mapData into an usable map
	 * @param mapData
	 * @author Marco Müller
	 * @return AbstractField[][] worldMap
	 */
	private static AbstractField[][] convertStringMapToFieldMap(List<String[]> mapData)
	{
		AbstractField[][] worldMap = new AbstractField[mapData.size()][mapData.size()];
		
		for (int i = 0; i < mapData.size(); i++)
		{
			for (int j = 0; j < worldMap.length; j++)
			{
				switch (mapData.get(j)[i])
				{
				case "1":
					worldMap[i][j] = FieldType.NORMAL.newInstance();
					break;
				case "2":
					worldMap[i][j] = FieldType.WALL.newInstance();
					break;
				case "3":
					worldMap[i][j] = FieldType.POISON.newInstance();
					break;
				case "4":
					worldMap[i][j] = FieldType.DEATH.newInstance();
					break;
				case "5":
					worldMap[i][j] = FieldType.ENERGY.newInstance();
					break;
				default:
					worldMap[i][j] = FieldType.POISON.newInstance();
					break;
				}
			}
		}
		return worldMap;
	}
}