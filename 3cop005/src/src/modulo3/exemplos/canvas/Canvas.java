package src.modulo3.exemplos.canvas;

import com.sun.opengl.util.GLUT;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.glu.GLU;
/**
 *
 * @author uel
 */
public class Canvas extends GLCanvas{
    private GL gl;
    private GLU glu;
    private GLUT glut;

    private Point2 currentPosition; //current position in the world.
    private IntRect viewport;
    private RealRect window;
    private float CD;

    public Canvas() {
    }

   //constructor
    public Canvas(int width, int height, String windowTitle, GLAutoDrawable drawable) {
        gl = drawable.getGL();
        glu = new GLU();
        glut = new GLUT();

//        setWindow(0, (float) width, 0, (float) height); // default world window
//        setViewport(0, width, 0, height); //default viewport
//        CP.set(0.0f, 0.0f); //initialize the cp to (0,0)
    }

    public void moveTo(float x, float y) {
        currentPosition.setPoint2(x, y);
    }

    public void moveTo(Point2 p) //moves current point CP to point p object
    {
        float x1, y1;
        x1 = p.getX();
        y1 = p.getY();
        currentPosition.setPoint2(x1, y1);
    }

    void lineTo(Point2 p) {
      gl.glBegin(GL.GL_LINES);
          gl.glVertex2f(currentPosition.getX(), currentPosition.getY());
          gl.glVertex2f(p.getX(), p.getY());
      gl.glEnd();
      currentPosition.setPoint2(p.getX(), p.getY());
      gl.glFlush();
    }

    void lineTo(float x, float y) {
      gl.glBegin(GL.GL_LINES);
          gl.glVertex2f(currentPosition.getX(), currentPosition.getY());
          gl.glVertex2f(x, y);
      gl.glEnd();
      currentPosition.setPoint2(x, y);
      gl.glFlush();
    }

    public void setWindow(float left, float right, float bottom, float top){
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluOrtho2D(left, right, bottom, top);
    }

    public void setViewport(int left, int right, int bottom, int top){
        gl.glViewport(left, bottom, right - left, top - bottom);
    }

    public void setBackgroundColor(float red, float green, float blue) {
        gl.glClearColor(red, green, blue, 0.0f);
    }

    public void setColor(float red, float green, float blue) {
            gl.glColor3f(red, green, blue);
}

    public void forward(float dist, boolean isVisible) {
//        float RadPerDeg = 0.017453393f;          //radians per degree
        float RadPerDeg = (float) Math.PI/180; //radians per degree
        float x = (float) (currentPosition.getX() + dist * Math.cos(RadPerDeg * CD));
        float y = (float) (currentPosition.getY() + dist * Math.sin(RadPerDeg * CD));
    
        if (isVisible) {
            lineTo(x, y);
        } else {
            moveTo(x, y);
        }
    }//forward

    public void turnTo(float angle) {
        CD = angle; //set current direction
    }

//since the 'origin' is at left upper corner, our direction sense is reversed
//  so clockwise and anti-clockwise are reversed
    public void turn(float angle) {
      CD += angle; //turn anti-clockwise for positive angle
    }

    public void clearScreen(){

    }

    public void init(GLAutoDrawable drawable) {
        gl = drawable.getGL();
		glu = new GLU();

		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

		gl.glLineWidth(2.0f);

		gl.glViewport(-250, -150, 250, 150);
		gl.glMatrixMode(GL.GL_PROJECTION);
		gl.glLoadIdentity();
		glu.gluOrtho2D(-250.0, 250.0, -150.0, 150.0);
    }

    public float getCD() {
        return CD;
    }

    public void setCD(float CD) {
        this.CD = CD;
    }

    public Point2 getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(Point2 currentPosition) {
        this.currentPosition = currentPosition;
    }

    public IntRect getViewport() {
        return viewport;
    }

    public void setViewport(IntRect viewport) {
        this.viewport = viewport;
    }

    public RealRect getWindow() {
        return window;
    }

    public void setWindow(RealRect window) {
        this.window = window;
    }

}
