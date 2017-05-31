package de.thelearningtriangle.core.overworld;

import java.util.Random;

import de.thelearningtriangle.core.overworld.field.AbstractField;
import de.thelearningtriangle.core.overworld.field.FieldType;

public class TriangleOverworldFactory {
	private static Random random;

	public static TriangleOverworld generateOverworld(int size, Random random) {
		TriangleOverworldFactory.random = random;

		TriangleOverworld triangleOverworld = new TriangleOverworld(random);

		triangleOverworld.setMap(generateField(size));

		return triangleOverworld;
	}

	private static AbstractField[][] generateField(int size) {

		AbstractField[][] field = generateRandomField(size);

		setBorder(field);

		return field;
	}

	private static AbstractField[][] generateRandomField(int size) {
		AbstractField[][] field = new AbstractField[size][size];

		for (int j = 0; j < field.length; j++) {
			for (int i = 0; i < field[j].length; i++) {
				field[j][i] = FieldType.getRandomFieldType(random).newInstance();
			}
		}
		return field;
	}

	private static void setBorder(AbstractField[][] field) {
		int size = field.length;
		field[0] = getWallVector(size);
		field[size - 1] = getWallVector(size);
		setSideFieldsToWallsWith(field);
	}

	private static void setSideFieldsToWallsWith(AbstractField[][] field) {
		int size = field.length;
		for (int i = 1; i < size - 1; i++) {
			field[i][0] = FieldType.WALL.newInstance();
			field[i][size - 1] = FieldType.WALL.newInstance();
		}
	}

	private static AbstractField[] getWallVector(int size) {
		AbstractField[] wallVector = new AbstractField[size];
		for (int i = 0; i < wallVector.length; i++) {
			wallVector[i] = FieldType.WALL.newInstance();
		}
		return wallVector;
	}
}