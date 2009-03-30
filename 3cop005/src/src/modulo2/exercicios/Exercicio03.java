package src.modulo2.exercicios;

import com.sun.opengl.util.Animator;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;


/**
 * Exercicio03.java <BR>
 * author: Brian Paul (converted to Java by Ron Cemer and Sven Goethel) <P>
 *
 * This version is equal to Brian Paul's version 1.2 1999/10/21
 */
public class Exercicio03 implements GLEventListener {

    public static void main(String[] args) {
        Frame frame = new Frame("Exercicio 03 - Grafico");
        GLCanvas canvas = new GLCanvas();

        canvas.addGLEventListener(new Exercicio03());
        frame.add(canvas);
        frame.setSize(640, 480);
        final Animator animator = new Animator(canvas);
        frame.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                // Run this on another thread than the AWT event queue to
                // make sure the call to Animator.stop() completes before
                // exiting
                new Thread(new Runnable() {

                    public void run() {
                        animator.stop();
                        System.exit(0);
                    }
                }).start();
            }
        });
        // Center frame
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        animator.start();
    }

    public void init(GLAutoDrawable drawable) {
        // Use debug pipeline
        // drawable.setGL(new DebugGL(drawable.getGL()));

        GL gl = drawable.getGL();
        System.err.println("INIT GL IS: " + gl.getClass().getName());

        // Enable VSync
        gl.setSwapInterval(1);

        // Setup the drawing area and shading mode
        gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f); //Cor de fundo branco
        gl.glColor3f(0.0f, 0.0f, 0.0f); //Cor do desenho
        gl.glPointSize(4.0f); //um ponto eh 4 x 4 pixels
        gl.glLineWidth(2.0f);
        gl.glShadeModel(GL.GL_FLAT);
    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        GL gl = drawable.getGL();
        GLU glu = new GLU();

        if (height <= 0) { // avoid a divide by zero error!
        
            height = 1;
        }
        final float h = (float) width / (float) height;
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluOrtho2D(0.0f, 640.0f, 0.0f, 480.0f);
        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    public void display(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();

        // Clear the drawing area
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);
        // Reset the current matrix to the "identity"
        gl.glLoadIdentity();
        gl.glBegin(GL.GL_LINE_STRIP); //GL_POINTS ,GL_LINE_STRIP
        for(double x = 0; x < 640 ; x++)
        {
         //GL.GL_DOUBLE   func = 0.25*(x-319)*(x-319);
            System.out.println("x - " + x + " - func(x) = " + function(x));
            gl.glVertex2d(x, function(x) + 200);
        }
        gl.glEnd();

        gl.glBegin(GL.GL_LINES); //GL_POINTS ,GL_LINE_STRIP
                   gl.glVertex2d(0,200);
                   gl.glVertex2d(640,200);
        gl.glEnd();

        // Flush all drawing operations to the graphics card
        gl.glFlush();
    }

    public double function(double x){
        System.out.println("(150 * " + Math.sin(2 * 3.14 * x * 15 / 640));
        //return (int) (0.25*(x-319)*(x-319));
        return (double) (150 * Math.sin(2 * 3.14 * x * 15 / 640));
    }
    
    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }
}

