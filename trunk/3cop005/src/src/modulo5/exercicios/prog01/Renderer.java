package src.modulo5.exercicios.prog01;

import java.awt.event.*;
import com.sun.opengl.util.GLUT;
import javax.media.opengl.DebugGL;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;
import src.modulo5.complementos.*;


public class Renderer extends KeyAdapter implements GLEventListener{
    // Atributos
	private GL gl;
	private GLU glu;
    private GLUT glut;
	private GLAutoDrawable glDrawable;
	private float translacaoX, translacaoY;

    private Olho olho;
    private Visada visada;
    private U u;


    /**
	 * Método definido na interface GLEventListener e chamado pelo objeto no qual será feito o desenho
	 * logo após a inicialização do contexto OpenGL.
	 */
    public void init(GLAutoDrawable drawable) {
        gl = drawable.getGL();
        glu = new GLU();
        glut = new GLUT();

        olho = new Olho(2.0, 2.0, 2.0);
        visada = new Visada(0.0, 1.0, 0.0);
        u = new U(0.0, 1.0, 0.0);

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

    /**
	 * Método definido na interface GLEventListener e chamado pelo objeto no qual será feito o desenho
	 * para começar a fazer o desenho OpenGL pelo cliente.
	 */
    public void display(GLAutoDrawable drawable) {
        displayWire();
    }

    /**
	 * Método definido na interface GLEventListener e chamado pelo objeto no qual será feito o desenho
	 * quando o modo de exibição ou o dispositivo de exibição associado foi alterado.
	 */
    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {

    }

    /**
	 * Método definido na interface GLEventListener e chamado pelo objeto no qual será feito o desenho
	 * depois que a janela foi redimensionada.
	 */
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {

    }


    /**
	 * Método definido na interface KeyListener que está sendo implementado que seja
	 * feita a saída do sistema quando for pressionada a tecla ESC.
	 */
    @Override
	public void keyPressed(KeyEvent e)
	{
		switch (e.getKeyCode())
		{
            case KeyEvent.VK_V + KeyEvent.SHIFT_DOWN_MASK:
                System.out.println("SHIFT + V");
                break;

            case KeyEvent.VK_V :
                System.out.println("v");
                break;

            case KeyEvent.VK_X:
                System.out.println("x - translada x +0.2");
                glu.gluLookAt(0.2, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
                break;

			case KeyEvent.VK_RIGHT:
                translacaoX+=0.1;
                break;

            case KeyEvent.VK_LEFT:
                translacaoX -= 0.1;
                break;

            case KeyEvent.VK_UP:
                translacaoY += 0.1;
                break;

            case KeyEvent.VK_DOWN:
                translacaoY -= 0.1;
                break;

            case KeyEvent.VK_ESCAPE:
                System.exit(0);
                break;
		}
		
	}

        //<<<<<<<<<<<<<<<<<<< axis >>>>>>>>>>>>>>
    public void axis(double length){ // draw a z-axis, with cone at end
        gl.glPushMatrix();
        gl.glBegin(GL.GL_LINES);
           gl.glVertex3d(0, 0, 0); gl.glVertex3d(0,0,length); // along the z-axis
        gl.glEnd();
        gl.glTranslated(0, 0 ,length -0.2);
        glut.glutWireCone(0.04, 0.2, 12, 9);
        gl.glPopMatrix();
    }

    public void displayWire() {
        gl.glMatrixMode(GL.GL_PROJECTION); // set the view volume shape
        gl.glLoadIdentity();
        gl.glOrtho(-2.0 * 64 / 48.0, 2.0 * 64 / 48.0, -2.0, 2.0, 0.1, 100);
        gl.glMatrixMode(GL.GL_MODELVIEW); // position and aim the camera
        gl.glLoadIdentity();
        glu.gluLookAt(olho.getX(), olho.getY(), olho.getZ(), visada.getX(), visada.getY(), visada.getZ(), u.getX(), u.getY(), u.getZ());

        gl.glClear(GL.GL_COLOR_BUFFER_BIT); // clear the screen
        gl.glColor3d(0, 0, 0); // draw black lines
        axis(0.5);					 // z-axis
        gl.glPushMatrix();
        gl.glRotated(90, 0, 1.0, 0);
        axis(0.5);					// y-axis
        gl.glRotated(-90.0, 1, 0, 0);
        axis(0.5);					// z-axis
        gl.glPopMatrix();

        gl.glPushMatrix();
        gl.glScaled(2.0, 2.0, 2.0);
        gl.glTranslated(1, 1, 1);

        glut.glutWireTeapot(0.25); // teapot at (1,1,1)
        gl.glPopMatrix();

        gl.glFlush();
    }

}
