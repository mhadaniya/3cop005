package src.modulo8.complementos;

import java.awt.*;
import java.awt.image.*;
import java.awt.color.ColorSpace;
import java.awt.geom.AffineTransform;
import java.awt.event.*; 
import java.nio.ByteBuffer;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLDrawable;
import javax.media.opengl.GLDrawableFactory;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;

// import javax.swing.*;

public class JOGL2DBasics extends Frame implements GLEventListener {
    
    public static final Dimension PREFERRED_FRAME_SIZE =
        new Dimension (450,375);
    
    byte[] dukeRGBA;
    ByteBuffer checkImageBuf;
    int dukeHeight;
    int dukeWidth;
    
    public JOGL2DBasics() {
        // init Frame
        super ("Simple 2D with JOGL");
        System.out.println ("constructor");
        // get a GLCanvas
        GLCapabilities capabilities = new GLCapabilities();
        GLCanvas canvas = new GLCanvas(capabilities);
        // add a GLEventListener, which will get called when the
        // canvas is resized or needs a repaint
        canvas.addGLEventListener(this);
        // now add the canvas to the Frame.  Note we use BorderLayout.CENTER
        // to make the canvas stretch to fill the container (ie, the frame)
        add (canvas, BorderLayout.CENTER);
    }
    
    /** We'd like to be 600x400, please.
     */
    public Dimension getPreferredSize () {
        return PREFERRED_FRAME_SIZE;
    }
    
    /** main just creates and shows a JOGL2DBasics Frame
     */
    public static void main (String[] args) {
        Frame f = new JOGL2DBasics();
        f.pack();
        f.setVisible(true);
    }
    
    
    /*
     * METHODS DEFINED BY GLEventListener
     */
    public void init(GLAutoDrawable drawable) {
        System.out.println ("init()");

        GL gl = drawable.getGL();
        // set erase color
        gl.glClearColor( 1.0f, 1.0f, 1.0f, 1.0f ); //white
        // set drawing color and point size
        gl.glColor3f( 0.0f, 0.0f, 0.0f );
        gl.glPointSize(4.0f); //a 'dot' is 4 by 4 pixels
    }

    public void display(GLAutoDrawable drawable) {
        long inTime = System.currentTimeMillis();
        System.out.println ("display()");
        GL gl = drawable.getGL();
        GLU glu = new GLU();
        gl.glClear (GL.GL_COLOR_BUFFER_BIT);
        drawSomeDots(gl);
        drawSomeLines(gl);
        drawOpenPoly(gl);
        drawClosedPoly(gl);
        drawFilledRect (gl, Color.cyan.darker());
        drawFilledPoly (gl, Color.red.darker());
        drawTriangles (gl);
        drawImage (gl);
    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        System.out.println ("reshape()");
        GL gl = drawable.getGL();
        GLU glu = new GLU();
        gl.glViewport( 0, 0, width, height );
        gl.glMatrixMode( GL.GL_PROJECTION );
        gl.glLoadIdentity();
        glu.gluOrtho2D( 0.0, 450.0, 0.0, 375.0);
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
        System.out.println("> Display changed.");
    }
    
    
        
    /*
     * OUR HELPER METHODS
     */
    
    
    /** An example of drawing dots.
     */ 
    protected void drawSomeDots(GL gl) {
        gl.glBegin (GL.GL_POINTS);
        gl.glVertex2i (100,50);
        gl.glVertex2i (100,130);
        gl.glVertex2i (150,130);
        gl.glEnd ();
    }
    
    /** An example of drawing lines
     */
    protected void drawSomeLines (GL gl) {
        gl.glBegin (GL.GL_LINES);
        gl.glVertex2i (50, 200);
        gl.glVertex2i (75, 250);
        // same thing, but over ten pixels to the right
        gl.glVertex2i (60, 200);
        gl.glVertex2i (85, 250);
        gl.glEnd();
    }
    
    /** An example of drawing an open polyline.  The
        last point does NOT connect back to the first
    */
    protected void drawOpenPoly (GL gl) {
        gl.glBegin (GL.GL_LINE_STRIP);
        gl.glVertex2i (100, 200);
        gl.glVertex2i (150, 250);
        gl.glVertex2i (100, 250);
        gl.glVertex2i (150, 200);
        gl.glEnd();    
    }
    
    /** An example of drawing an open polyline.  The
        last point connects back to the first
    */
    protected void drawClosedPoly (GL gl) {
        gl.glBegin (GL.GL_LINE_LOOP);
        gl.glVertex2i (200, 200);
        gl.glVertex2i (250, 250);
        gl.glVertex2i (200, 250);
        gl.glVertex2i (250, 200);
        gl.glEnd();    
    }
    
