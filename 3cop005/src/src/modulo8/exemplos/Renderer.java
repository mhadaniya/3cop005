package src.modulo8.exemplos;

import java.awt.event.*;
import com.sun.opengl.util.GLUT;
import java.io.IOException;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;
import src.modulo8.complementos.TratadorDeImagem;


public class Renderer extends KeyAdapter implements GLEventListener{
    // Atributos
	private GL gl;
	private GLU glu;
    private GLUT glut;
	private GLAutoDrawable glDrawable;
    private int zoomH = 1;
    private int zoomV = 1;
    private int PosX = 0;
    private int PosY = 0;

    private TratadorDeImagem imagem;
 
    /**
	 * M�todo definido na interface GLEventListener e chamado pelo objeto no qual ser� feito o desenho
	 * logo ap�s a inicializa��o do contexto OpenGL.
	 */
    public void init(GLAutoDrawable drawable) {
        String nomeArquivo = "Wilderness sem contraste.bmp";
        String caminhoArquivo = System.getProperty("user.dir");
        System.out.println("Abrindo > " + caminhoArquivo + "/" + nomeArquivo);
                       
        try {
                      
            imagem = new TratadorDeImagem(caminhoArquivo + "/" + nomeArquivo);

            System.out.println("W: " + imagem.getWidth() + " - H:" + imagem.getHeight());

        } catch (IOException ex) {
            System.err.println("#Erro > " + ex.toString());            
        }

        glDrawable = drawable;
        gl = drawable.getGL();
        glu = new GLU();
        glut = new GLUT();
        
        System.out.println("INIT GL IS: " + gl.getClass().getName());

        // Enable VSync
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        gl.glShadeModel(GL.GL_FLAT);
        gl.glPixelStorei(GL.GL_UNPACK_ALIGNMENT, 1);
        
    }

    /**
	 * M�todo definido na interface GLEventListener e chamado pelo objeto no qual ser� feito o desenho
	 * para come�ar a fazer o desenho OpenGL pelo cliente.
	 */
    public void display(GLAutoDrawable drawable) {
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);
        gl.glPixelZoom(zoomH, zoomV);
        gl.glRasterPos2f(PosX, PosY);
        gl.glDrawPixels(imagem.getWidth(), imagem.getHeight(), GL.GL_RGB, GL.GL_UNSIGNED_BYTE, imagem.getCheckImageBuf());
//        gl.glMatrixMode(GL.GL_PROJECTION); // set the view volume shape
//        gl.glLoadIdentity();
//        gl.glOrtho((-2.0 * 64 / 48.0), (2.0 * 64 / 48.0), (-2.0) , (2.0), 0.1, 100);
//        gl.glMatrixMode(GL.GL_MODELVIEW); // position and aim the camera
//        gl.glLoadIdentity();
        
    }

    /**
	 * M�todo definido na interface GLEventListener e chamado pelo objeto no qual ser� feito o desenho
	 * quando o modo de exibi��o ou o dispositivo de exibi��o associado foi alterado.
	 */
    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {

    }

    /**
	 * M�todo definido na interface GLEventListener e chamado pelo objeto no qual ser� feito o desenho
	 * depois que a janela foi redimensionada.
	 */
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {

    }


    /**
	 * M�todo definido na interface KeyListener que est� sendo implementado que seja
	 * feita a sa�da do sistema quando for pressionada a tecla ESC.
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
   

}
