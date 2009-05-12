package src.modulo3.exemplos;

import com.sun.opengl.util.Animator;
import java.awt.*;
import java.awt.event.*;
import javax.media.opengl.*;
import javax.media.opengl.glu.GLU;
import src.modulo3.exemplos.canvas.Canvas;

/**
 *
 * @author uel
 */

//TODO - arrumar o código de CANVAS.
public class prog2 implements GLEventListener{
    private GL gl;
    private GLU glu;
    private Canvas canvas;

     public static void main(String[] args) {
        Frame frame = new Frame("Modulo 03 - Programa 02");
        Canvas canvas = new Canvas();

        canvas.addGLEventListener(new prog2());
        frame.add(canvas);
        frame.setSize(640, 480);
        final Animator animator = new Animator(canvas);
        frame.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                
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

    public void centerWindow(Component frame) {
        Dimension screenSize =
           Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize  = frame.getSize();

        if (frameSize.width  > screenSize.width )
           frameSize.width  = screenSize.width;
        if (frameSize.height > screenSize.height)
           frameSize.height = screenSize.height;

        frame.setLocation (
            (screenSize.width  - frameSize.width ) >> 1,
            (screenSize.height - frameSize.height) >> 1
        );
    }

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
        canvas.setWindow(-5.0f, 5.0f, -0.3f, 1.0f); // coordenada mundo obs:pode-se inverter os eixos para que a figura fique de cabeça para baixo
        canvas.setViewport(0, 640, 0, 480);     // Sub Janela de visualização
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);

        canvas.setViewport(0, 640, 0, 480);
        gl.glViewport(0, 640, 0, 480);
        plotsin();

        canvas.setViewport(340, 640, 260, 480);
        gl.glViewport(340, 640, 260, 480);
        plotsin();
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {

    }

    public void init(GLAutoDrawable drawable) {
        canvas = new Canvas(480, 640, "Programa Teste", drawable);
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