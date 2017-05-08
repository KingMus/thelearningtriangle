package de.thelearningtriangle.core.overworld;

import com.jogamp.nativewindow.util.Point;

import de.thelearningtriangle.core.triangle.LearningTriangle;

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