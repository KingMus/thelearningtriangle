package de.thelearningtriangle.core;

import de.thelearningtriangle.core.overworld.TriangleDeathException;

public class LearningTriangle
{
	private int totalEnergy;
	private int consumption;
	private int highConsumptionCycles;

	public LearningTriangle()
	{
		this(500);
	}

	public LearningTriangle(int startingEnergy)
	{
		this.totalEnergy = startingEnergy;
		this.consumption = 1;
		highConsumptionCycles = 0;
	}

	public void addEnergy(int energy)
	{
		this.totalEnergy += energy;
		if (this.totalEnergy <= 0)
		{
			// TODO: Here the Triangle should die
		}
	}

	public int getEnergy()
	{
		return totalEnergy;
	}

	public void setHighConsumptionCycles(int cycles)
	{
		this.highConsumptionCycles = cycles;
	}

	public int getConsumption()
	{
		return consumption;
	}

	public void setConsumption(int consumption)
	{
		this.consumption = consumption;
	}

	public int getHighConsumptionCycles()
	{
		return highConsumptionCycles;
	}

	public void cycle() throws TriangleDeathException
	{
		testIfTrianlgeDies();
		this.addEnergy(-consumption);
		calculateConsumption();
	}

	private void calculateConsumption()
	{
		if (consumption > 1)
		{
			calculatePoison();
		}
	}

	private void calculatePoison()
	{
		highConsumptionCycles--;
		if (highConsumptionCycles == 0)
		{
			consumption = 1;
		}
	}

	private void testIfTrianlgeDies() throws TriangleDeathException
	{
		if (this.totalEnergy == 0)
		{
			throw new TriangleDeathException();
		}
	}

	/*
	 * public void cycle() { float consumption = this.consumption; if
	 * (highConsumptionCycles > 0) { consumption *= 2; highConsumptionCycles--;
	 * } this.addEnergy(consumption); }
	 */
}