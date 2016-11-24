package de.thelearningtriangle.core.overworld;

import de.thelearningtriangle.core.LearningTriangle;

public abstract class AbstractField
{
	private FieldType fieldType;
	
	protected AbstractField(FieldType fieldType)
	{
		this.fieldType = fieldType;
	}
	
	public final FieldType getFieldType()
	{
		return this.fieldType;
	}
	
	public final void access(LearningTriangle learningTriangle)
	{
		accessEvent(learningTriangle);
	}
	
	protected abstract void accessEvent(LearningTriangle learningTriangle);
}