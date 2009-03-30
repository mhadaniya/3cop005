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
 * Exercicio01.java <BR>
 * author: Brian Paul (converted to Java by Ron Cemer and Sven Goethel) <P>
 *
 * This version is equal to Brian Paul's version 1.2 1999/10/21
 */
public class Exercicio01 implements GLEventListener {

    public static void main(String[] args) {
        Frame frame = new Frame("Exercicio 01 - Quadrados");
        GLCanvas canvas = new GLCanvas();

        canvas.addGLEventListener(new Exercicio01());
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

        desenhaLinhas(drawable, 4, 4, 180, 280);
        desenhaLinhas(drawable, 1, 2, 200, 150);
        desenhaLinhas(drawable, 2, 2, 100, 50);
        desenhaLinhas(drawable, 1, 6, 80, 150);
        desenhaLinhas(drawable, 2, 3, 280, 80);
        desenhaLinhas(drawable, 4, 1, 400, 450);

        // Flush all drawing operations to the graphics card
        gl.glFlush();
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }

    /**
     * @param esx - escala em x (altera a largura)
     *        esy - escala em y (altera a altura)
     *        deltax - deslocamento do x do quadrilatero
     *        deltay - deslocamento do y do quadrilatero
     */
    public void desenhaLinhas(GLAutoDrawable drawable, int esx, int esy, int deltax, int deltay){
        GL gl = drawable.getGL();

        gl.glBegin(GL.GL_QUADS);
//        gl.glBegin(GL.GL_LINE_LOOP);

            esx *= 25;
            esy *= 25;

            gl.glVertex2i(deltax, deltay);
            gl.glVertex2i(deltax + esx, deltay);
            gl.glVertex2i(deltax + esx, deltay + esy);
            gl.glVertex2i(deltax, esy + deltay);
        gl.glEnd();
    }

}

