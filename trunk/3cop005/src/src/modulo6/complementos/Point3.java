package src.modulo6.complementos;

/**
 *
 * @author uel
 */
public class Point3 {
    private float x;
    private float y;
    private float z;

    public Point3() {
    }

    public Point3(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void setPoint3(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void setPoint3(Point3 point3) {
        this.x = point3.getX();
        this.y = point3.getY();
        this.z = point3.getZ();
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

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }

}
