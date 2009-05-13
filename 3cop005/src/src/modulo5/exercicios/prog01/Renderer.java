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
    private double visualizacao;

    /**
	 * Método definido na interface GLEventListener e chamado pelo objeto no qual será feito o desenho
	 * logo após a inicialização do contexto OpenGL.
	 */
    public void init(GLAutoDrawable drawable) {
        glDrawable = drawable;
        gl = drawable.getGL();
        glu = new GLU();
        glut = new GLUT();
        visualizacao = 1.0;

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
        gl.glMatrixMode(GL.GL_PROJECTION); // set the view volume shape
        gl.glLoadIdentity();
        gl.glOrtho((-2.0 * 64 / 48.0) * visualizacao, (2.0 * 64 / 48.0)* visualizacao, (-2.0) * visualizacao, (2.0) * visualizacao, 0.1, 100);
        gl.glMatrixMode(GL.GL_MODELVIEW); // position and aim the camera
        gl.glLoadIdentity();

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
                if(e.isShiftDown()){
                    System.out.println("V - aumenta a area de visualização");
                    visualizacao = visualizacao - 0.1;
                }else{
                    System.out.println("v - aumenta a area de visualização");
                    visualizacao = visualizacao + 0.1;
                }
                break;

            case KeyEvent.VK_X:
                if(e.isShiftDown()){
                    System.out.println("x - translada a posição do olho no eixo x -0.2");
                    olho.setX(olho.getX() - 0.2);
                }else{
                    System.out.println("X - translada a posição do olho no eixo x +0.2");
                    olho.setX(olho.getX() + 0.2);
                }
                break;

            case KeyEvent.VK_Y:
                if(e.isShiftDown()){
                    System.out.println("y - translada a posição do olho no eixo y -0.2");
                olho.setY(olho.getY() - 0.2);
                }else{
                    System.out.println("Y - translada a posição do olho no eixo y +0.2");
                    olho.setY(olho.getY() + 0.2);
                }
                break;

            case KeyEvent.VK_Z:
                if(e.isShiftDown()){
                    System.out.println("z - translada a posição do olho no eixo z -0.2");
                    olho.setZ(olho.getZ() - 0.2);
                }else{
                    System.out.println("Z - translada a posição do olho no eixo z +0.2");
                    olho.setZ(olho.getZ() + 0.2);
                }
                break;

            case KeyEvent.VK_A:
                if(e.isShiftDown()){
                    System.out.println("a - translada a posição da visada no eixo x -0.2");
                    visada.setX(visada.getX() - 0.2);
                }else{
                    System.out.println("A - translada a posição da visada no eixo x +0.2");
                    visada.setX(visada.getX() + 0.2);
                }
                break;

            case KeyEvent.VK_W:
                if(e.isShiftDown()){
                    System.out.println("w - translada a posição da visada no eixo y -0.2");
                    visada.setY(visada.getY() - 0.2);
                }else{
                    System.out.println("W - translada a posição da visada no eixo y +0.2");
                    visada.setY(visada.getY() + 0.2);
                }
                break;

            case KeyEvent.VK_S:
                if(e.isShiftDown()){
                    System.out.println("s - translada a posição da visada no eixo z +0.2");
                    visada.setZ(visada.getZ() - 0.2);
                }else{
                    System.out.println("S - translada a posição da visada no eixo z +0.2");
                    visada.setZ(visada.getZ() + 0.2);
                }
                break;

            case KeyEvent.VK_R:
                System.out.println("r - RESET");
                olho = new Olho(2.0, 2.0, 2.0);
                visada = new Visada(0.0, 1.0, 0.0);
                u = new U(0.0, 1.0, 0.0);
                visualizacao = 1.0;
                break;

            case KeyEvent.VK_ESCAPE:
                System.exit(0);
                break;

		}
        glDrawable.display();
		
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
