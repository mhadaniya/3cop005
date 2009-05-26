package src.modulo6.complementos;

/**
 *
 * @author uel
 */
public class Vector3 {
    private float x;
    private float y;
    private float z;

    public Vector3() {
    }

    public Vector3(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void setVector(float x, float y, float z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void setVector(Vector3 vector3){
        this.x = vector3.getX();
        this.y = vector3.getY();
        this.z = vector3.getZ();
    }

   public void normalize(){
      double sizeSq = x * x + y * y + z * z;
	  if (sizeSq < 0.0000001) {
		//***	cerr << "\nnormalize() sees vector (0,0,0)!";
	    return; // does nothing to zero vectors;
	  }
	  float scaleFactor = (float) (1.0/(float)Math.sqrt(sizeSq));
	  x *= scaleFactor;
      y *= scaleFactor;
      z *= scaleFactor;
	}

     public Vector3 cross(Vector3 b) {
	   Vector3 c = new Vector3(this.y*b.getZ() - this.z*b.getY(), this.z*b.getX() - this.x*b.getZ(), this.x*b.getY() - this.y*b.getX());
	   return c;
	}

    public float dot(Vector3 b) {
      return (this.x * b.getX() + this.y * b.getY() + this.z * b.getZ());
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
