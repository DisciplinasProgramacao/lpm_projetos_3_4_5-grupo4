import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
public class SalvarFilme {

    /**
     * Salva o conteúdo de um ArrayList de strings em um arquivo de texto.
     * @param enderecoArquivo o endereço do arquivo de texto onde os dados serão salvos
     * @param textos o ArrayList de strings contendo os dados a serem salvos
     */
    public static void salvarTxt(String enderecoArquivo, ArrayList<String> textos) {
        try {
            File myObj = new File(enderecoArquivo);
            myObj.delete();
            myObj.createNewFile();
            FileWriter myWriter = new FileWriter(enderecoArquivo);
            for (String string : textos) {
                myWriter.write(string + "\n");
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

}
