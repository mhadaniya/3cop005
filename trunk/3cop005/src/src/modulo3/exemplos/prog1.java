package src.modulo3.exemplos;

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
 * @author uel
 */
public class prog1 implements GLEventListener{
    private GL gl;
    private GLU glu;

    public static void main(String[] args) {
        Frame frame = new Frame("Modulo 03 - Programa 01");
        GLCanvas canvas = new GLCanvas();

        canvas.addGLEventListener(new prog1());
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

    /** coordenadas mundo
     *
     * @param left
     * @param right
     * @param bottom
     * @param top
     */
    void setWindow(float left, float right, float bottom, float top) {
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluOrtho2D(left, right, bottom, top);
    }
    /**
     * subjanela de visualização
     * considera a janela definida no comando glutInitWindowSize(640,480);
     * @param left
     * @param right
     * @param bottom
     * @param top
     */
    void setViewport(int left, int right, int bottom, int top) {
        gl.glViewport(left, bottom, right - left, top - bottom);
    }

    /**
     * 
     */
    void plotsin() {
        gl.glBegin(GL.GL_LINE_STRIP);
        {
            for (float x = -4.0f; x < 4.0f; x += 0.1) {
                float y = (float) (Math.sin(3.14159 * x) / (3.14159 * x));
                gl.glVertex2f(x, y);
            }
        }
        gl.glEnd();
        gl.glFlush();
    }
    
    public void display(GLAutoDrawable drawable) {
        setWindow(-5.0f, 5.0f, -0.3f, 1.0f); // coordenada mundo obs:pode-se inverter os eixos para que a figura fique de cabeça para baixo
        setViewport(0, 640, 0, 480);     // Sub Janela de visualização
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);

        setViewport(0, 640, 0, 480);
        plotsin();

        setViewport(340, 640, 260, 480);
        plotsin();
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {

    }

    public void init(GLAutoDrawable drawable) {
        gl = drawable.getGL();
        glu = new GLU();
        
        gl.glClearColor(1.0f, 1.0f, 1.0f, 0.0f);        // set white background color
        gl.glColor3f(0.0f, 0.0f, 0.0f);          // set the drawing color
        gl.glPointSize(4.0f);                 // a 'dot' is 4 by 4 pixels
        gl.glLineWidth(2.0f);
    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        
    }

}
