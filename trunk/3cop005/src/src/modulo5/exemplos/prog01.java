package src.modulo5.exemplos;

import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import com.sun.opengl.util.Animator;
import com.sun.opengl.util.GLUT;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;

public class prog01 implements GLEventListener{
    private GL gl;
    private GLU glu;
    private GLUT glut;

    public static void main(String[] args) {
        Frame frame = new Frame("Modulo 05 - Programa 01");
        GLCanvas canvas = new GLCanvas();

        canvas.addGLEventListener(new prog01());
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

    //<<<<<<<<<<<<<<<<<<< axis >>>>>>>>>>>>>>
    public void axis(double length){ // draw a z-axis, with cone at end
        gl.glPushMatrix();
        gl.glBegin(GL.GL_LINES);
           gl.glVertex3d(0, 0, 0); gl.glVertex3d(0,0,length); // along the z-axis
        gl.glEnd();
        gl.glTranslated(0, 0,length -0.2);
        glut.glutWireCone(0.04, 0.2, 12, 9);
        gl.glPopMatrix();
    }

    public void displayWire(){
	gl.glMatrixMode(GL.GL_PROJECTION); // set the view volume shape
	gl.glLoadIdentity();
	gl.glOrtho(-2.0*64/48.0, 2.0*64/48.0, -2.0, 2.0, 0.1, 100);
	gl.glMatrixMode(GL.GL_MODELVIEW); // position and aim the camera
	gl.glLoadIdentity();
	glu.gluLookAt(2.0, 2.0, 2.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0);

	gl.glClear(GL.GL_COLOR_BUFFER_BIT); // clear the screen
        gl.glColor3d(0,0,0); // draw black lines
        axis(0.5);					 // z-axis
	gl.glPushMatrix();
	gl.glRotated(90, 0,1.0, 0);
	axis(0.5);					// y-axis
	gl.glRotated(-90.0, 1, 0, 0);
	axis(0.5);					// z-axis
	gl.glPopMatrix();

	gl.glPushMatrix();
    gl.glScaled(2.0, 2.0, 2.0);
	gl.glTranslated(1,1,1);

	glut.glutWireTeapot(0.2); // teapot at (1,1,1)
	gl.glPopMatrix();

	gl.glFlush();
}

    public void init(GLAutoDrawable drawable) {
        gl = drawable.getGL();
        glu = new GLU();
        glut = new GLUT();
        
        System.err.println("INIT GL IS: " + gl.getClass().getName());

        // Enable VSync
        gl.setSwapInterval(1);

        // Setup the drawing area and shading mode
        gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f); //Cor de fundo branco
        gl.glViewport(0, 0, 640, 480);
        gl.glColor3f(0.0f, 0.0f, 0.0f); //Cor do desenho
        gl.glPointSize(1.0f); //um ponto eh 4 x 4 pixels
        gl.glLineWidth(1.0f);
        gl.glShadeModel(GL.GL_FLAT); // try setting this to GL_FLAT and see what happens.
    }

    public void display(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();

        displayWire();
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
        //glu.gluPerspective(45.0f, h, 1.0, 20.0);
        glu.gluOrtho2D(0.0f, 640.0f, 0.0f, 480.0f);
        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

}
