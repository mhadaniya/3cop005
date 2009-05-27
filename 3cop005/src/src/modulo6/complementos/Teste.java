package src.modulo6.complementos;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.io.*;
import java.nio.*;

import javax.media.opengl.*;
import javax.media.opengl.glu.*;

import com.sun.opengl.util.*;

/**
 * This program uses the color matrix to exchange the color channels of an
 * image. <br>
 * <br>
 * Red -> Green <br>
 * Green -> Blue <br>
 * Blue -> Red
 *
 * @author Kiet Le (Java port)
 */
public class Teste
  extends JFrame
    implements GLEventListener//
    , KeyListener //
{
  private GLCapabilities caps;
  private GLCanvas canvas;


  private ByteBuffer pixels;
  // private int width; not reference as params...
  // private int height;...as are all Java primitives
  private Dimension dim = new Dimension(0, 0);

  public Teste()
  {
    super("colormatrix");

    caps = new GLCapabilities();
    canvas = new GLCanvas(caps);
    canvas.addGLEventListener(this);
    canvas.addKeyListener(this);

    add(canvas);
  }

  public void run()
  {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(640, 480);
    setLocationRelativeTo(null);
    setVisible(true);
    canvas.requestFocusInWindow();
  }

  public static void main(String[] args)
  {
    new Teste().run();
  }

  public void init(GLAutoDrawable drawable)
  {
    GL gl = drawable.getGL();

    float m[] =
    { 0.0f, 1.0f, 0.0f, 0.0f,//
      0.0f, 0.0f, 1.0f, 0.0f,//
      1.0f, 0.0f, 0.0f, 0.0f,//
      0.0f, 0.0f, 0.0f, 1.0f };

    pixels = readImage("Data/leeds.bin", dim);
    System.out.println(pixels.toString());

    gl.glPixelStorei(GL.GL_UNPACK_ALIGNMENT, 1);
    gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

    gl.glMatrixMode(GL.GL_COLOR);
    gl.glLoadMatrixf(m, 0);
    gl.glMatrixMode(GL.GL_MODELVIEW);

  }

  public void display(GLAutoDrawable drawable)
  {
    GL gl = drawable.getGL();

    gl.glClear(GL.GL_COLOR_BUFFER_BIT);

    gl.glRasterPos2i(1, 1);
    gl.glDrawPixels(dim.width, dim.height, //
        GL.GL_RGB, GL.GL_UNSIGNED_BYTE, pixels);

    gl.glFlush();

  }

  public void reshape(GLAutoDrawable drawable, int x, int y, int w, int h)
  {
    GL gl = drawable.getGL();

    gl.glViewport(0, 0, w, h);
    gl.glMatrixMode(GL.GL_PROJECTION);
    gl.glLoadIdentity();
    gl.glOrtho(0, w, 0, h, -1.0, 1.0);
    gl.glMatrixMode(GL.GL_MODELVIEW);
  }

  public void displayChanged(GLAutoDrawable drawable, boolean modeChanged,
      boolean deviceChanged)
  {
  }

  /**
   * Reads an image from an archived file and return it as ByteBuffer object.
   *
   * @author Mike Butler, Kiet Le
   */
  private ByteBuffer readImage(String filename, Dimension dim)
  {
    if (dim == null) dim = new Dimension(0, 0);
    ByteBuffer bytes = null;
    try
    {
      DataInputStream dis = new DataInputStream(getClass().getClassLoader()
          .getResourceAsStream(filename));
      dim.width = dis.readInt();
      dim.height = dis.readInt();
      System.out.println("Creating buffer, width: " + dim.width + " height: "
                         + dim.height);
      // byte[] buf = new byte[3 * dim.height * dim.width];
      bytes = BufferUtil.newByteBuffer(3 * dim.width * dim.height);
      for (int i = 0; i < bytes.capacity(); i++)
      {
        bytes.put(dis.readByte());
      }
      dis.close();

    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    bytes.rewind();
    return bytes;
  }

  public void keyTyped(KeyEvent key)
  {
  }

  public void keyPressed(KeyEvent key)
  {
    switch (key.getKeyChar()) {

      case KeyEvent.VK_ESCAPE:
        System.exit(0);
        break;

    }
  }

  public void keyReleased(KeyEvent key)
  {
  }

}