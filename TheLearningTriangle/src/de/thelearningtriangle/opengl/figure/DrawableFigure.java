package de.thelearningtriangle.opengl.figure;

import com.jogamp.opengl.GLAutoDrawable;

/**
 * 
 * @author Noixes This interface should be implemented by all figures that
 *         opengl should draw.
 * 
 */
public interface DrawableFigure
{
	void drawFigureWith(GLAutoDrawable drawable);
}