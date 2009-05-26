package src.modulo6.exemplos.complementos;

import com.sun.opengl.util.GLUT;
import javax.media.opengl.*;
import javax.media.opengl.glu.GLU;
import src.modulo6.complementos.Point3;
import src.modulo6.complementos.Vector3;

/**
 *
 * @author uel
 */
public class Camera {
    private GL gl;
    private GLU glu;
    private GLUT glut;

    private Point3 eye;
    private Vector3 u, v, n;
    private double viewAngle, aspect, nearDist, farDist; //view volume shape    

    /**
     * Construtor da camera
     */
    public Camera() {

    }

    public Camera(GL gl, GLU glu, GLUT glut){
        this.gl = gl;
        this.glu = glu;
        this.glut = glut;

        eye = new Point3();
        u = new Vector3();
        v = new Vector3();
        n = new Vector3();

        setModelViewMatrix();
    }

    /**
     * tell OpenGL where the camera is
     */
    public void setModelViewMatrix(){
        // load modelview matrix with existing camera values
        Vector3 eVec = new Vector3(eye.getX(), eye.getY(), eye.getZ());

        float m[] =
        { u.getX(), u.getY(), u.getZ(), -eVec.dot(u),//
          v.getX(), v.getY(), v.getZ(), -eVec.dot(v),//
          n.getX(), n.getY(), n.getZ(), -eVec.dot(n),//
          0.0f, 0.0f, 0.0f, 1.0f };

        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadMatrixf(m, 0);
    };


    public void roll(float angle) {
        float PI = 3.1416f;
        float cs = (float) (Math.cos(PI/180.0 * angle));
        float sn = (float) (Math.sin(PI / 180.0 * angle));
        Vector3 t = u;
        u.setVector(cs * t.getX() - sn * v.getX(), cs * t.getY() - sn * v.getY(), cs * t.getZ() - sn * v.getZ());
        v.setVector(sn * t.getX() + cs * v.getX(), sn * t.getY() + cs * v.getY(), sn * t.getZ() + cs * v.getZ());
        setModelViewMatrix();
    };

    public void pitch(float angle) {
        float PI = 3.1416f;
        float cs = (float) (Math.cos(PI / 180 * angle));
        float sn = (float) (Math.sin(PI / 180 * angle));
        Vector3 t = v; // remember old v
        v.setVector(cs * t.getX() - sn * n.getX(), cs * t.getY() - sn * n.getY(), cs * t.getZ() - sn * n.getZ());
        n.setVector(sn * t.getX() + cs * n.getX(), sn * t.getY() + cs * n.getY(), sn * t.getZ() + cs * n.getZ());
        setModelViewMatrix();
    };

    public void yaw(float angle) {
        float PI = 3.1416f;
        float cs = (float) (Math.cos(PI / 180 * angle));
        float sn = (float) (Math.sin(PI / 180 * angle));
        Vector3 t = n; // remember old v
        n.setVector(cs * t.getX() - sn * u.getX(), cs * t.getY() - sn * u.getY(), cs * t.getZ() - sn * u.getZ());
        u.setVector(sn * t.getX() + cs * u.getX(), sn * t.getY() + cs * u.getY(), sn * t.getZ() + cs * u.getZ());
        setModelViewMatrix();
    };

    public void slide(double du, double dv, double dn) {
        eye.setX((float) (eye.getX() + (du * u.getX()) + (dv * v.getX()) + (dn * n.getX())));
        eye.setY((float) (eye.getY() + (du * u.getY()) + (dv * v.getY()) + (dn * n.getY())));
        eye.setZ((float) (eye.getZ() + (du * u.getZ()) + (dv * v.getZ()) + (dn * n.getZ())));
//        eye.y += delU * u.y + delV * v.y + delN * n.y;
//        eye.z += delU * u.z + delV * v.z + delN * n.z;
        setModelViewMatrix();
    };

    public void set(Point3 Eye, Point3 look, Vector3 up){
        // make u, v, n vectors
        eye.setPoint3(Eye);
        n.setVector(eye.getX() - look.getX(), eye.getY() - look.getY(),eye.getZ() - look.getZ());
        u.setVector(up.cross(n));
        v.setVector(n.cross(u));
        u.normalize(); 
        v.normalize();
        n.normalize();
        setModelViewMatrix();
    };

    public void setShape(float vAngle, float asp, float nr, float fr) {
        viewAngle = vAngle;
        aspect = asp;
        nearDist = nr;
        farDist = fr;
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(viewAngle, aspect, nearDist, farDist);
        gl.glMatrixMode(GL.GL_MODELVIEW); // set its mode back again
    };

    public void setAspect(float asp){
        aspect = asp;
    };

    public void getShape(float viewAngle, float aspect,  float Near,  float Far){

    };

    public void rotAxes(Vector3 a, Vector3 b, float angle) {
        // rotate orthogonal vectors a (like x axis) and b(like y axia) through angle degrees
        float ang = (float) (3.14159265 / 180 * angle);
        float C = (float) Math.cos(angle);
        float S = (float) Math.sin(angle);
        Vector3 t = new Vector3(C * a.getX() + S * b.getX(), C * a.getY() + S * b.getY(), C * a.getZ() + S * b.getZ());
        b.setVector(-S * a.getX() + C * b.getX(), -S * a.getY() + C * b.getY(), -S * a.getZ() + C * b.getZ());
        a.setVector(t.getX(), t.getY(), t.getZ()); // put tmp into a'
    };

    public void setDefaultCamera(){

    };

}
