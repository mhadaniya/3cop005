package src.modulo3.exemplos.canvas;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;
/**
 *
 * @author uel
 */
public class Canvas implements GLEventListener{
    private Point2 currentPosition; //current position in the world.
    private IntRect viewport;
    private RealRect window;
    private float CD;

    public Canvas() {
    }

   //constructor
    Canvas(int width, int height, String windowTitle) {
//        char*
//        argv[1]; //dummy argument list for glutinit()
//        char dummyString[8];
//        argv[0] = dummyString; //hook up the pointer
//        int argc = 1;
//
//        glutInit(&  argc, argv);
//        glutInitDisplayMode(GLUT_SINGLE | GLUT_RGB);
//        glutInitWindowSize(width, height);
//        glutInitWindowPosition(20, 20);
//        glutCreateWindow(windowTitle);
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
//      glBegin(GL_LINES);
//      glVertex2f((GLfloat) CP.getX(), (GLfloat) CP.getY());
//      glVertex2f((GLfloat) p.getX(), (GLfloat) p.getY());
//      glEnd();
//      currentPosition.setPoint2(p.getX(), p.getY());
//      glFlush();
    }

    public void setWindow(float left, float right, float bottom, float top){
//        glMatrixMode(GL_PROJECTION);
//        glLoadIdentity();
//        gluOrtho2D(left, right, bottom, top);
    }

    public void setViewport(int left, int right, int bottom, int top){
//        glViewport(left, bottom, right - left, top - bottom);
    }

    public void setBackgroundColor(float red, float green, float blue) {
//        glClearColor(red, green, blue, 0.0);
    }

    public void setColor(float red, float green, float blue) {
//            glColor3f(red, green, blue);
}

    public void forward(float dist, int isVisible) {
  //  const float RadPerDeg = 0.017453393;          //radians per degree
//      float RadPerDeg = PI / 180; //radians per degree
//      float x = currentPosition.getX() + dist * Math.cos(RadPerDeg * CD);
//      float y = currentPosition.getY() + dist * Math.sin(RadPerDeg * CD);
//      if (isVisible)
//        lineTo(x, y);
//      else
//        moveTo(x, y);
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
        GL gl = drawable.getGL();
		GLU glu = drawable.getGLU();

		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

		gl.glLineWidth(2.0f);

		gl.glViewport(-250, -150, 250, 150);
		gl.glMatrixMode(GL.GL_PROJECTION);
		gl.glLoadIdentity();
		glu.gluOrtho2D(-250.0, 250.0, -150.0, 150.0);
    }

    public void display(GLAutoDrawable drawable) {
        drawGraph(drawable.getGL());
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {        
    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {        
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
