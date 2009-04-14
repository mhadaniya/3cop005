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
import src.modulo3.exemplos.canvas.Canvas;

/**
 *
 * @author uel
 */

//TODO - arrumar o código de CANVAS.
public class Prog2 extends Frame {

    private Frame frame;
    private Canvas canvas;
    private GL gl;
    private GLU glu;

    public Prog2() {
        super("Modulo 03 - Programa 02");

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

    class Renderer implements GLEventListener {

        public void init(GLAutoDrawable drawable) {
            frame = new Frame("Modulo 03 - Programa 02");
            canvas = new Canvas();

            canvas.addGLEventListener(new Prog2());
//        canvas.setSize(width, height);
            frame.add(canvas);

            gl = drawable.getGL();
            glu = new GLU();

//        setBackgroundColor(1.0, 1.0, 1.0);
//        canvas.setColor(0.0, 0.0, 0.0);
            gl.glPointSize(4.0f);                 // a 'dot' is 4 by 4 pixels
            gl.glLineWidth(2.0f);
        }

        public void display(GLAutoDrawable drawable) {
//        setWindow(-5.0, 5.0, -0.3, 1.0); // coordenada mundo obs:pode-se inverter os eixos para que a figura fique de cabeça para baixo
//        setViewport(0, 640, 0, 480);     // Sub Janela de visualização
            gl.glClear(GL.GL_COLOR_BUFFER_BIT);

//        canvas.setViewport(0, 640, 0, 480);
            plotsin();

//        canvas.setViewport(340, 640, 260, 480);
            plotsin();
        }

        public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
//        throw new UnsupportedOperationException("Not supported yet.");
        }

        public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
//        throw new UnsupportedOperationException("Not supported yet.");
        }
    }

    public static void main(String[] args) {
        final Prog2 scene = new Prog2();

        scene.addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent e) {
                scene.getAnimator().stop();
                System.exit(0);
            }
        });

        scene.setVisible(true);
        scene.getAnimator().start();
        scene.getGLCanvas().requestFocus();

    }
}
    