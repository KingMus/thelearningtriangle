package de.thelearningtriangle.core.overworld;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.jogamp.nativewindow.util.Point;

import de.thelearningtriangle.core.LearningTriangle;
import de.thelearningtriangle.core.overworld.field.AbstractField;
import de.thelearningtriangle.core.overworld.field.DeathField;
import de.thelearningtriangle.core.overworld.field.WallField;

public class TriangleOverworld
{
	private int size;

	private AbstractField[][] field;

	private Random random;

	public TriangleOverworld(int size)
	{
		this(size, new Random());
	}

	public TriangleOverworld(int size, Random random)
	{
		this.size = size;
		this.random = random;
	}

	public void setField(AbstractField[][] field)
	{
		this.field = field;
	}

	public int getSize()
	{
		return size;
	}

	public AbstractField getField(int x, int y)
	{
		return field[x][y];
	}

	public void setTriangle(int x, int y) throws FieldAccessException
	{
		field[x][y].access(new LearningTriangle());
	}

	public Point getRandomSpawningPoint()
	{
		List<Point> points = new ArrayList<Point>();
		for (int y = 0; y < field.length; y++)
		{
			AbstractField[] abstractFields = field[y];
			for (int x = 0; x < abstractFields.length; x++)
			{
				AbstractField abstractField = abstractFields[x];
				if (!((WallField.class.isInstance(abstractField)) | DeathField.class.isInstance(abstractField)))
				{
					points.add(new Point(x, y));
				}
			}
		}
		return points.get(random.nextInt(points.size()));
	}
}