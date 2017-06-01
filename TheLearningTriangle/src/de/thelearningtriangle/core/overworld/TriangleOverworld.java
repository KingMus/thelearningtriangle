package de.thelearningtriangle.core.overworld;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import com.jogamp.nativewindow.util.Point;

import de.thelearningtriangle.core.overworld.field.AbstractField;
import de.thelearningtriangle.core.overworld.field.DeathField;
import de.thelearningtriangle.core.overworld.field.FieldType;
import de.thelearningtriangle.core.overworld.field.WallField;
import de.thelearningtriangle.core.triangle.LearningTriangle;

public class TriangleOverworld {

	private Optional<AbstractField[][]> map;

	private List<TrianglePosition> trianglePositions = new ArrayList<TrianglePosition>();

	private Random random;

	public TriangleOverworld(Random random) {
		this.random = random;
	}

	public void setMap(AbstractField[][] field) {
		this.map = Optional.of(field);
	}

	private AbstractField[][] getMap() throws NoMapException {
		return map.orElseThrow(NoMapException::new);
	}

	public int getSize() throws NoMapException {
		return getMap().length;
	}

	public AbstractField getField(Point point) throws NoMapException {
		return getField(point.getX(), point.getY());
	}

	public AbstractField getField(int x, int y) throws NoMapException {
		return getMap()[x][y];
	}

	public void setTriangle(int x, int y) throws NoMapException, FieldAccessException {
		setTriangle(new Point(x, y));
	}

	public void setTriangle(Point point) throws NoMapException, FieldAccessException {
		LearningTriangle learningTriangle = new LearningTriangle();
		getMap()[point.getX()][point.getY()].access(learningTriangle);
		trianglePositions.add(new TrianglePosition(point, learningTriangle));
	}

	public Point getRandomSpawningPoint() throws NoMapException {
		List<Point> possibleSpawningPoints = new ArrayList<Point>();
		AbstractField[][] currentMap = getMap();
		for (int x = 0; x < currentMap.length; x++) {
			AbstractField[] abstractFields = currentMap[x];
			for (int y = 0; y < abstractFields.length; y++) {
				AbstractField abstractField = abstractFields[y];
				if (!((WallField.class.isInstance(abstractField)) | DeathField.class.isInstance(abstractField))) {
					possibleSpawningPoints.add(new Point(x, y));
				}
			}
		}
		return possibleSpawningPoints.get(random.nextInt(possibleSpawningPoints.size()));
	}

	public Point calculateNewPoint(Point currentPoint, Direction direction) {
		return new Point(
				currentPoint.getX() + direction.getChangeInX(),
				currentPoint.getY() + direction.getChangeInY());
	}

	public void moveTriangle(TrianglePosition trianglePosition, Direction direction) throws NoMapException {
		Point newPoint = calculateNewPoint(trianglePosition.getPoint(), direction);
		try {
			LearningTriangle learningTriangle = trianglePosition.getLearningTriangle();
			getMap()[newPoint.getX()][newPoint.getY()].access(learningTriangle);

			trianglePositions.remove(trianglePosition);
			trianglePositions.add(new TrianglePosition(newPoint, learningTriangle));

		} catch (FieldAccessException e) {
			System.out.println(MessageFormat.format(
					"Triangle cant move to pos [{0},{1}] (in direction: {2})",
					newPoint.getX(),
					newPoint.getY(),
					direction.name()));
			moveTriangle(trianglePosition, Direction.values()[random.nextInt(4)]);
		}
	}

	public List<Integer> getVisionVectorFor(Point point) {
		List<Integer> fieldVector = new ArrayList<Integer>();

		int visionField = 3;
		for (int visionX = -visionField; visionX < visionField; visionX++) {
			for (int visionY = -visionField; visionY < visionField; visionY++) {
				AbstractField field = null;

				try {
					field = getField(point.getX() + visionX, point.getY() + visionY);
				} catch (Exception e) {
					field = FieldType.WALL.newInstance();
				} finally {
					fieldVector.add(FieldType.getIdFor(field));
				}
			}
		}
		return fieldVector;
	}

	public List<TrianglePosition> getTrianglePositions() {
		return trianglePositions;
	}
}