package src.modulo6.exemplos.prog2;

import java.awt.event.*;
import com.sun.opengl.util.GLUT;
import java.nio.FloatBuffer;
import javax.media.opengl.DebugGL;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;
import src.modulo5.complementos.*;
import src.modulo6.exemplos.complementos.Camera;


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

    float ex=2.3f, ey=1.3f, ez=2,vx=0, vy=0.1f,vz=0.1f;
    double winHt = 1.0;//0.2;//1.0;


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
        gl.glClearColor(1.0f, 1.0f, 1.0f, 0); //Cor de fundo branco
        gl.glViewport(0, 0, 640, 480);
        gl.glColor3f(0.0f, 0.0f, 0.0f); //Cor do desenho
        gl.glPointSize(1.0f); //um ponto eh 4 x 4 pixels
        gl.glLineWidth(1.0f);
        gl.glShadeModel(GL.GL_FLAT); // try setting this to GL_FLAT and see what happens.
        gl.glEnable(GL.GL_LIGHTING);
        gl.glEnable(GL.GL_LIGHT0);
        gl.glShadeModel(GL.GL_SMOOTH);
        gl.glEnable(GL.GL_DEPTH_TEST);
        gl.glEnable(GL.GL_NORMALIZE);
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

        displaySolid();
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
            case KeyEvent.VK_ESCAPE:
                System.exit(0);
                break;

		}
        glDrawable.display();
		
	}

    public void wall(double thickness) {
        gl.glPushMatrix();
        gl.glTranslated(0.5, 0.5 * thickness, 0.5);
        gl.glScaled(1.0, thickness, 1.0);
        glut.glutSolidCube(1.0f);
        gl.glPopMatrix();
    }

    public void tableLeg(double thick, double len) {
        gl.glPushMatrix();
        gl.glTranslated(0, len / 2, 0);
        gl.glScaled(thick, len, thick);
        glut.glutSolidCube(1.0f);
        gl.glPopMatrix();
    }

    void jackPart() {
        gl.glPushMatrix();
        gl.glScaled(0.2, 0.2, 1.0);
        glut.glutSolidSphere(1, 15, 15);
        gl.glPopMatrix();
        gl.glPushMatrix();
        gl.glTranslated(0, 0, 1.2);
        glut.glutSolidSphere(0.2, 15, 15);
        gl.glTranslated(0, 0, -2.4);
        glut.glutSolidSphere(0.2, 15, 15);
        gl.glPopMatrix();
    }


    void jack() {
        gl.glPushMatrix();
        jackPart();
        gl.glRotated(90.0, 0, 1, 0);
        jackPart();
        gl.glRotated(90.0, 1, 0, 0);
        jackPart();
        gl.glPopMatrix();
    }
    
    void table(double topWid, double topThick, double legThick, double legLen) {
        gl.glPushMatrix();
        gl.glTranslated(0, legLen, 0);
        gl.glScaled(topWid, topThick, topWid);
        glut.glutSolidCube(1.0f);
        gl.glPopMatrix();
        double dist = 0.95 * topWid / 2.0 - legThick / 2.0;
        gl.glPushMatrix();
        gl.glTranslated(dist, 0, dist);
        tableLeg(legThick, legLen);
        gl.glTranslated(0, 0, -2 * dist);
        tableLeg(legThick, legLen);
        gl.glTranslated(-2 * dist, 0, 2 * dist);
        tableLeg(legThick, legLen);
        gl.glTranslated(0, 0, -2 * dist);
        tableLeg(legThick, legLen);
        gl.glPopMatrix();
    }

    void displaySolid() {
// set properties of the surface material
        float[] mat_ambient = {0.0f, 0.0f, 0.7f, 1.0f};
        float[] mat_diffuse = {0.6f, 0.6f, 0.6f, 1.0f};
        float[] mat_specular = {1.0f, 1.0f, 1.0f, 1.0f};
        float[] mat_shininess = {50.0f};
        gl.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT, mat_ambient, 0);
        gl.glMaterialfv(GL.GL_FRONT, GL.GL_DIFFUSE, mat_diffuse, 0);
        gl.glMaterialfv(GL.GL_FRONT, GL.GL_SPECULAR, mat_specular, 0);
        gl.glMaterialfv(GL.GL_FRONT, GL.GL_SHININESS, mat_shininess, 0);
// set the light source properties
        float[] lightIntensity = {0.7f, 0.7f, 0.7f, 1.0f};
        float[] light_position = {2.0f, 6.0f, 3.0f, 0.0f};
        gl.glLightfv(GL.GL_LIGHT0, GL.GL_POSITION, light_position, 0);
        gl.glLightfv(GL.GL_LIGHT0, GL.GL_DIFFUSE, lightIntensity, 0);
// set camera
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
//  double winHt = 1.0;//0.2;//1.0;
        gl.glOrtho(-winHt * 64 / 48.0, winHt * 64 / 48.0, -winHt, winHt, 0.1, 100.0);
        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();
        glu.gluLookAt(ex, ey, ez, vx, vy, vz, 0.0, 1.0, 0.0);
        
// start drawing
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        gl.glPushMatrix();
        gl.glTranslated(0.4, 0.4, 0.6);
        gl.glRotated(45.0, 0, 0, 1);
        gl.glScaled(0.08, 0.08, 0.08);

        jack();
        gl.glPopMatrix();
        gl.glPushMatrix();
        gl.glTranslated(0.6, 0.38, 0.5);
        gl.glRotated(30, 0, 1, 0);
        glut.glutSolidTeapot(0.08);
        gl.glPopMatrix();
        gl.glPushMatrix();
//  glTranslated(0.25,0.42,0.35);
        gl.glTranslated(1.25, 0.42, 0.35);
        glut.glutSolidSphere(0.1, 15, 15);
        gl.glPopMatrix();
        gl.glPushMatrix();
        gl.glTranslated(0.4, 0, 0.4);
        table(0.6, 0.02, 0.02, 0.3);
        gl.glPopMatrix();
        wall(0.02);
        gl.glPushMatrix();
        gl.glRotated(90.0, 0.0, 0.0, 1.0);
        wall(0.02);
        gl.glPopMatrix();
        gl.glPushMatrix();
        gl.glRotated(-90.0, 1.0, 0.0, 0.0);
        wall(0.02);
        gl.glPopMatrix();
        gl.glFlush();
    }

}
