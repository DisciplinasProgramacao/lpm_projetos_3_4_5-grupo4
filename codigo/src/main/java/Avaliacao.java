public class Avaliacao {
    private Cliente cliente;
    Integer nota;
    String comentario;

    public Avaliacao(Cliente cliente, Integer nota) {
        this.cliente = cliente;
        this.nota = nota;
    }

    public Avaliacao(Cliente cliente, Integer nota, String comentario) {
        this.cliente = cliente;
        this.nota = nota;
        this.comentario = comentario;
    }

    public Cliente getCliente() {
        return cliente;
    }
}
