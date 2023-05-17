import static java.util.Objects.nonNull;

public class Especialista {
    public void avaliarComComentario(String nomeMedia, int nota, String comentario, Cliente cliente) {
        Media media = cliente.listaJaVistas.stream().filter(s -> s.getNome().equals(nomeMedia)).findFirst().orElse(null);
        if (nonNull(media)) {
            Avaliacao avaliacao = new Avaliacao(cliente, nota, comentario);
            media.addAvaliacao(avaliacao);
        }
    }
}
