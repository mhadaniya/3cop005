package src.modulo2.exercicios;

import java.awt.event.*;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;

import com.sun.opengl.util.*;

public class Exercicio04 implements GLEventListener, MouseListener, MouseMotionListener, KeyListener {
    
    private GL gl;
    private GLU glu;

    private GLintPoint[] listaVertices;
    private int indiceLista;

    private int prevMouseX, prevMouseY;
    private float diffX, diffY;
    private boolean mouseRButtonDown = false;
    
    public static void main(String[] args) {
        Frame frame = new Frame("Exercicio 04 - Mouse e Teclado");
        GLCanvas canvas = new GLCanvas();

        canvas.addGLEventListener(new Exercicio04());
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
        // Interface para as funcoes OpenGL
        gl = drawable.getGL();
        glu = new GLU();       

        listaVertices = new GLintPoint[]{};
        indiceLista = 0;

        //Informacoes da placa gráfica
        System.err.println("INIT GL IS: " + gl.getClass().getName());
        System.err.println("GL_VENDOR: " + gl.glGetString(GL.GL_VENDOR));
        System.err.println("GL_RENDERER: " + gl.glGetString(GL.GL_RENDERER));
        System.err.println("GL_VERSION: " + gl.glGetString(GL.GL_VERSION));       

        // Enable VSync
        gl.setSwapInterval(1);

        // Setup the drawing area and shading mode
        gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f); //Cor de fundo branco
        gl.glColor3f(0.0f, 0.0f, 0.0f); //Cor do desenho
        gl.glPointSize(4.0f); //um ponto eh 4 x 4 pixels
        gl.glLineWidth(2.0f);
        gl.glShadeModel(GL.GL_FLAT);

        drawable.addMouseListener(this);
        drawable.addMouseMotionListener(this);
        drawable.addKeyListener(this);
    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        gl = drawable.getGL();
        glu = new GLU();

        if (height <= 0) { // avoid a divide by zero error!        
            height = 1;
        }
        final float h = (float) width / (float) height;
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluOrtho2D(0.0f, 640.0f, 480.0f, 0.0f);
        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    public void display(GLAutoDrawable drawable) {
        gl = drawable.getGL();

        // Clear the drawing area
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);
        // Reset the current matrix to the "identity"
        gl.glLoadIdentity();
        desenharVerticesLista(drawable);
        gl.glEnd();
        // Flush all drawing operations to the graphics card
        gl.glFlush();
    }

    public void desenharVerticesLista(GLAutoDrawable drawable){
        gl = drawable.getGL();

        // Clear the drawing area
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);

        if(listaVertices.length > 0){
            for (int i = 0; i < listaVertices.length; i++) {
                gl.glBegin(i);
                gl.glVertex2i(listaVertices[i].getX(), listaVertices[i].getY());
            }
            gl.glEnd();
        }            
        
        // Flush all drawing operations to the graphics card
        gl.glFlush();
    }

    /**
     * Limpa a tela
     * @param drawable
     */
    public void limparTela(){        
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        listaVertices = new GLintPoint[]{};
    }

    public void criaNovoVertice(int x, int y){
        GLintPoint[] listaAntiga = listaVertices;
        if(listaVertices.length > 0){
            listaVertices = new GLintPoint[listaAntiga.length + 1];

            for (int i = 0; i < listaVertices.length - 1; i++) {
                listaVertices[i] = new GLintPoint(listaAntiga[i].getX(), listaAntiga[i].getY());
            }
            listaVertices[listaAntiga.length] = new GLintPoint(x,y);
        }else{
            listaVertices = new GLintPoint[]{new GLintPoint(x,y)};
        }
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }

    public void mouseClicked(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public void mousePressed(MouseEvent e) {
        prevMouseX = e.getX();
        prevMouseY = e.getY();
        if ((e.getModifiers() & e.BUTTON3_MASK) != 0) {
          mouseRButtonDown = true;
        }
        System.out.println("Mouse: X - " + prevMouseX + " | Y - " + prevMouseY);
    }

    public void mouseReleased(MouseEvent e) {
        if ((e.getModifiers() & e.BUTTON3_MASK) != 0) {
             mouseRButtonDown = false;
        }
    }

    public void mouseEntered(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public void mouseExited(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public void mouseDragged(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        if (mouseRButtonDown) {
            diffX = (float) (x - prevMouseX);
            diffY = (float) (y - prevMouseY);
        } else {
            prevMouseX = x;
            prevMouseY = y;
        }
    }

    public void mouseMoved(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public void keyTyped(KeyEvent e) {
        // new UnsupportedOperationException("Not supported yet.");
    }

    @SuppressWarnings("static-access")
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {

            case 84: //T
                System.exit(1);
                break;

            case 27: //ESC
                System.exit(1);
                break;

            case 67: //C - cria um novo vertice
                System.out.println("Criar vertice");
                criaNovoVertice(prevMouseX, prevMouseY);
                break;

            case 65: //A
                System.out.println("February");
                break;

            case 77: //M
                System.out.println("February");
                break;

            case 76: //L
                System.out.println("February");
                break;

            case 82: //R
                System.out.println("Limpar tela");
                limparTela();
                break;

            default:
                System.out.println("Invalid month.");
                break;
        }

        System.out.println(String.format("Tecla: %s - Código Unicode: %d",
        e.getKeyText(e.getKeyCode()), e.getKeyCode()));

    }

    public void keyReleased(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }
}

