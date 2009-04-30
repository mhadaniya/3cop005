package src.modulo4.complementos;

import java.io.*;
import java.util.StringTokenizer;

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
    private Point3[] vertices3d;
    private Face[] faces;

    public LeitorObj(String caminhoArquivo) {
        boolean continuaLeitura = true;
        arquivo = new File(caminhoArquivo);
        int countVertice = 0;
        int countF = 0;
        vertices3d = new Point3[1];

        try {
            fileInputStream = new FileInputStream(arquivo);
            inputStreamReader = new InputStreamReader(fileInputStream);
            bufferedReader = new BufferedReader(inputStreamReader);
            String stringAux;
            String linhaAux;
            StringTokenizer tokenizer;

            while(continuaLeitura){
                linha = bufferedReader.readLine();
                if(linha == null){
                    continuaLeitura = false;
                }else{
                    if(linha.contains("v ")){
                        countVertice = countVertice + 1;
                    }
                    if(linha.contains("f ")){
                        countF = countF + 1;
                    }
                    if (linha.contains("# ")){
                        System.out.println("Linha contem # " + linha);
                    }
                }
            }
            continuaLeitura = true;
            fileInputStream = new FileInputStream(arquivo);
            inputStreamReader = new InputStreamReader(fileInputStream);
            bufferedReader = new BufferedReader(inputStreamReader);

            vertices3d = new Point3[countVertice];
            int countAuxVertice = 0;
            int countAuxFace = 0;
            faces = new Face[countF];

            while(continuaLeitura){
                linha = bufferedReader.readLine();
                if(linha == null){
                    continuaLeitura = false;
                }else{
                    if(linha.contains("v ")){                                                
                        stringAux = linha;                        
                        float x = 0, y = 0, z = 0;

                        tokenizer = new StringTokenizer(stringAux);

                        if (tokenizer.countTokens() == 4){
                            tokenizer.nextToken();
                            x = Float.parseFloat(tokenizer.nextToken());
                            y = Float.parseFloat(tokenizer.nextToken());
                            z = Float.parseFloat(tokenizer.nextToken());
                        }
                        vertices3d[countAuxVertice] = new Point3(x, y, z);
                        countAuxVertice++;
//                        System.out.println("TAMANHO DO VERTICE - " + vertices3d.length);
                    }

                    if(linha.contains("f ")){
//                        stringAux = linha;
//                        float x = 0, y = 0, z = 0;
//
//                        tokenizer = new StringTokenizer(stringAux);
//
//                        if (tokenizer.countTokens() == 4){
//                            tokenizer.nextToken();
//                            x = Float.parseFloat(tokenizer.nextToken());
//                            y = Float.parseFloat(tokenizer.nextToken());
//                            z = Float.parseFloat(tokenizer.nextToken());
//                        }
//                        //vertices3d[countAuxFace] = new Point3(x, y, z);
//                        faces[countAuxFace] = new Face();
//                        countAuxFace++;
////                        System.out.println("TAMANHO DO VERTICE - " + vertices3d.length);
//                        countF = countF + 1;
                    }
                    if (linha.contains("# ")){
//                        System.out.println("Linha contem # " + linha);
                    }
                }
            }

            // CARREGANDO OS VERTICES NO ARRAY.
            System.out.println("Vertices = " + countVertice + " == " + countAuxVertice);

            System.out.println("F = " + countF);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        
    }




}