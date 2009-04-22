package src.modulo3.exemplos;

import com.sun.opengl.util.Animator;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.media.opengl.*;
import javax.media.opengl.glu.GLU;
import src.modulo3.exemplos.canvas.Canvas;

/**
 *
 * @author uel
 */

//TODO - arrumar o código de CANVAS.
public class prog2 extends JFrame {
    private GL gl;
    private GLU glu;

    public prog2() {
        //set the JFrame title
        super("Modulo 03 - Programa 02");

        //kill the process when the JFrame is closed
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //only three JOGL lines of code ... and here they are
        GLCapabilities glcaps = new GLCapabilities();

        GLCanvas glcanvas = GLDrawableFactory.getFactory().
        GLDrawableFactory.getFactory().createGLCanvas(glcaps);

        glcanvas.addGLEventListener(
           new Canvas()
        );

        //add the GLCanvas just like we would any Component
        getContentPane().add(glcanvas, BorderLayout.CENTER);
        setSize(500, 300);

        //center the JFrame on the screen
        centerWindow(this);
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

    public static void main(String[] args) {
        final prog2 app = new prog2();

        // show what we've done
        SwingUtilities.invokeLater (
            new Runnable() {
                public void run() {
                    app.setVisible(true);
                }
            }
        );
    }


//    class Renderer implements GLEventListener {
//
//        public void init(GLAutoDrawable drawable) {
//            frame = new Frame("Modulo 03 - Programa 02");
//            canvas = new Canvas();
//
//            canvas.addGLEventListener(new Prog2());
////        canvas.setSize(width, height);
//            frame.add(canvas);
//
//            gl = drawable.getGL();
//            glu = new GLU();
//
////        setBackgroundColor(1.0, 1.0, 1.0);
////        canvas.setColor(0.0, 0.0, 0.0);
//            gl.glPointSize(4.0f);                 // a 'dot' is 4 by 4 pixels
//            gl.glLineWidth(2.0f);
//        }
//
//        public void display(GLAutoDrawable drawable) {
////        setWindow(-5.0, 5.0, -0.3, 1.0); // coordenada mundo obs:pode-se inverter os eixos para que a figura fique de cabeça para baixo
////        setViewport(0, 640, 0, 480);     // Sub Janela de visualização
//            gl.glClear(GL.GL_COLOR_BUFFER_BIT);
//
////        canvas.setViewport(0, 640, 0, 480);
//            plotsin();
//
////        canvas.setViewport(340, 640, 260, 480);
//            plotsin();
//        }
//
//    }

}