package de.thelearningtriangle.opengl.core;

public enum Direction
{
	HORIZONTAL(0), VERTICAL(1);
	private int position;
	
	private Direction(int position)
	{
		this.position = position;
	}
	
	public int getDirectionCode()
	{
		return this.position;
	}
}
