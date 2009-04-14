package src.modulo3.exemplos.canvas;

import javax.media.opengl.GL;

/**
 * single point w/ floating point coordinates
 * @author uel
 */
public class Point2 {
    private float x;
    private float y;

    public Point2() {
        this.x = 0.0f;
        this.y = 0.0f;
    }

    public Point2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void setPoint2(float x, float y){
        this.x = x;
        this.y = y;
    }
    
    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void draw(GL gl){
      gl.glBegin(GL.GL_POINTS); //draw this point
      gl.glVertex2f(x ,y);
      gl.glEnd();      
    }

}
