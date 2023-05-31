import java.io.IOException;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;
import static java.util.Objects.nonNull;

public class Cliente implements Serializable {
    private String nomeUsuario;
    private String senha;
    Set<Media> listaParaVer;
    List<ItemListaJaVista> listaJaVistas;

    Especialista tipoCliente = null;

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public String getSenha() {
        return senha;
    }

    public Set<Media> getListaParaVer() {
        return listaParaVer;
    }

    public List<Media> getListaJaVistas() {
        List<Media> listaVistas  = new ArrayList<Media>();
        for (ItemListaJaVista media : listaJaVistas) {
            listaVistas.add(media.getMedia());
        }
        return listaVistas;
    }

    /**
     * Construtor que cria um cliente com o nome de usuário e senha fornecidos.
     *
     * @param nomeUsuario O nome de usuário do cliente.
     * @param senha       A senha do cliente.
     */
    public Cliente(String nomeUsuario, String senha) {
        this.nomeUsuario = nomeUsuario;
        this.senha = senha;
        this.listaJaVistas = new ArrayList<>();
        this.listaParaVer = new HashSet<>();
    }

    /**
     * Adiciona uma nova mídia à lista de mídias para ver do cliente.
     *
     * @param media a mídia a ser adicionada
     */
    public boolean adicionarNaLista(Media media) {
        return listaParaVer.add(media);
    }

    /**
     * Remove uma mídia da lista de mídias para ver do cliente, com base no nome da mídia.
     *
     * @param nomeMedia o nome da mídia a ser removida
     */
    public boolean retirarDaLista(String nomeMedia) {
        return listaParaVer.removeIf(l -> l.getNome().equals(nomeMedia));
    }

    /**
     * Filtra as mídias da lista de mídias para ver do cliente por gênero.
     *
     * @param genero o gênero pelo qual as mídias serão filtradas
     * @return uma lista de mídias filtradas por gênero
     */
    public List<Media> filtrarPorGenero(String genero) {
        return listaParaVer.stream().filter(l -> l.getGenero().equals(genero)).collect(Collectors.toList());
    }

    /**
     * Filtra as mídias da lista de mídias para ver do cliente por idioma.
     *
     * @param idioma o idioma pelo qual as mídias serão filtradas
     * @return uma lista de mídias filtradas por idioma
     */
    public List<Media> filtrarPorIdioma(String idioma) {
        return listaParaVer.stream().filter(l -> l.getIdioma().equals(idioma)).collect(Collectors.toList());
    }

    private boolean audiencia(Media media, Date date) {
        Optional<ItemListaJaVista> existe = listaJaVistas.stream().filter(s -> s.getMedia().getId().equals(media.getId())).findFirst();
        if (existe.isEmpty()) {
            media.registrarAudiencia();
            this.listaJaVistas.add(new ItemListaJaVista(media, date));
            this.isClienteEspecialista();
            return true;
        }

        return false;
    }

    /**
     * Registra a visualização de uma mídia pelo cliente.
     * Se a mídia já foi registrada como vista pelo cliente, não faz nada.
     *
     * @param media a mídia a ser registrada como vista
     */
    public boolean registrarAudiencia(Media media) {
        return audiencia(media, new Date());
    }

    /**
     * Registra a visualização de uma mídia pelo cliente.
     * Se a mídia já foi registrada como vista pelo cliente, não faz nada.
     *
     * @param media a mídia a ser registrada como vista
     * @param data a data o qual foi visto
     */
    public boolean registrarAudiencia(Media media, Date data) {
        return audiencia(media, data);
    }

    /**
     * Salva uma lista de clientes em um arquivo com o nome "clientes.dat".
     *
     * @param allClientes a lista de clientes a ser salva
     * @throws IOException se ocorrer um erro de I/O durante a escrita do arquivo
     */
    public static void salvarTodosClientes(List<Cliente> allClientes) throws IOException {
        GenericDao<Cliente> clienteDao = new GenericDao<>();
        clienteDao.save(allClientes, "data/clientes.dat");
    }

    /**
     * Carrega a lista de clientes salva no arquivo "clientes.dat".
     *
     * @return uma lista de clientes carregada do arquivo
     * @throws IOException se ocorrer um erro de I/O durante a leitura do arquivo
     * @throws ClassNotFoundException se a classe do objeto lido do arquivo não for encontrada
     */
    public static List<Cliente> carregarTodosClientes() throws IOException, ClassNotFoundException {
        GenericDao<Cliente> clienteDao = new GenericDao<>();
        return clienteDao.load("data/clientes.dat");
    }

    /**
     * Adiciona uma avaliação (nota) de uma mídia vista pelo cliente.
     *
     * @param nomeMedia o nome da mídia avaliada
     * @param nota a nota atribuída à mídia pelo cliente
     */
    public boolean avaliar(String nomeMedia, int nota) {
        ItemListaJaVista item = listaJaVistas.stream().filter(s -> s.getMedia().getNome().equals(nomeMedia)).findFirst().orElse(null);
        if (nonNull(item)) {
            Media media = item.getMedia();
            Avaliacao avaliacao = new Avaliacao(this, nota);
            media.addAvaliacao(avaliacao);

            return true;
        }

        return false;
    }

    /**
     * Verifica se o cliente esta qualificado como especialista. True para caso tenha 5 avaliações nos ultimos 30 dias.
     * 
     * @return se o cliente é especialista
     */
    public boolean isClienteEspecialista() {
        if (this.midiasValidasDeAvaliacao() >=5) {
            this.tipoCliente = new Especialista();
            return true;
        } else {
            this.tipoCliente = null;
            return false;
        }

    }

    /**
     * Verifica a quantidade de avaliações que são considerada validas para qualificação de especialista
     * @return numero de avaliações validas
     */
    public int midiasValidasDeAvaliacao(){
        return (int) listaJaVistas.stream().filter(item -> item.isValid()).count();
    }

    /**
     * Avalia uma mídia com um comentário, atribuindo uma nota e associando ao cliente.
     *
     * @param nomeMedia  O nome da mídia a ser avaliada.
     * @param nota       A nota atribuída à mídia.
     * @param comentario O comentário relacionado à avaliação da mídia.
     */

    public boolean avaliarComComentario(String nomeMedia, int nota, String comentario) {
        try {
            this.tipoCliente.avaliarComComentario(nomeMedia, nota, comentario, this);
            return true;
        } catch (NullPointerException e) {
            System.err.println("Voce deve ser cliente especialista para escrever comentario");
            return false;
        }
    }

    public boolean equals(Cliente cliente) {
        return this.nomeUsuario.equals(cliente.getNomeUsuario());
    }

}
