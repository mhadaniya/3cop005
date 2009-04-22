package src.modulo4.complementos;

import java.util.LinkedList;

/**
 *
 * @author uel
 */
public class Mesh {
    int numVerts;
    Point3[] pt;
    int numNormals;
    Vector3[] norm;
    int numFaces;
    Face[] face;
    LinkedList vertexList;
    LinkedList faceList;

    public Mesh() {
        vertexList = null;
        faceList = null;
    }

    // Desenha a figura
    public void draw() {
//        numFaces = faceList->getSize();
//        for ( int f = 0; f < numFaces; f++ ) {
//            LinkedListNode *n = faceList->elementAt(f);
//            int nVerts = n->qtdEl;
//            glBegin (GL_LINE_LOOP);
//                 for ( int v = 0; v < nVerts; v++ ) {
//                        LinkedListNode *p = vertexList->elementAt( n->el[v] - 1 );
//                        glVertex3f ( p->el[0]/50, p->el[1]/50, p->el[2]/50);
//                 }
//            glEnd();
//        }
    }

}
