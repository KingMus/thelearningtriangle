package de.thelearningtriangle.core.overworld.field;

import de.thelearningtriangle.core.LearningTriangle;

public class EnergyField extends AbstractField
{
	private float energy = 50.f;
	
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