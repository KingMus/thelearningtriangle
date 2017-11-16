package de.thelearningtriangle.core.overworld.field;

import de.thelearningtriangle.core.model.triangle.LearningTriangle;

public class PoisonField extends AbstractField {
	public static final Integer consumption = 3; // can be changed
	public static final Integer consumptionCycles = 5;

	public PoisonField() {
		super(FieldType.POISON);
	}

	@Override
	public void access(LearningTriangle learningTriangle) {
		learningTriangle.setConsumption(consumption);
		learningTriangle.setHighConsumptionCycles(consumptionCycles);
	}
}