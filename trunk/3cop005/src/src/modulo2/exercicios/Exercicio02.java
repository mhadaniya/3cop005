package src.modulo2.exercicios;

import com.sun.opengl.util.Animator;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;



/**
 * Exercicio02.java <BR>
 * author: Brian Paul (converted to Java by Ron Cemer and Sven Goethel) <P>
 *
 * This version is equal to Brian Paul's version 1.2 1999/10/21
 */
public class Exercicio02 implements GLEventListener {

    public static void main(String[] args) {
        Frame frame = new Frame("Exercicio 02 - Dinossauro");
        GLCanvas canvas = new GLCanvas();

        canvas.addGLEventListener(new Exercicio02());
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
        // Use debug pipeline
        // drawable.setGL(new DebugGL(drawable.getGL()));

        GL gl = drawable.getGL();
        System.err.println("INIT GL IS: " + gl.getClass().getName());

        // Enable VSync
        gl.setSwapInterval(1);

        // Setup the drawing area and shading mode
        gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
        gl.glColor3f(0.0f, 0.0f, 0.0f); //Cor do desenho
        gl.glPointSize(4.0f); //um ponto eh 4 x 4 pixels
        gl.glLineWidth(2.0f);
        gl.glShadeModel(GL.GL_SMOOTH); // try setting this to GL_FLAT and see what happens.
    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        GL gl = drawable.getGL();
        GLU glu = new GLU();

        if (height <= 0) { // avoid a divide by zero error!        
            height = 1;
        }
        final float h = (float) width / (float) height;
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluOrtho2D(0.0f, 640.0f, 0.0f, 480.0f);
        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    public void display(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();

        // Clear the drawing area
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);
        desenharDinossauro("dino.dat", 0.05, 0.05, 50, 150, drawable);
        desenharDinossauro("dino.dat", 0.20, 0.20, 360, 150, drawable);
        desenharDinossauro("dino.dat", 0.05, -0.05, 50, 150, drawable);
        desenharDinossauro("dino.dat", 0.25, -0.25, 480, 150, drawable);
        desenharDinossauro("dino.dat", 0.40, 0.40, 300, 300, drawable);        
        // Reset the current matrix to the "identity"
        gl.glLoadIdentity();
        // Flush all drawing operations to the graphics card
        gl.glFlush();
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }


    public void desenharDinossauro(String nomeArquivo, double esx, double esy,
                double deltax, double deltay, GLAutoDrawable drawable){
        GL gl =  drawable.getGL();

        try {
            File arquivo = new File(nomeArquivo);
			FileReader reader = new FileReader(arquivo);
			BufferedReader in = new BufferedReader(reader);
            
            String temp, s1, s2;
            int numpolys, numLines, x, y;

            temp = in.readLine();
            numpolys = Integer.parseInt(temp);
            System.out.println("NUMPOLYS = " + numpolys);
            for (int j = 0; j < numpolys; j++) {
                temp = in.readLine();
                numLines = Integer.parseInt(temp);
                System.out.println("NUMLINES = " + numLines);
                gl.glBegin(GL.GL_LINE_STRIP);
                    for (int i = 0; i < numLines; i++) {
                        temp = in.readLine();//separar
                        s1 = temp.substring ( 0, temp.indexOf ( " " ) ); // pega o pedaço do inicio da string até o espaco
                        s2 = temp.substring ( temp.indexOf ( " " ) + 1 ); // pega do espaco pra frente
                        x = Integer.parseInt(s1);
                        y = Integer.parseInt(s2);
                        System.out.println("X = " + s1 + " | Y = " + s2);
                        //gl.glVertex2d(x, y);glVertex2d((x*esx)+deltax, (y*esy)+deltay);
                        gl.glVertex2d((x*esx)+deltax, (y*esy)+deltay);
                }
                gl.glEnd();
            }
            
        } catch (FileNotFoundException e1) {
            System.out.println(e1.toString() + "File not found!");
        } catch (IOException ioe) {
            System.out.println(ioe.toString());
        }
    }
}

