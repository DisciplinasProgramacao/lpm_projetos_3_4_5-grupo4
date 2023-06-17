import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class GeradorDeClientes {
    public static List<Cliente> gerarClientes() {
        List<Cliente> list = new ArrayList<>();
        try {
            List<String> allLines = Files.readAllLines(Paths.get("data/Espectadores.csv"));
            allLines.forEach(line -> {
                list.add(new Cliente(line.split(";")));
            });
        } catch (IOException e) {
            System.err.println("Erro ao carregar arquivo espectadores");
        }

        return list;
    }

    public static void recoverListas(Cliente cliente, List<Media> medias) {
        try {
            List<String> listas = Files.readAllLines(Paths.get("data/Audiencia.csv"));
            var clienteListas = listas.stream().filter(item -> {
                var splittedItem = item.split(";");
                return splittedItem[0].equals(cliente.getNomeUsuario());
            });
            clienteListas.forEach(item -> {
                var splittedItem = item.split(";");
                String op = splittedItem[1];
                Media media = medias.stream().filter(m -> m.getId().equals(Integer.parseInt(splittedItem[2]))).findFirst().orElse(null);
                if (op.equals("A")) cliente.registrarAudiencia(media);
                else if (op.equals("F")) cliente.adicionarNaLista(media);
            });
        } catch (IOException e) {
            System.err.println("Erro ao carregar arquivo audiencia");
        }
    }
}
