package de.thelearningtriangle.core.overworld.field;

import de.thelearningtriangle.core.triangle.LearningTriangle;

public class EnergyField extends AbstractField
{
	public Integer energy = 25;
	
	public EnergyField()
	{
		super(FieldType.ENERGY);
	}
	
	@Override
	public void access(LearningTriangle learningTriangle)
	{
		learningTriangle.addEnergy(energy);
		energy = 1;
	}
}