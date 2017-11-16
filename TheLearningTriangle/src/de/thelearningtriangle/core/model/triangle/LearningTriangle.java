package de.thelearningtriangle.core.model.triangle;

import de.thelearningtriangle.core.exceptions.TriangleDeathException;

public class LearningTriangle {
	private long totalEnergy;
	private int consumption;
	private int highConsumptionCycles;
	private long distance;

	public LearningTriangle() {
		this(10);
	}

	public LearningTriangle(LearningTriangle baseTriangle) {
		this.totalEnergy = baseTriangle.totalEnergy;
		this.consumption = baseTriangle.consumption;
		this.highConsumptionCycles = baseTriangle.highConsumptionCycles;
		this.distance = baseTriangle.distance;
	}

	public LearningTriangle(int startingEnergy) {
		this.totalEnergy = startingEnergy;
		this.consumption = 1;
		highConsumptionCycles = 0;
	}

	public void addEnergy(long energy) {
		this.totalEnergy += energy;
	}

	public long getEnergy() {
		return totalEnergy;
	}

	public long getDistance() {
		return distance;
	}

	public void setHighConsumptionCycles(int cycles) {
		this.highConsumptionCycles = cycles;
	}

	public int getConsumption() {
		return consumption;
	}

	public void setConsumption(int consumption) {
		this.consumption = consumption;
	}

	public int getHighConsumptionCycles() {
		return highConsumptionCycles;
	}

	public void cycle() throws TriangleDeathException {
		testIfTrianlgeDies();
		this.addEnergy(-consumption);
		calculateConsumption();
		distance++;
	}

	private void calculateConsumption() {
		if (consumption > 1) {
			calculatePoison();
		}
	}

	private void calculatePoison() {
		highConsumptionCycles--;
		if (highConsumptionCycles == 0) {
			consumption = 1;
		}
	}

	private void testIfTrianlgeDies() throws TriangleDeathException {
		if (this.totalEnergy <= 0) {
			throw new TriangleDeathException();
		}
	}
}