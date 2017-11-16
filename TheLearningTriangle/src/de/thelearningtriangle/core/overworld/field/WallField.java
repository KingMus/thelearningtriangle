package de.thelearningtriangle.core.overworld.field;

import de.thelearningtriangle.core.exceptions.FieldAccessException;
import de.thelearningtriangle.core.model.triangle.LearningTriangle;

public class WallField extends AbstractField {
	public WallField() {
		super(FieldType.WALL);
	}

	@Override
	public void access(LearningTriangle learningTriangle) throws FieldAccessException {
		throw new FieldAccessException();
	}
}