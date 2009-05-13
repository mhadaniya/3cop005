package src.modulo5.exercicios.prog01;

import javax.swing.*;
import java.awt.*;
import javax.media.opengl.*;


public class Main {

    private Renderer renderer;

    public Main() {
        // Cria janela
		JFrame janela = new JFrame("Modulo05 - Programa01");
		janela.setBounds(50,100,500,500);
		janela.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		BorderLayout layout = new BorderLayout();
		Container caixa=janela.getContentPane();
		caixa.setLayout(layout);

		// Cria um objeto GLCapabilities para especificar o número de bits
		// por pixel para RGBA
		GLCapabilities c = new GLCapabilities();
		c.setRedBits(8);
		c.setBlueBits(8);
		c.setGreenBits(8);
		c.setAlphaBits(8);

        // Cria o objeto que irá gerenciar os eventos
		renderer = new Renderer();

		// Cria um canvas, adiciona na janela, e especifica o objeto "ouvinte"
		// para os eventos Gl, de mouse e teclado
		GLCanvas canvas = new GLCanvas(c);
		janela.add(canvas,BorderLayout.CENTER);
		canvas.addGLEventListener(renderer);
		canvas.addKeyListener(renderer);
		janela.setVisible(true);
		canvas.requestFocus();
    }

    /**
	 *
	 * Método main que apenas cria um objeto ExemploJava.
	 */
	public static void main(String args[])
	{
		Main main = new Main();
	}

}
