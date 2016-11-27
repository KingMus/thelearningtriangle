package de.thelearningtriangle.core.overworld;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TrainOverworldTest
{
	Overworld underTest = new TrainOverworld(64);
	
	@Test
	public void createOverworldAndGetItsCorrectSize() throws Exception
	{
		assertEquals(64, underTest.getSize());
	}
	
	@Test
	public void createFieldsWithTwoDimensionalArray() throws Exception
	{
		assertEquals(64, underTest.getOverworldMatrix().length);
		assertEquals(64, underTest.getOverworldMatrix()[0].length);
	}
}
