package de.thelearningtriangle.core.model.triangle;

import com.jogamp.nativewindow.util.Point;

public class TrianglePosition {
	private final Point point;
	private final LearningTriangle learningTriangle;

	public TrianglePosition(Point point, LearningTriangle learningTriangle) {
		this.point = point;
		this.learningTriangle = learningTriangle;
	}

	public Point getPoint() {
		return point;
	}

	public LearningTriangle getLearningTriangle() {
		return learningTriangle;
	}

}