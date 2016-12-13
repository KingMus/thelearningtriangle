package de.thelearningtriangle.core.overworld;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

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
        assertEquals(64, underTest.getFieldMatrix().getMatrix().length);
        assertEquals(64, underTest.getFieldMatrix().getMatrix()[0].length);
    }
}
