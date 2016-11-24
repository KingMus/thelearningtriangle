package de.thelearningtriangle.core.overworld;

import java.util.Date;

import de.thelearningtriangle.core.LearningTriangle;

public class PoisonField extends AbstractField
{
    private long poisonTimeInMs;
    
    public PoisonField()
    {
        super(FieldType.POISON);
        poisonTimeInMs = 5000; // 5 Seconds
    }
    
    @Override
    protected void accessEvent(LearningTriangle learningTriangle)
    {
        long poisonTime = poisonTimeInMs += new Date().getTime();
        learningTriangle.setHighConsumptionCycles(poisonTime);
    }
}