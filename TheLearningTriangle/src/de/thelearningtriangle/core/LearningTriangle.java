package de.thelearningtriangle.core;

import java.sql.Timestamp;
import java.util.Date;

public class LearningTriangle
{
	private float totalEnergy;
	private float consumption;
	private Timestamp highConsumptionTimestamp;
	
	public LearningTriangle()
	{
		this(500);
	}
	
	public LearningTriangle(float startingEnergy)
	{
		this.totalEnergy = startingEnergy;
		this.consumption = -1.f;
		this.highConsumptionTimestamp = new Timestamp(0);
	}
	
	public void addEnergy(float energy)
	{
		this.totalEnergy += energy;
		if (this.totalEnergy <= 0)
		{
			// TODO: Here the Triangle should die
		}
	}
	
	public float getEnergy()
	{
		return totalEnergy;
	}
	
	public void setConsumptionTimestamp(long timestamp)
	{
		this.highConsumptionTimestamp = new Timestamp(timestamp);
	}
	
	public void cycle()
	{
		float consumption = this.consumption;
		if (new Date().getTime() < this.highConsumptionTimestamp.getTime())
		{
			consumption *= 2;
		}
		this.addEnergy(consumption);
	}
}