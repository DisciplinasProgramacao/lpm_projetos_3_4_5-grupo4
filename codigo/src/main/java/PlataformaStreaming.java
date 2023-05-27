import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class PlataformaStreaming {
    private String nome;
    private HashSet<Media> midias;
    private List<Cliente> clientes;
    private Cliente clienteAtual;

    /**
     * Cria uma nova instância de PlataformaStreaming com o nome fornecido.
     * @param nome o nome da plataforma de streaming
     */
    public PlataformaStreaming(String nome) {
        this.nome = nome;
        midias = new HashSet<>();
        clienteAtual = null;
        try {
            clientes = Cliente.carregarTodosClientes();
        } catch (Exception e) {
            clientes = new ArrayList<>();
        }
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

    public String getNome() {
        return nome;
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
    public boolean adicionarCliente(Cliente cliente) {
        if (cliente == null) return false;
        if (clientes.stream().anyMatch(c -> c.getNomeUsuario().equals(cliente.getNomeUsuario()))) return false;

        clientes.add(cliente);
        try {
            Cliente.salvarTodosClientes(clientes);
        } catch (IOException e) {
            System.out.println("Erro ao salvar clientes: " + e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * Filtra as mídias com base no modelo fornecido e no comparador fornecido.
     * @param comparator o comparador usado para filtrar as mídias
     * @param model o modelo usado para filtrar as mídias
     * @return a lista de mídias filtradas
     */
    private List<Media> filtrar(Comparator<Media> comparator, Media model) {
        return midias.stream().filter((media -> comparator.compare(media, model) == 0)).collect(Collectors.toList());
    }

    /**
     * @param comparator Comparator a ser utilizado para filtrar
     * @param model Media modelo com o genero que deseja filtrar
     * @return Lista de Medias com o mesmo genero de model
     */
    public List<Media> filtrarPorGenero(Comparator<Media> comparator, Media model) {
        return filtrar(comparator, model);
    }

    /**
     * @param comparator Comparator a ser utilizado para filtrar
     * @param model Media modelo com o idioma que deseja filtrar
     * @return Lista de Medias com o mesmo idioma de model
     */
    public List<Media> filtrarPorIdioma(Comparator<Media> comparator, Media model) {
        return filtrar(comparator, model);
    }

    /**
     * @param comparator Comparator a ser utilizado para filtrar
     * @param model Serie modelo com a quantidade de episódios que deseja filtrar
     * @return Lista de Series com a mesma quantidade de episódios de model
     */
    public List<Media> filtrarPorQtdEpisodios(Comparator<Serie> comparator, Serie model) {
        return midias.stream().filter((media -> {
            if (media instanceof Serie)
                return comparator.compare((Serie) media, model) == 0;
            return false;
        })).collect(Collectors.toList());
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
        if (midia != null) {
            midia.registrarAudiencia();
            clienteAtual.registrarAudiencia(midia);
        }
    }

    /**
     * Faz logoff do cliente atualmente logado.
     */
    public void logoff() {
        clienteAtual = null;
    }
}
