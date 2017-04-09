package de.thelearningtriangle.core.overworld.field;

import de.thelearningtriangle.core.triangle.LearningTriangle;

public class PoisonField extends AbstractField
{
	public static final Integer consumption = 10; // can be changed
	public static final Integer consumptionCycles = 5;
	
	public PoisonField()
	{
		super(FieldType.POISON);
	}
	
	@Override
	protected void accessEvent(LearningTriangle learningTriangle)
	{
		learningTriangle.setConsumption(consumption);
		learningTriangle.setHighConsumptionCycles(consumptionCycles);
	}
}