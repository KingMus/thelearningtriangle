package de.thelearningtriangle.core.overworld;

import static org.junit.Assert.assertThat;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import de.thelearningtriangle.core.overworld.field.AbstractField;
import de.thelearningtriangle.core.overworld.field.FieldType;
import de.thelearningtriangle.core.overworld.field.PoisonField;
import de.thelearningtriangle.core.triangle.LearningTriangle;

public class LearningTriangleTest
{
	
	@Test
	public void aLearningTriangleLosesEnergyWhenCycle() throws Exception
	{
		LearningTriangle triangle = new LearningTriangle(10);
		triangle.cycle();
		assertThat(triangle.getEnergy(), CoreMatchers.is(9));
	}
	
	@Test(expected = TriangleDeathException.class)
	public void aLearningTriangleDiesIfItHasNoEnergyLeftInACycle() throws Exception
	{
		LearningTriangle triangle = new LearningTriangle(0);
		triangle.cycle();
	}
	
	@Test(expected = TriangleDeathException.class)
	public void aLearningTriangleDiesAfterOneCircleOnADeathField() throws Exception
	{
		LearningTriangle triangle = new LearningTriangle(0);
		
		AbstractField field = FieldType.DEATH.newInstance();
		field.access(triangle);
		triangle.cycle();
	}
	
	@Test
	public void aPoisonedTrianlgeHasNormalConsumptionAfterTheSetCycleAmountOfPoisonField() throws Exception
	{
		LearningTriangle triangle = new LearningTriangle(1000);
		AbstractField field = FieldType.POISON.newInstance();
		field.access(triangle);
		for (int i = 0; i < PoisonField.consumptionCycles + 1; i++)
		{
			triangle.cycle();
		}
		assertThat(triangle.getConsumption(), CoreMatchers.is(1));
	}
	
	@Test
	public void aPoisonedTriangleHasCorrectEnergyAfterConsumptionCycle() throws Exception
	{
		LearningTriangle triangle = new LearningTriangle(1000);
		AbstractField field = FieldType.POISON.newInstance();
		field.access(triangle);
		for (int i = 0; i < PoisonField.consumptionCycles; i++)
		{
			triangle.cycle();
		}
		assertThat(triangle.getEnergy(), CoreMatchers.is(950));
	}
	
}