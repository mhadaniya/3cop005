package src.modulo4.complementos;

/**
 *
 * @author uel
 */
public class VertexID {
    private int vertIndex;
    private int normIndex;

    public VertexID() {
    }

    public VertexID(int vertIndex, int normIndex) {
        this.vertIndex = vertIndex;
        this.normIndex = normIndex;
    }

    public int getNormIndex() {
        return normIndex;
    }

    public void setNormIndex(int normIndex) {
        this.normIndex = normIndex;
    }

    public int getVertIndex() {
        return vertIndex;
    }

    public void setVertIndex(int vertIndex) {
        this.vertIndex = vertIndex;
    }

}
