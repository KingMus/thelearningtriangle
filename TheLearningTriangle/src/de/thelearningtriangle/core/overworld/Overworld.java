package de.thelearningtriangle.core.overworld;

public class Overworld
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
	
	public AbstractField[][] getOverworldMatrix()
	{
		return this.overworldMatrix;
	}
}