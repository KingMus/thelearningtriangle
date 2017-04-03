package de.thelearningtriangle.core.overworld.field;

import java.util.Optional;

import de.thelearningtriangle.core.LearningTriangle;
import de.thelearningtriangle.core.overworld.FieldAccessException;

public abstract class AbstractField
{
	private FieldType fieldType;

	private Optional<LearningTriangle> triangle = Optional.empty();

	protected AbstractField(FieldType fieldType)
	{
		this.fieldType = fieldType;
	}

	public final FieldType getFieldType()
	{
		return this.fieldType;
	}

	public final void access(LearningTriangle learningTriangle) throws FieldAccessException
	{
		accessEvent(learningTriangle);
	}

	protected abstract void accessEvent(LearningTriangle learningTriangle) throws FieldAccessException;

	public Optional<LearningTriangle> getTriangle()
	{
		return triangle;
	}

	protected final void setTriangle(LearningTriangle learningTriangle)
	{
		this.triangle = Optional.of(learningTriangle);
	}
}