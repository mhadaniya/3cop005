package src.modulo2.exemplos;

import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
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
import src.modulo2.exemplos.GLintPoint;
/**
 *
 * @author uel
 */
public class prog2 implements GLEventListener{
    int counter = 0;
    int screenWidth = 640;	   // width of screen window in pixels
    int screenHeight = 480;	   // height of screen window in pixels
    private GL gl;
    private GLU glu;

    public static void main(String[] args) {
        Frame frame = new Frame("Segundo Programa - Sierpinsk");
        GLCanvas canvas = new GLCanvas();

        canvas.addGLEventListener(new SimpleJOGL());
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

        gl.setSwapInterval(1);

        // Setup the drawing area and shading mode
        gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f); //Cor de fundo branco
        gl.glColor3f(0.0f, 0.0f, 0.0f); //Cor do desenho
        gl.glPointSize(2.0f); //um ponto eh 4 x 4 pixels
        gl.glDrawBuffer(GL.GL_BACK);
        gl.glLoadIdentity();
        //gl.glShadeModel(GL.GL_FLAT); // try setting this to GL_FLAT and see what happens.
        glu.gluOrtho2D(0.0f, (float)screenWidth, 0.0f, (float)screenHeight);

    }

    public void display(GLAutoDrawable drawable) {
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);     // clear the screen
        gl.glBegin(GL.GL_POINTS);
            Sierpinski();
        gl.glEnd();
        gl.glFlush();
    }


    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {

    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {

    }

    int random(int m)
    {
     return (int) Math.random()%m;
    }

    void drawDot(int x, int y)
    {
        gl.glVertex2d(x, y);
    }

    @SuppressWarnings("empty-statement")
    void Sierpinski()
    {
        GLintPoint[] T = new GLintPoint[]{
            new GLintPoint(20, 20),
            new GLintPoint(300, 30),
            new GLintPoint(200, 300)};
        //GLintPoint[] T = new GLintPoint[];
        //GLintPoint T[3] = new GLintPoint{};
//        GLintPoint T[3]= {{20,20},{300,30},{200, 300}};

        int index = random(3);         // 0, 1, or 2 equally likely
        GLintPoint point = T[index]; 	 // initial point
        drawDot(point.getX(), point.getY());     // draw initial point
        for (int i = 0; i < 4000; i++) // draw 4000 dots
        {
            index = random(3);
            point.setX((point.getX() + T[index].getX()) / 2);
            point.setY((point.getY() + T[index].getY()) / 2);
            drawDot(point.getX(), point.getY());
            gl.glFlush();
	}

}

}
