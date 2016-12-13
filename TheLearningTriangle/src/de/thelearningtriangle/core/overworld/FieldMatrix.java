package de.thelearningtriangle.core.overworld;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.jogamp.nativewindow.util.Point;

import de.thelearningtriangle.core.overworld.field.AbstractField;
import de.thelearningtriangle.core.overworld.field.WallField;

public class FieldMatrix
{
    private AbstractField[][] abstractFieldMatrix;
    private Random random;
    
    public FieldMatrix(AbstractField[][] abstractFieldMatrix, Random random)
    {
        this.abstractFieldMatrix = abstractFieldMatrix;
        this.random = random;
    }
    
    public AbstractField[][] getMatrix()
    {
        return abstractFieldMatrix;
    }
    
    public Point getRandomSpawningPoint()
    {
        List<Point> points = new ArrayList<Point>();
        for (int y = 0; y < abstractFieldMatrix.length; y++)
        {
            AbstractField[] abstractFields = abstractFieldMatrix[y];
            for (int x = 0; x < abstractFields.length; x++)
            {
                AbstractField abstractField = abstractFields[x];
                if (!(abstractField instanceof WallField))
                {
                    points.add(new Point(x, y));
                }
            }
        }
        return points.get(random.nextInt(points.size()));
    }
}