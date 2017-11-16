package de.thelearningtriangle.core.overworld.field;

import de.thelearningtriangle.core.exceptions.FieldAccessException;
import de.thelearningtriangle.core.model.triangle.LearningTriangle;

public abstract class AbstractField {
	private FieldType fieldType;

	protected AbstractField(FieldType fieldType) {
		this.fieldType = fieldType;
	}

	public void setFieldType(FieldType fieldType)
	{
		this.fieldType = fieldType;
	}

	public final FieldType getFieldType() {
		return this.fieldType;
	}

	public abstract void access(LearningTriangle learningTriangle) throws FieldAccessException;
}