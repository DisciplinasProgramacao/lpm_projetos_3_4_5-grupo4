import java.io.*;
import java.util.List;

public class GenericDao<T> {


    /**
     * Salva uma lista de objetos serializáveis em um arquivo.
     *
     * @param list     a lista de objetos a ser salva
     * @param filePath o caminho do arquivo onde a lista será salva
     * @throws IOException se ocorrer um erro ao escrever o arquivo
     */
    public void save(List<T> list, String filePath) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filePath))) {
            out.writeObject(list);
        }
    }

    /**
     * Carrega uma lista de objetos serializáveis a partir de um arquivo.
     *
     * @param filePath o caminho do arquivo de onde a lista será carregada
     * @return a lista de objetos carregada a partir do arquivo
     * @throws IOException            se ocorrer um erro ao ler o arquivo
     * @throws ClassNotFoundException se a classe do objeto carregado não for encontrada
     */
    public List<T> load(String filePath) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filePath))) {
            return (List<T>) in.readObject();
        }
    }
}
