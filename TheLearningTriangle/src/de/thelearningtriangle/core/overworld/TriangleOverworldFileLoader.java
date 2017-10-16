package de.thelearningtriangle.core.overworld;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import de.thelearningtriangle.core.overworld.field.AbstractField;
import de.thelearningtriangle.core.overworld.field.FieldType;

public class TriangleOverworldFileLoader
{


	/**
	 * this method reads a file and creates a List of String-Arrays from the values
	 * @return List<String[]>
	 * @author Marco Mueller
	 */
	public static List<String[]> parseMapFromFile(String fileName)
	{

		String file = System.getProperty("user.dir").replace('\\', '/') + "/MapFiles/"+fileName;
		BufferedReader br = null;
		String splitChar = ",";

		String line = "";

		List<String[]> mapData = new ArrayList<String[]>();

		try
		{

			br = new BufferedReader(new FileReader(file));

			while ((line = br.readLine()) != null)
			{

				String[] oneLineOfData = line.split(splitChar);
				mapData.add(oneLineOfData);

			}

		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		} finally
		{
			if (br != null)
			{
				try
				{
					br.close();
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}

		return mapData;

	}

	/**
	 * this method creates a file from overworld data
	 * @param worldMap
	 * @author Marco Mueller
	 */
	public static void writeFileFromMap(AbstractField[][] worldMap)
	{

		PrintWriter fileWriter = null;

		try
		{

			fileWriter = new PrintWriter(new FileWriter(System.getProperty("user.dir").replace('\\', '/') + "/MapFiles/savedMap"));

			for (int i = 0; i < worldMap.length; i++)
			{
				for (int j = 0; j < worldMap.length; j++)
				{
					switch (worldMap[j][i].getFieldType())
					{
					case NORMAL:
						fileWriter.print("1");
						break;
					case WALL:
						fileWriter.print("2");
						break;
					case POISON:
						fileWriter.print("3");
						break;
					case DEATH:
						fileWriter.print("4");
						break;
					case ENERGY:
						fileWriter.print("5");
						break;
					default:
						fileWriter.print("1");
						break;
					}
					
					if(j<worldMap.length-1){
						fileWriter.print(",");
					}
					
				}
				
				fileWriter.println();
			}

		} catch (IOException e)
		{
			e.printStackTrace();
		} finally
		{			if (fileWriter != null)
				fileWriter.close();
		}
	}

}