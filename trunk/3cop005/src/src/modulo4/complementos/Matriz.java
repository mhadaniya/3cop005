package src.modulo4.complementos;

/**
 *
 * @author uel
 */
public class Matriz {
    private int linhas;
    private int colunas;
    private float matriz[][];


    public Matriz() {
        this.linhas = 2;
        this.colunas = 2;
        matriz = new float[linhas][colunas];

        for (int row = 0; row < linhas; row++) {
            for (int column = 0; column < colunas; column++) {
                matriz[row][column] = 1;
            }
        }
    }

    public void showMatriz(){
        for (int row = 0; row < 2; row++) {
            for (int column = 0; column < 2; column++) {
                System.out.print(" " + matriz[row][column] + " ");
            }
             System.out.println();
        }
    }

    public Matriz(int linhas, int colunas) {
        this.linhas = linhas;
        this.colunas = colunas;

        if((this.getLinhas() > 0)&&(this.getColunas() > 0)){
            matriz = new float[linhas][colunas];
        }
    }

   
    public int getColunas() {
        return colunas;
    }

    public void setColunas(int colunas) {
        if(colunas <= 0){
            return;
        }else{
            this.colunas = colunas;
        }
    }

    public int getLinhas() {
        return linhas;
    }

    public void setLinhas(int linhas) {
        if(linhas <= 0){
            return;
        }else{
            this.linhas = linhas;
        }
    }

}
