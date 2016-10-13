package de.thelearningtriangle.opengl.core;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GL2ES3;
import com.jogamp.opengl.GL4;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.Animator;

public class Game extends JFrame implements GLEventListener, KeyListener
{
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;
    /**
     * 
     */
    private static final long serialVersionUID = -1313675494223113008L;
    private TestBox testBox;
    private GLCanvas canvas;
    
    public Game()
    {
        super("The Learning Triangle");
        GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(profile);
        
        canvas = new GLCanvas(capabilities);
        canvas.addGLEventListener(this);
        canvas.setFocusable(true);
        canvas.addKeyListener(this);
        
        testBox = new TestBox(0.2f, 0.2f);
        
        this.getContentPane().add(canvas);
        
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
        canvas.requestFocus();
        
    }
    
    @Override
    public void display(GLAutoDrawable drawable)
    {
        testBox.calculatePosition();
        GL2 gl = drawable.getGL().getGL2();
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        
        gl.glColor3f(1, 0, 0);
        
        gl.glBegin(GL2ES3.GL_TRIANGLES);
        for (float[] position : testBox.getVertex3fs())
        {
            float ratio = (float) WINDOW_WIDTH / (float) WINDOW_HEIGHT;
            gl.glVertex3f(position[0] / ratio, position[1], position[2]);
        }
        gl.glLoadIdentity();
        
        gl.glEnd();
        
        gl.glFlush();
    }
    
    @Override
    public void dispose(GLAutoDrawable drawable)
    {
        // TODO Auto-generated method stub
        
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
    
    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height)
    {
        // TODO Auto-generated method stub
        
    }
    
    public void play()
    {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public void keyTyped(KeyEvent e)
    {
    }
    
    @Override
    public void keyPressed(KeyEvent e)
    {
        testBox.keyEventAction(e, true);
    }
    
    @Override
    public void keyReleased(KeyEvent e)
    {
        testBox.keyEventAction(e, false);
    }
}