package de.thelearningtriangle.core.overworld;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TriangleOverworldFileLoader
{
	
	/**
	 * This method creates a list of string arrays from a .csv-file. Values are
	 * splitted with ";".
	 */
	public static List<String[]> parseMapFromFile()
	{

		String file = 	System.getProperty("user.dir").replace('\\', '/')+"/MapFiles/anotherMap.txt";
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
}