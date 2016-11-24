package de.thelearningtriangle.core;

public class LearningTriangle
{
    private float totalEnergy;
    private float consumption;
    private long highConsumptionCycles;
    
    public LearningTriangle()
    {
        this(500);
    }
    
    public LearningTriangle(float startingEnergy)
    {
        this.totalEnergy = startingEnergy;
        this.consumption = -1.f;
        highConsumptionCycles = 0;
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
    
    public void setHighConsumptionCycles(long cycles)
    {
        this.highConsumptionCycles = cycles;
    }
    
    public void cycle()
    {
        float consumption = this.consumption;
        if (highConsumptionCycles > 0)
        {
            consumption *= 2;
            highConsumptionCycles--;
        }
        this.addEnergy(consumption);
    }
}