package de.thelearningtriangle.opengl.core;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GL4;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.Animator;

import de.thelearningtriangle.opengl.figure.DrawableFigure;

public class Game extends JFrame implements GLEventListener
{
	private static final int WINDOW_WIDTH = 700;
	private static final int WINDOW_HEIGHT = 700;
	/**
	 * 
	 */
	private static final long serialVersionUID = -1313675494223113008L;
	private GLCanvas canvas;
	private List<DrawableFigure> drawableFigures;
	
	public Game()
	{
		super("The Learning Triangle");
		GLProfile profile = GLProfile.get(GLProfile.GL2);
		GLCapabilities capabilities = new GLCapabilities(profile);
		
		canvas = new GLCanvas(capabilities);
		canvas.addGLEventListener(this);
		canvas.setFocusable(true);
		
		this.getContentPane().add(canvas);
		
		this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);
		canvas.requestFocus();
		
		drawableFigures = new ArrayList<DrawableFigure>();
	}
	
	@Override
	public void display(GLAutoDrawable drawable)
	{
		GL2 gl = drawable.getGL().getGL2();
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
		
		gl.glColor3f(1, 0, 0);
		for (DrawableFigure drawableFigure : drawableFigures)
		{
			drawableFigure.drawFigureWith(drawable);
		}
	}
	
	@Override
	public void dispose(GLAutoDrawable drawable)
	{
	}
	
	@Override
	public void init(GLAutoDrawable drawable)
	{
		GL4 gl = drawable.getGL().getGL4();
		gl.glClearColor(0.392f, 0.584f, 0.929f, 1.0f);
		gl.setSwapInterval(0);
		
		Animator animator = new Animator(drawable);
		animator.start();
		animator.setRunAsFastAsPossible(true);
		canvas.setAnimator(animator);
	}
	
	public void registerDrawableFigure(DrawableFigure drawableFigure)
	{
		this.drawableFigures.add(drawableFigure);
	}
	
	public void unregisterDrawableFigure(DrawableFigure drawableFigure)
	{
		this.drawableFigures.remove(drawableFigure);
	}
	
	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height)
	{
	}
}