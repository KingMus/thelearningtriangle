package de.thelearningtriangle.core.overworld.field;

import de.thelearningtriangle.core.triangle.LearningTriangle;

public class EnergyField extends AbstractField {
	public static final Integer energy = 50;

	public EnergyField() {
		super(FieldType.ENERGY);
	}

	@Override
	public void access(LearningTriangle learningTriangle) {
		learningTriangle.addEnergy(energy);
	}
}