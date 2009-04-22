package src.modulo4.complementos;


/**
 *
 * @author uel
 */
public class Face {
    int nVerts;
    VertexID[] vert;

    public Face() {
        nVerts = 0;
        vert = null;
    }
       
    public int getNVerts() {
        return nVerts;
    }

    public void setNVerts(int nVerts) {
        this.nVerts = nVerts;
    }
}
