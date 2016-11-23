package de.thelearningtriangle.core.overworld;

import de.thelearningtriangle.core.LearningTriangle;

public class DeathField extends Field
{
	
	public DeathField()
	{
		super(FieldType.DEATH);
	}
	
	@Override
	protected void accessEvent(LearningTriangle learningTriangle)
	{
		float minDeathAmount = (learningTriangle.getEnergy() + 1) * (-1);
		learningTriangle.addEnergy(minDeathAmount);
	}
}