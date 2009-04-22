package src.modulo4.complementos;

import java.io.*;


/**
 *
 * @author uel
 */
public class LeitorObj {
    private File arquivo;
    private FileInputStream fileInputStream;
    private InputStreamReader inputStreamReader;
    private BufferedReader bufferedReader;
    private String linha;

    public LeitorObj(String caminhoArquivo) {
        boolean continuaLeitura = true;
        arquivo = new File(caminhoArquivo);
        int countVertice = 0;
        int countF = 0;

        try {
            fileInputStream = new FileInputStream(arquivo);
            inputStreamReader = new InputStreamReader(fileInputStream);
            bufferedReader = new BufferedReader(inputStreamReader);

            while(continuaLeitura){
                linha = bufferedReader.readLine();
                if(linha == null){
                    continuaLeitura = false;
                }else{
                    if(linha.contains("v ")){
//                        System.out.println(linha);
                        countVertice = countVertice + 1;
                    }
                    if(linha.contains("f ")){
//                        System.out.println(linha);
                        countF = countF + 1;
                    }
                    if (linha.contains("# ")){
                        System.out.println("Linha contem # " + linha);
                    }
                }
            }

            System.out.println("Vertices = " + countVertice);
            System.out.println("F = " + countF);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        
    }




}