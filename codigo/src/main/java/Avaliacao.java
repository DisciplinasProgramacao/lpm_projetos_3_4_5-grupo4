public class Avaliacao {
    private Cliente cliente;
    Integer nota;
    String comentario;

    /**
     * Construtor que cria uma avaliação com o cliente e a nota atribuída.
     *
     * @param cliente O cliente que fez a avaliação.
     * @param nota    A nota atribuída à mídia na avaliação.
     */
    public Avaliacao(Cliente cliente, Integer nota) {
        this.cliente = cliente;
        this.nota = nota;
    }

    /**
     * Construtor que cria uma avaliação com o cliente, a nota atribuída e um comentário.
     *
     * @param cliente    O cliente que fez a avaliação.
     * @param nota       A nota atribuída à mídia na avaliação.
     * @param comentario O comentário relacionado à avaliação.
     */
    public Avaliacao(Cliente cliente, Integer nota, String comentario) {
        this.cliente = cliente;
        this.nota = nota;
        this.comentario = comentario;
    }

    public Cliente getCliente() {
        return cliente;
    }
}