    /** An example of drawing triangles with GL_TRIANGLES.
        Each three points is a new triangle.
    */
    protected void drawTriangles (GL gl) {
        gl.glBegin (GL.GL_TRIANGLES);
        
        gl.glVertex2i (400, 50);
        gl.glVertex2i (400, 100);
        gl.glVertex2i (420, 75);
        
        gl.glVertex2i (425, 50);
        gl.glVertex2i (425, 100);
        gl.glVertex2i (445, 75);
        
        gl.glEnd();    
    }
    
    
    /** Draws a rectangle with the given AWT color
     */
    protected void drawFilledRect (GL gl, Color c) {
        float redF = c.getRed() / 255f;
        float greenF = c.getGreen() / 255f;
        float blueF = c.getBlue() / 255f;
        gl.glColor3f (redF, greenF, blueF);
        gl.glRecti (200, 50, 250, 150);
        // now reset the color to black
        gl.glColor3f (0.0f, 0.0f, 0.0f);
    }
    
    
    /** Draws a polygon with the given AWT color
     */
    protected void drawFilledPoly (GL gl, Color c) {
        float redF = c.getRed() / 255f;
        float greenF = c.getGreen() / 255f;
        float blueF = c.getBlue() / 255f;
        gl.glColor3f (redF, greenF, blueF);
        gl.glBegin (GL.GL_POLYGON);
        gl.glVertex2i (300, 50);
        gl.glVertex2i (350, 60);
        gl.glVertex2i (375, 100);
        gl.glVertex2i (325, 115);
        gl.glVertex2i (300, 75);
        gl.glEnd();    
        // now reset the color to black
        gl.glColor3f (0.0f, 0.0f, 0.0f);
    }
    
    /** Loads "duke_wave.gif" and renders it into GL space
     */
    protected void drawImage (GL gl) {
        // load duke_wave.gif and get pixmap, if necessary
        if (dukeRGBA == null)
            loadDukeRGBA();
        System.out.println("> loadDukeRGBA() - 100%");
        // enable alpha mask (import from gif sets alpha bits)
        gl.glBlendFunc (GL.GL_SRC_ALPHA, GL.GL_ONE_MINUS_SRC_ALPHA);
        gl.glEnable (GL.GL_BLEND);
        System.out.println("> alpha mask - 100%");
        // draw a rectangle under part of duke to prove alpha works
        gl.glColor3f (0.0f, 0.5f, 0.0f);
        gl.glRecti (0, 300, 100, 330);
        gl.glColor3f (0.0f, 0.0f, 0.0f);
        System.out.println("> draw rectangle - 100%");
        // draw bytes into OpenGL
        gl.glRasterPos2i (10, 300);
        System.out.println("> gl.glRasterPos2i (10, 300) - 100%");
        gl.glDrawPixels(dukeWidth, dukeHeight, gl.GL_RGB, gl.GL_UNSIGNED_BYTE, checkImageBuf);
        System.out.println("> draw bytes - 100%");
    }
    
    
    @SuppressWarnings("static-access")
    protected void loadDukeRGBA () {
        Image i = Toolkit.getDefaultToolkit().createImage("duke_wave.gif");
        MediaTracker tracker = new MediaTracker(new Canvas());
        tracker.addImage (i, 0);
        try {
            tracker.waitForAll();
        } catch (InterruptedException ie) {}
        dukeHeight = i.getHeight(null);
        dukeWidth = i.getWidth(null);
        System.out.println ("Got Duke image, width=" +
                            dukeWidth + ", height=" + dukeHeight);
        
        // turn it into a BufferedImage
        WritableRaster raster = 
            Raster.createInterleavedRaster (DataBuffer.TYPE_BYTE,
                                            dukeWidth,
                                            dukeHeight,
                                            4,
                                            null);
        System.out.println("> WritableRaster - OK");
        ComponentColorModel colorModel=
            new ComponentColorModel (ColorSpace.getInstance(ColorSpace.CS_sRGB),
                                     new int[] {8,8,8,8},
                                     true,
                                     false,
                                     ComponentColorModel.TRANSLUCENT,
                                     DataBuffer.TYPE_BYTE);
        System.out.println("> ComponentColorModel - OK");

        BufferedImage dukeImg = 
            new BufferedImage (colorModel, // color model
                               raster,
                               false, // isRasterPremultiplied
                               null); // properties
        System.out.println("> BufferedImage - OK");
        
        Graphics2D g = dukeImg.createGraphics();
        // use an AffineTransformation to draw upside-down in the java
        // sense, which will make it right-side-up in OpenGL
        AffineTransform gt = new AffineTransform();
        gt.translate (0, dukeHeight);
        gt.scale (1, -1d);
        g.transform (gt);
        g.drawImage (i, null, null);
        System.out.println("> Translate - 100%");
        // now retrieve the underlying byte array from dukeImg
        DataBufferByte dukeBuf = (DataBufferByte)raster.getDataBuffer();
        System.out.println("> DataBufferByte - OK");
        dukeRGBA = dukeBuf.getData();
        System.out.println("> dukeRGBA = dukeBuf.getData(); - 100%");
        checkImageBuf.wrap(dukeRGBA);
        System.out.println("> checkImageBuf.wrap(dukeRGBA) - 100%");
        g.dispose();
        System.out.println("> dispose - OK");
    }
   
   
}
