package de.thelearningtriangle.core.overworld.field;

import de.thelearningtriangle.core.LearningTriangle;

public class EnergyField extends AbstractField
{
	public static final Integer energy = 50;

	public EnergyField()
	{
		super(FieldType.ENERGY);
	}

	@Override
	protected void accessEvent(LearningTriangle learningTriangle)
	{
		learningTriangle.addEnergy(energy);
	}
}