package de.thelearningtriangle.qlearning;

import java.util.ArrayList;
import java.util.List;

import com.jogamp.nativewindow.util.Point;

import de.thelearningtriangle.core.overworld.Direction;
import de.thelearningtriangle.core.overworld.FieldAccessException;
import de.thelearningtriangle.core.overworld.NoMapException;
import de.thelearningtriangle.core.overworld.TriangleDeathException;
import de.thelearningtriangle.core.overworld.TriangleOverworld;
import de.thelearningtriangle.core.overworld.field.AbstractField;
import de.thelearningtriangle.core.triangle.LearningTriangle;

public class RecursiveQTriangle {

	public TriangleOverworld overworld;

	public RecursiveQTriangle(TriangleOverworld overworld) {
		this.overworld = overworld;
	}

	public List<TriangleMoveData> calculateBestMoves(Point point, LearningTriangle triangle, int depth)
			throws NoMapException {
		List<TriangleMoveData> bestMoveDataList = new ArrayList<TriangleMoveData>();

		AbstractField currentField = overworld.getField(point);

		try {
			currentField.access(triangle);
			triangle.cycle();
		}
		// if this exception rises, the triangle tries to access a wall or died.
		// so punish it for that
		catch (FieldAccessException | TriangleDeathException e) {
			TriangleMoveData currentMoveData = new TriangleMoveData();
			currentMoveData.setScore(-1000);
			bestMoveDataList.add(currentMoveData);
			return bestMoveDataList;
		}

		if (depth == 0) {
			TriangleMoveData moveData = new TriangleMoveData();
			moveData.setScore(triangle.getDistance() + triangle.getEnergy());
			bestMoveDataList.add(moveData);
			return bestMoveDataList;
		}

		List<Integer> currentVisionVector = overworld.getVisionVectorFor(point);
		LearningTriangle bestTriangle = null;

		for (Direction direction : Direction.getShuffledValues()) {
			Point newPoint = overworld.calculateNewPoint(point, direction);
			LearningTriangle newTriangle = new LearningTriangle(triangle);

			List<TriangleMoveData> thisBestMoves = calculateBestMoves(newPoint, newTriangle, depth - 1);
			TriangleMoveData thisMove = thisBestMoves.get(0);
			thisMove.setDirection(direction);
			thisMove.setVisionVector(currentVisionVector);

			if (bestMoveDataList.size() == 0) {
				bestMoveDataList = thisBestMoves;
				bestTriangle = newTriangle;
			} else {
				TriangleMoveData lastBestMove = bestMoveDataList.get(0);

				if (thisMove.getScore() > lastBestMove.getScore()) {
					bestMoveDataList = thisBestMoves;
					bestTriangle = newTriangle;
				}
			}
		}

		TriangleMoveData currentMoveData = new TriangleMoveData();
		long newScore = bestMoveDataList.get(0).getScore() + bestTriangle.getDistance() + bestTriangle.getEnergy();
		currentMoveData.setScore(newScore);

		List<TriangleMoveData> resultMoveData = new ArrayList<TriangleMoveData>();
		resultMoveData.add(currentMoveData);
		resultMoveData.addAll(bestMoveDataList);

		return resultMoveData;
	}

	public static class TriangleMoveData {
		private Direction direction;
		private List<Integer> visionVector;
		private long score;

		public Direction getDirection() {
			return direction;
		}

		public void setDirection(Direction direction) {
			this.direction = direction;
		}

		public List<Integer> getVisionVector() {
			return visionVector;
		}

		public void setVisionVector(List<Integer> visionVector) {
			this.visionVector = visionVector;
		}

		public long getScore() {
			return score;
		}

		public void setScore(long score) {
			this.score = score;
		}

		@Override
		public String toString() {
			if (direction == null) {
				return "undefined\n";
			}
			StringBuilder stringBuilder = new StringBuilder().append(direction.getLabel()).append(",");
			visionVector.stream().forEach(vv -> stringBuilder.append(vv + ","));
			int lastIndexOfSeperator = stringBuilder.lastIndexOf(",");
			stringBuilder.replace(lastIndexOfSeperator, lastIndexOfSeperator + 1, "\n");
			return stringBuilder.toString();
		}
	}
}