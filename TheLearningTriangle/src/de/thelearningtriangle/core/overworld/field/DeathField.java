package de.thelearningtriangle.core.overworld.field;

import de.thelearningtriangle.core.triangle.LearningTriangle;

public class DeathField extends AbstractField
{
	
	public DeathField()
	{
		super(FieldType.DEATH);
	}
	
	@Override
	protected void accessEvent(LearningTriangle learningTriangle)
	{
		int deathEnergyAmount = (learningTriangle.getEnergy()) * (-1);
		learningTriangle.addEnergy(deathEnergyAmount);
	}
}