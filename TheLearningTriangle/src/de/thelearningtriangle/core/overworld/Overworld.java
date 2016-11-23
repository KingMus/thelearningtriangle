package de.thelearningtriangle.core.overworld;

public class Overworld
{
	private int size;
	private Field[][] overworldMatrix;
	
	public Overworld(int size)
	{
		this.size = size;
		this.overworldMatrix = new Field[size][size];
	}
	
	public int getSize()
	{
		return this.size;
	}
	
	public Field[][] getOverworldMatrix()
	{
		return this.overworldMatrix;
	}
}