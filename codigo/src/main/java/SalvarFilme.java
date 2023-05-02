import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SalvarFilme {

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