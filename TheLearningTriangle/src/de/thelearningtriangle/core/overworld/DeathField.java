package de.thelearningtriangle.core.overworld;

import de.thelearningtriangle.core.LearningTriangle;

public class DeathField extends AbstractField
{
	
	public DeathField()
	{
		super(FieldType.DEATH);
	}
	
	@Override
	protected void accessEvent(LearningTriangle learningTriangle)
	{
		float deathEnergyAmount = (learningTriangle.getEnergy() + 1) * (-1);
		learningTriangle.addEnergy(deathEnergyAmount);
	}
}