import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PlataformaStreaming {
    private String nome;
    private HashSet<Media> midias;
    private HashSet<Cliente> clientes;
    private Cliente clienteAtual;

    /**
     * Cria uma nova instância de PlataformaStreaming com o nome fornecido.
     * @param nome o nome da plataforma de streaming
     */
    public PlataformaStreaming(String nome) {
        this.nome = nome;
        midias = new HashSet<>();
        clientes = new HashSet<>();
        clienteAtual = null;
    }

    /**
     * Faz login do cliente com o nome de usuário e senha fornecidos.
     * @param nomeUsuario o nome de usuário do cliente
     * @param senha a senha do cliente
     * @return o cliente que fez login, ou null se as credenciais forem inválidas
     */
    public Cliente login(String nomeUsuario, String senha) {
        Optional<Cliente> cliente = clientes.stream().filter(c -> c.getNomeUsuario().equals(nomeUsuario) && c.getSenha().equals(senha)).findFirst();
        clienteAtual = cliente.orElse(null);

        return clienteAtual;
    }

    /**
     * Adiciona uma nova mídia à plataforma de streaming.
     * @param midia a mídia a ser adicionada
     */
    public void adicionarMidia(Media midia) {
        if (midia == null) return;
        midias.add(midia);
    }

    /**
     * Adiciona um novo cliente à plataforma de streaming.
     * @param cliente o cliente a ser adicionado
     */
    public void adicionarCliente(Cliente cliente) {
        if (cliente == null) return;
        clientes.add(cliente);
    }

    /**
     * Filtra as mídias com base no modelo fornecido e no comparador fornecido.
     * @param comparator o comparador usado para filtrar as mídias
     * @param model o modelo usado para filtrar as mídias
     * @return a lista de mídias filtradas
     */
    public List<Media> filtrar(Comparator<Media> comparator, Media model) {
        return midias.stream().filter((media -> comparator.compare(media, model) == 0)).collect(Collectors.toList());
    }

    /**
     * Busca uma mídia pelo nome fornecido.
     * @param nome o nome da mídia a ser buscada
     * @return a mídia encontrada, ou null se não for encontrada nenhuma mídia com o nome fornecido
     */
    public Media buscarMidia(String nome) {
        Optional<Media> midia = midias.stream().filter(m -> m.getNome().equals(nome)).findFirst();

        return midia.orElse(null);
    }

    /**
     * Registra a audiência de uma mídia pelo nome fornecido.
     * @param nomeMidia o nome da mídia cuja audiência será registrada
     */
    public void registrarAudiencia(String nomeMidia) {
        Media midia = buscarMidia(nomeMidia);
        if (midia != null) midia.registrarAudiencia();
    }

    /**
     * Faz logoff do cliente atualmente logado.
     */
    public void logoff() {
        clienteAtual = null;
    }
}
