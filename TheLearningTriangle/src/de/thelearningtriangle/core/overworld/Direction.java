package de.thelearningtriangle.core.overworld;

public enum Direction
{
	NORTH(0, 1), EAST(1, 0), SOUTH(0, -1), WEST(-1, 0);
	
	private int changeInX;
	private int changeInY;
	
	private Direction(int changeInX, int changeInY)
	{
		this.changeInX = changeInX;
		this.changeInY = changeInY;
	}
	
	public int getChangeInX()
	{
		return changeInX;
	}
	
	public int getChangeInY()
	{
		return changeInY;
	}
}