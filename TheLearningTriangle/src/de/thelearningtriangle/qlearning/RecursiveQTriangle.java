package de.thelearningtriangle.qlearning;

import java.util.List;

import com.jogamp.nativewindow.util.Point;

import de.thelearningtriangle.core.overworld.Direction;
import de.thelearningtriangle.core.overworld.FieldAccessException;
import de.thelearningtriangle.core.overworld.NoMapException;
import de.thelearningtriangle.core.overworld.TriangleOverworld;
import de.thelearningtriangle.core.overworld.field.AbstractField;
import de.thelearningtriangle.core.triangle.LearningTriangle;

public class RecursiveQTriangle
{
	
	public TriangleOverworld overworld;
	
	public RecursiveQTriangle(TriangleOverworld overworld)
	{
		this.overworld = overworld;
	}
	
	public List<TrianlgeMoveData> calculateBestMove(TriangleScoreData data, int depth)
	{
		LearningTriangle currentTriangle = new LearningTriangle(data.getTriangle());
		try
		{
			AbstractField field = overworld.getField(data.getPoint());
			field.access(currentTriangle);
			long newScore = data.getScore() + currentTriangle.getDistance() + currentTriangle.getEnergy();
			TriangleScoreData newData = new TriangleScoreData(data.getPoint(), data.getTriangle(), newScore);
			
			if (depth == 0)
			{
				return null;
			}
			for (Direction currentDirection : Direction.values())
			{
				
			}
		}
		catch (NoMapException e)
		{
			e.printStackTrace();
		}
		catch (FieldAccessException e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
	
	class TriangleScoreData
	{
		private Point point;
		private LearningTriangle triangle;
		private long score;
		
		public TriangleScoreData(Point point, LearningTriangle triangle, long score)
		{
			this.point = new Point(point.getX(), point.getY());
			this.triangle = new LearningTriangle(triangle);
			this.score = score;
		}
		
		public Point getPoint()
		{
			return point;
		}
		
		public long getScore()
		{
			return score;
		}
		
		public LearningTriangle getTriangle()
		{
			return triangle;
		}
	}
	
	class TrianlgeMoveData
	{
		private Direction direction;
		private List<Integer> visionVector;
		private long score;
		
		public Direction getDirection()
		{
			return direction;
		}
		
		public void setDirection(Direction direction)
		{
			this.direction = direction;
		}
		
		public List<Integer> getVisionVector()
		{
			return visionVector;
		}
		
		public void setVisionVector(List<Integer> visionVector)
		{
			this.visionVector = visionVector;
		}
		
		public long getScore()
		{
			return score;
		}
		
		public void setScore(long score)
		{
			this.score = score;
		}
		
		@Override
		public String toString()
		{
			StringBuilder stringBuilder = new StringBuilder().append(direction.getLabel()).append(",");
			visionVector.stream().forEach(vv -> stringBuilder.append(vv + ","));
			int lastIndexOfSeperator = stringBuilder.lastIndexOf(",");
			stringBuilder.replace(lastIndexOfSeperator, lastIndexOfSeperator + 1, "\n");
			return stringBuilder.toString();
		}
	}
}