import static java.util.Objects.nonNull;

public class Especialista {
    /**
     * Avalia uma mídia com um comentário, atribuindo uma nota e associando ao cliente.
     *
     * @param nomeMedia  O nome da mídia a ser avaliada.
     * @param nota       A nota atribuída à mídia.
     * @param comentario O comentário relacionado à avaliação da mídia.
     * @param cliente    O cliente que está avaliando a mídia.
     */
    public void avaliarComComentario(String nomeMedia, int nota, String comentario, Cliente cliente) {
        Media media = cliente.listaJaVistas.stream().filter(s -> s.getMedia().getNome().equals(nomeMedia)).findFirst().orElse(null).getMedia();
        if (nonNull(media)) {
            Avaliacao avaliacao = new Avaliacao(cliente, nota, comentario);
            media.addAvaliacao(avaliacao);
        }
    }
}
