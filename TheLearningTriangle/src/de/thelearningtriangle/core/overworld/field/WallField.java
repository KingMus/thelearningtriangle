package de.thelearningtriangle.core.overworld.field;

import de.thelearningtriangle.core.LearningTriangle;
import de.thelearningtriangle.core.overworld.FieldAccessException;

public class WallField extends AbstractField
{

	public WallField()
	{
		super(FieldType.WALL);
	}

	@Override
	protected void accessEvent(LearningTriangle learningTriangle) throws FieldAccessException
	{
		throw new FieldAccessException();
	}
}