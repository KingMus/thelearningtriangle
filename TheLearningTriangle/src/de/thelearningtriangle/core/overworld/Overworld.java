package de.thelearningtriangle.core.overworld;

import de.thelearningtriangle.core.overworld.field.AbstractField;

public abstract class Overworld
{
	private int size;
	private AbstractField[][] overworldMatrix;
	
	public Overworld(int size)
	{
		this.size = size;
		this.overworldMatrix = new AbstractField[size][size];
	}
	
	public int getSize()
	{
		return this.size;
	}
	
	protected AbstractField[][] getOverworldMatrix()
	{
		return this.overworldMatrix;
	}
}