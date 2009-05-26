package src.modulo6.complementos;

/**
 *
 * @author uel
 */
public class Teste {

    public Teste() {
        LeitorObj leitorObj = new LeitorObj("c:\\obj\\cat.obj");

    }

    public static void main(String[] args){
        Teste teste = new Teste();

        Matriz matriz = new Matriz();

        matriz.showMatriz();
    }

}
