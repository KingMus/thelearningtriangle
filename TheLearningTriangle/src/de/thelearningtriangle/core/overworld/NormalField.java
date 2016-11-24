package de.thelearningtriangle.core.overworld;

import de.thelearningtriangle.core.LearningTriangle;

public class NormalField extends AbstractField
{
	
	public NormalField()
	{
		super(FieldType.NORMAL);
	}
	
	@Override
	protected void accessEvent(LearningTriangle learningTriangle)
	{
		// Here does nothing happen!
	}
}