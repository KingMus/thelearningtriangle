package de.thelearningtriangle.core.controller;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import de.thelearningtriangle.core.exceptions.FieldAccessException;
import de.thelearningtriangle.core.exceptions.NoMapException;
import de.thelearningtriangle.core.model.overworld.TriangleOverworld;
import de.thelearningtriangle.core.overworld.field.AbstractField;
import de.thelearningtriangle.core.overworld.field.FieldType;

public class TriangleOverworldFactory
{
	private static Random random;
	
	//variables for triangle spawnpoint
	private static int triangleX;
	private static int triangleY;
	
	private static int mode = defineMode();
	
	private static int defineMode() {
		Object[] options = { "Random!", "Load..." };
		return JOptionPane.showOptionDialog(null, "Random map or load map?", "TLT",
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, // do not use a custom Icon
				options, // the titles of buttons
				options[0]);
	}
	
	public static TriangleOverworld getOverworldMap() throws NoMapException, FieldAccessException {
		int worldSize;
		TriangleOverworld overworld;
		if (mode == 0)
		{
			worldSize = Integer.parseInt(JOptionPane.showInputDialog("Size of Map:"));
			overworld = TriangleOverworldFactory.generateOverworld(worldSize, random);
			overworld.setTriangle(overworld.getRandomSpawningPoint());
		} else
		{
			JFileChooser fc = new JFileChooser();
			fc.showOpenDialog(null);
			
			List<String[]> mapData = TriangleOverworldFileLoader.parseMapFromFile(fc.getSelectedFile());
			overworld = TriangleOverworldFactory.loadOverworld(mapData);
			overworld.setTriangle(TriangleOverworldFactory.getTriangleX(), TriangleOverworldFactory.getTriangleY());
			worldSize = mapData.size();
		}
		return overworld;
	}
	
	
	public static TriangleOverworld generateOverworld(int worldSize, Random random)
	{
		TriangleOverworldFactory.random = random;
		
		TriangleOverworld triangleOverworld = new TriangleOverworld(random);
		
		AbstractField[][] worldMap = generateField(worldSize);
		triangleOverworld.setMap(worldMap);
		
//		TriangleOverworldFileLoader.writeFileFromMap(worldMap);
		
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
	 * @author Marco Mueller
	 * @return an usable TriangleOverworld triangleOverworld
	 * @throws FieldAccessException 
	 * @throws NoMapException 
	 */
	public static TriangleOverworld loadOverworld(List<String[]> mapData) throws NoMapException, FieldAccessException
	{

		TriangleOverworld triangleOverworld = new TriangleOverworld(random);
		
		AbstractField[][] worldMap = convertStringMapToFieldMap(triangleOverworld, mapData);
		
		triangleOverworld.setMap(worldMap);
		
		return triangleOverworld;
	}

	/**
	 * converts the String mapData into an usable map
	 * @param mapData
	 * @author Marco Mueller
	 * @param triangleOverworld 
	 * @return AbstractField[][] worldMap
	 */
	private static AbstractField[][] convertStringMapToFieldMap(TriangleOverworld triangleOverworld, List<String[]> mapData)
	{
		AbstractField[][] worldMap = new AbstractField[mapData.size()][mapData.size()];
		
		for (int i = 0; i < mapData.size(); i++)
		{
			for (int j = 0; j < worldMap.length; j++)
			{
				switch (mapData.get(j)[i])
				{
				case "9":
					triangleX = i;
					triangleY = j;
					worldMap[i][j] = FieldType.NORMAL.newInstance();
					break;
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

	public static int getTriangleX()
	{
		return triangleX;
	}

	public static int getTriangleY()
	{
		return triangleY;
	}

	public static int getMode() {
		return mode;
	}
}