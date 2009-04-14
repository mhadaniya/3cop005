package src.modulo3.exemplos.canvas;

import javax.media.opengl.GL;

/**
 *
 * @author uel
 */
public class RealRect {
    private float left;
    private float right;
    private float bottom;
    private float top;

    public RealRect() {
        this.left = 0;
        this.right = 100;
        this.bottom = 0;
        this.top = 100;
    }

    public RealRect(float left, float right, float bottom, float top) {
        this.left = left;
        this.right = right;
        this.bottom = bottom;
        this.top = top;
    }

    public void setRealRect(float left, float right, float bottom, float top) {
        this.left = left;
        this.right = right;
        this.bottom = bottom;
        this.top = top;
    }

    //  void draw(void); //draw this rectangle using OpenGL
    void draw(GL gl){
        gl.glRectf(left, bottom, right, top);
    }


    public float getBottom() {
        return bottom;
    }

    public void setBottom(float bottom) {
        this.bottom = bottom;
    }

    public float getLeft() {
        return left;
    }

    public void setLeft(float left) {
        this.left = left;
    }

    public float getRight() {
        return right;
    }

    public void setRight(float right) {
        this.right = right;
    }

    public float getTop() {
        return top;
    }

    public void setTop(float top) {
        this.top = top;
    }

}
