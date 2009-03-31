package src.modulo2.exemplos;

import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
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
public class prog6 implements GLEventListener {
    private GL gl;
    private GLU glu;

    public static void main(String[] args) {
        Frame frame = new Frame("Programa 05");
        GLCanvas canvas = new GLCanvas();

        canvas.addGLEventListener(new prog6());
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
        gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);        // set white background color
        gl.glColor3f(0.0f, 0.0f, 0.0f);          // set the drawing color
        gl.glPointSize(4.0f);   // a 'dot' is 4 by 4 pixels
        gl.glLineWidth(2.0f);
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluOrtho2D(0.0, 640.0, 0.0, 480.0);
    }

    public void display(GLAutoDrawable drawable) {
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);     // clear the screen
        drawFlurry(10, 640, 480);
    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }

    //TODO - arrumar o random!!!
    int random(int m)
    {
     return (int) (1 + Math.random()%m);
    }

    void drawFlurry(int num, int Width, int Height)
    // draw num random rectangles in a Width by Height rectangle
    {
        for (int i = 0; i < num; i++)
        {
            int x1 = random(Width);			// place corner randomly
            int y1 = random(Height);
            int x2 = random(Width); 		// pick the size so it fits
            int y2 = random(Height);
            float lev = random(10)/10.0f;		// random value, in range 0 to 1
            gl.glColor3f(lev,lev,lev);			// set the gray level
            gl.glRecti(x1, y1, x2, y2);			// draw the rectangle
        }
        gl.glFlush();

    }

}
