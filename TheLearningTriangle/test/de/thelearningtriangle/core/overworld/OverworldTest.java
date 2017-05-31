package de.thelearningtriangle.core.overworld;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import com.jogamp.nativewindow.util.Point;

import de.thelearningtriangle.core.overworld.field.AbstractField;
import de.thelearningtriangle.core.overworld.field.DeathField;
import de.thelearningtriangle.core.overworld.field.WallField;

public class OverworldTest {
	Random random = new Random(System.currentTimeMillis());

	@Test
	public void anOverworldWithSizeOneCanBeCreated() throws Exception {
		TriangleOverworld overworld = TriangleOverworldFactory.generateOverworld(1, random);
		assertThat(overworld.getSize(), CoreMatchers.is(1));
	}

	@Test
	public void anOverworldWithSizeTwoCanBeCreated() throws Exception {
		TriangleOverworld overworld = TriangleOverworldFactory.generateOverworld(2, random);
		assertThat(overworld.getSize(), CoreMatchers.is(2));
	}

	@Test
	public void outerFieldOfAThreeByThreeOverworldAreWalls() throws Exception {
		TriangleOverworld overworld = TriangleOverworldFactory.generateOverworld(3, random);
		assertTrue(WallField.class.isInstance(overworld.getField(0, 0)));
		assertTrue(WallField.class.isInstance(overworld.getField(0, 1)));
		assertTrue(WallField.class.isInstance(overworld.getField(0, 2)));

		assertTrue(WallField.class.isInstance(overworld.getField(2, 0)));
		assertTrue(WallField.class.isInstance(overworld.getField(2, 1)));
		assertTrue(WallField.class.isInstance(overworld.getField(2, 2)));

		assertTrue(WallField.class.isInstance(overworld.getField(1, 0)));
		assertTrue(WallField.class.isInstance(overworld.getField(1, 2)));
	}

	@Test
	public void canGetASpawningPoint() throws Exception {
		TriangleOverworld overworld = TriangleOverworldFactory.generateOverworld(100, random);
		Point randomSpawningPoint = overworld.getRandomSpawningPoint();
		AbstractField field = overworld.getField(randomSpawningPoint.getX(), randomSpawningPoint.getY());
		assertFalse(WallField.class.isInstance(field));
		assertFalse(DeathField.class.isInstance(field));
	}
}