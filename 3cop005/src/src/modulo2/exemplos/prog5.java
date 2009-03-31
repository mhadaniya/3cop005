package src.modulo2.exemplos;

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
 *
 * @author ADANIYA
 */
public class prog5 implements GLEventListener{
    private GL gl;
    private GLU glu;
    private GLintPoint peak;

    public static void main(String[] args) {
        Frame frame = new Frame("Programa 05");
        GLCanvas canvas = new GLCanvas();

        canvas.addGLEventListener(new prog5());
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
        gl = drawable.getGL();
        glu = new GLU();

        System.err.println("INIT GL IS: " + gl.getClass().getName());
        // Enable VSync
        gl.glClearColor(1.0f,1.0f,1.0f,1.0f);        // set white background color
        gl.glColor3f(0.0f, 0.0f, 0.0f);          // set the drawing color
        gl.glPointSize(4.0f);                 // a 'dot' is 4 by 4 pixels
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluOrtho2D(0.0, 640.0, 0.0, 480.0);
    }

    public void display(GLAutoDrawable drawable) {
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);     // clear the screen
        hardwiredHouse();
        peak = new GLintPoint(100, 100);
//        peak.setX(100);  //x=100;
//        peak.setY(100); //y=100;
        parameterizedHouse(peak, 100, 100);
        peak.setX(400);
        peak.setY(100); //y=100;
        parameterizedHouse(peak, 100, 100);
        for (int i = 10;i < 150 ; i=i+10) retangulo(peak, i, i);
        gl.glFlush();                        // send all output to display
    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    void retangulo(GLintPoint peak, int width, int height) {
        gl.glBegin(GL.GL_LINE_LOOP);
            gl.glVertex2i(peak.getX(), peak.getY());
            gl.glVertex2i(peak.getX() + width, peak.getY());
            gl.glVertex2i(peak.getX() + width, peak.getY() + height);
            gl.glVertex2i(peak.getX(), peak.getY() + height);
        gl.glEnd();
    }

    void parameterizedHouse(GLintPoint peak, int width, int height) // the top of house is at the peak; the size of house is given
    //  by height and width
    {
        gl.glBegin(GL.GL_LINE_LOOP);
        //draw shell of house
            gl.glVertex2i(peak.getX(), peak.getY());
            gl.glVertex2i(peak.getX() + width / 2, peak.getY() - 3 * height / 8);
            gl.glVertex2i(peak.getX() + width / 2, peak.getY() - height);
            gl.glVertex2i(peak.getX() - width / 2, peak.getY() - height);
            gl.glVertex2i(peak.getX() - width / 2, peak.getY() - 3 * height / 8);
        gl.glEnd();
    }

    void hardwiredHouse() {
        gl.glBegin(GL.GL_LINE_LOOP);
            gl.glVertex2i(40, 40); // draw the shell of house
            gl.glVertex2i(40, 90);
            gl.glVertex2i(70, 120);
            gl.glVertex2i(100, 90);
            gl.glVertex2i(100, 40);
        gl.glEnd();
        gl.glBegin(GL.GL_LINE_STRIP);
            gl.glVertex2i(50, 100); // draw the chimney
            gl.glVertex2i(50, 120);
            gl.glVertex2i(60, 120);
            gl.glVertex2i(60, 110);
        gl.glEnd();
    }

}
