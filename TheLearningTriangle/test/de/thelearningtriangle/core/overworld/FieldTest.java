package de.thelearningtriangle.core.overworld;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import de.thelearningtriangle.core.overworld.field.AbstractField;
import de.thelearningtriangle.core.overworld.field.EnergyField;
import de.thelearningtriangle.core.overworld.field.FieldType;
import de.thelearningtriangle.core.overworld.field.PoisonField;
import de.thelearningtriangle.core.triangle.LearningTriangle;

public class FieldTest {
	@Test
	public void aTriangleCanAccessANormalField() throws Exception {
		AbstractField field = FieldType.NORMAL.createNewFieldInstance();
		field.access(new LearningTriangle());
	}

	@Test(expected = FieldAccessException.class)
	public void aTriangleTryingToAccessAWallFieldShouldThrowFieldAccessException() throws Exception {
		AbstractField field = FieldType.WALL.createNewFieldInstance();
		field.access(new LearningTriangle());
	}

	@Test
	public void aTriangleWhichAccessesADeathFieldHasZeroEnergy() throws Exception {
		AbstractField field = FieldType.DEATH.createNewFieldInstance();
		LearningTriangle learningTriangle = new LearningTriangle();
		field.access(learningTriangle);
		assertThat(learningTriangle.getEnergy(), CoreMatchers.is(0));
	}

	@Test
	public void aTriangleWhichAccessesAnEnergyFieldGetsEnergy() throws Exception {
		AbstractField field = FieldType.ENERGY.createNewFieldInstance();
		LearningTriangle learningTriangle = new LearningTriangle(1);
		field.access(learningTriangle);
		assertThat(learningTriangle.getEnergy(), CoreMatchers.is(1 + EnergyField.energy));
	}

	@Test
	public void aTriangleWhichAccessesAPoisonFieldIncreasesItsEnergyconsumption() throws Exception {
		AbstractField field = FieldType.POISON.createNewFieldInstance();
		LearningTriangle learningTriangle = new LearningTriangle();
		field.access(learningTriangle);
		assertThat(learningTriangle.getConsumption(), CoreMatchers.is(PoisonField.consumption));
	}

	@Test
	public void aTriangleWhichAccessesAPoisonFieldIncreasesItsEnergyconsumptioncycles() throws Exception {
		AbstractField field = FieldType.POISON.createNewFieldInstance();
		LearningTriangle learningTriangle = new LearningTriangle();
		field.access(learningTriangle);
		assertThat(learningTriangle.getHighConsumptionCycles(), CoreMatchers.is(PoisonField.consumptionCycles));
	}

	@Test
	public void triangleCanExistOnAField() throws Exception {
		AbstractField field = FieldType.NORMAL.createNewFieldInstance();
		field.access(new LearningTriangle());
	}

	@Test
	public void noTriangleExistOnAField() throws Exception {
		AbstractField field = FieldType.NORMAL.createNewFieldInstance();
	}

	@Test
	public void canGetIDFromAField() throws Exception {
		AbstractField field = FieldType.NORMAL.createNewFieldInstance();
		assertEquals(FieldType.NORMAL.getId(), FieldType.getIdFor(field));
	}
}