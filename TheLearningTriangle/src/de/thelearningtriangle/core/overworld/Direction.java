package de.thelearningtriangle.core.overworld;

public enum Direction {
	NORTH(0, -1, 0), EAST(1, 0, 1), SOUTH(0, 1, 2), WEST(-1, 0, 3);

	private int changeInX;
	private int changeInY;
	private int label;

	private Direction(int changeInX, int changeInY, int label) {
		this.changeInX = changeInX;
		this.changeInY = changeInY;
		this.label = label;
	}

	public int getChangeInX() {
		return changeInX;
	}

	public int getChangeInY() {
		return changeInY;
	}

	public int getLabel() {
		return label;
	}

	public static Direction getDirectionFor(int bestIndex) {
		for (Direction d : values()) {
			if (d.getLabel() == bestIndex) {
				return d;
			}
		}
		return null;
	}
}