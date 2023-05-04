import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PlataformaStreaming {
    private String nome;
    private HashSet<Media> midias;
    private HashSet<Cliente> clientes;
    private Cliente clienteAtual;

    public PlataformaStreaming(String nome) {
        this.nome = nome;
        midias = new HashSet<>();
        clientes = new HashSet<>();
        clienteAtual = null;
    }

    public Cliente login(String nomeUsuario, String senha) {
        Optional<Cliente> cliente = clientes.stream().filter(c -> c.getNomeUsuario().equals(nomeUsuario) && c.getSenha().equals(senha)).findFirst();
        clienteAtual = cliente.orElse(null);

        return clienteAtual;
    }

    public void adicionarMidia(Media midia) {
        if (midia == null) return;
        midias.add(midia);
    }

    public void adicionarCliente(Cliente cliente) {
        if (cliente == null) return;
        clientes.add(cliente);
    }

    public List<Media> filtrarPorGenero(String genero) {
        return midias.stream().filter(m -> m.getGenero().equals(genero)).collect(Collectors.toList());
    }

    public List<Media> filtrarPorIdioma(String idioma) {
        return midias.stream().filter(m -> m.getIdioma().equals(idioma)).collect(Collectors.toList());
    }

    public List<Media> filtrarPorQtdEpisodios(int quantEpisodios) {
        return midias.stream().filter(m -> m instanceof Serie && ((Serie) m).getQuantidadeEpisodios().equals(quantEpisodios)).collect(Collectors.toList());

    }
    public Media buscarMidia(String nome) {
        Optional<Media> midia = midias.stream().filter(m -> m.getNome().equals(nome)).findFirst();

        return midia.orElse(null);
    }

    public void registrarAudiencia(String nomeMidia) {
        Media midia = buscarMidia(nomeMidia);
        if (midia != null) midia.registrarAudiencia();
    }

    public void logoff() {
        clienteAtual = null;
    }
}
