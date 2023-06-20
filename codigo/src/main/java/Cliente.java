import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class Cliente implements Serializable {
    private String nomeUsuario;
    private String senha;
    Set<Media> listaParaVer;
    List<ItemListaJaVista> listaJaVistas;
    PermissoesCliente permissoes;

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
        List<Media> listaVistas = new ArrayList<>();
        for (ItemListaJaVista media : listaJaVistas) {
            listaVistas.add(media.getMedia());
        }
        return listaVistas;
    }

    private void init(String nomeUsuario, String senha) {
        this.nomeUsuario = nomeUsuario;
        this.senha = senha;
        this.listaJaVistas = new ArrayList<>();
        this.listaParaVer = new HashSet<>();
        permissoes = new ClientePadrao();
    }

    /**
     * Construtor que cria um cliente com o nome de usuário e senha fornecidos.
     *
     * @param nomeUsuario O nome de usuário do cliente.
     * @param senha       A senha do cliente.
     */
    public Cliente(String nomeUsuario, String senha) {
        init(nomeUsuario, senha);
    }

    /**
     * Construtor que cria um cliente com o nome de usuário e senha fornecidos.
     *
     * @param nomeUsuario   O nome de usuário do cliente.
     * @param senha         A senha do cliente.
     * @param permissoes  A categoria de comentarista do cliente.
     */
    public Cliente(String nomeUsuario, String senha, PermissoesCliente permissoes) {
        init(nomeUsuario, senha);
        this.permissoes = permissoes;
    }

    /**
     * Construtor que cria um cliente com um array de strings
     *
     * @param splittedCliente Array de strings que representam um cliente
     */
    public Cliente(String[] splittedCliente) {
        init(splittedCliente[1], splittedCliente[2]);
    }

    /**
     * Adiciona uma nova mídia à lista de mídias para ver do cliente.
     *
     * @param media a mídia a ser adicionada
     */
    public boolean adicionarNaLista(Media media) {
        if (isNull(media)) return false;
        return listaParaVer.add(media);
    }

    /**
     * Remove uma mídia da lista de mídias para ver do cliente, com base no nome da
     * mídia.
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
        Optional<Generos> generoProcurado = Arrays.stream(Generos.values())
                .filter(g -> g.name().equalsIgnoreCase(genero))
                .findFirst();

        if (generoProcurado.isPresent()) {
            return listaParaVer.stream().filter(l -> l.getGenero().equals(generoProcurado.get().name()))
                    .collect(Collectors.toList());
        }
        return null;
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
        if (isNull(media)) return false;
        if (media.isLancamento() && !permissoes.podeLancamento()) return false;

        Optional<ItemListaJaVista> existe = listaJaVistas.stream()
                .filter(s -> s.getMedia().getId().equals(media.getId())).findFirst();
        if (existe.isPresent()) return false;

        media.registrarAudiencia();
        this.retirarDaLista(media.nome);
        this.listaJaVistas.add(new ItemListaJaVista(media, date));

        return true;
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
     * @param data  a data o qual foi visto
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
     * @throws IOException            se ocorrer um erro de I/O durante a leitura do
     *                                arquivo
     * @throws ClassNotFoundException se a classe do objeto lido do arquivo não for
     *                                encontrada
     */
    public static List<Cliente> carregarTodosClientes() throws IOException, ClassNotFoundException {
        File savedClientes = new File("data/clientes.dat");
        if (savedClientes.exists()) {
            GenericDao<Cliente> clienteDao = new GenericDao<>();
            return clienteDao.load(savedClientes.getPath());
        }
        return GeradorDeClientes.gerarClientes();
    }

    /**
     * Adiciona uma avaliação (nota) de uma mídia vista pelo cliente.
     *
     * @param nomeMedia o nome da mídia avaliada
     * @param nota      a nota atribuída à mídia pelo cliente
     */
    public boolean avaliar(String nomeMedia, int nota) {
        ItemListaJaVista item = listaJaVistas.stream().filter(s -> s.getMedia().getNome().equals(nomeMedia)).findFirst()
                .orElse(null);
        if (nonNull(item)) {
            Media media = item.getMedia();
            Avaliacao avaliacao = new Avaliacao(this, nota);
            media.addAvaliacao(avaliacao);

            return true;
        }

        return false;
    }

    /**
     * Verifica a quantidade de avaliações que são considerada validas para
     * qualificação de especialista
     * 
     * @return numero de avaliações validas
     */
    public int midiasValidasDeAvaliacao() {
        return (int) listaJaVistas.stream().filter(item -> item.isValid()).count();
    }

    /**
     * Avalia uma mídia com um comentário, atribuindo uma nota e associando ao
     * cliente.
     *
     * @param nomeMedia  O nome da mídia a ser avaliada.
     * @param nota       A nota atribuída à mídia.
     * @param comentario O comentário relacionado à avaliação da mídia.
     */
    public boolean avaliarComComentario(String nomeMedia, int nota, String comentario) {
        if (!permissoes.podeComentar()) return false;

        var jaViu = listaJaVistas.stream().filter(m -> m.getMedia().getNome().equals(nomeMedia)).findFirst();
        if (jaViu.isEmpty()) return false;

        var avaliacao = new Avaliacao(this, nota, comentario);
        jaViu.get().getMedia().addAvaliacao(avaliacao);

        return true;
    }

    public boolean equals(Cliente cliente) {
        return this.nomeUsuario.equals(cliente.getNomeUsuario());
    }

    private void setPermissoes(PermissoesCliente pc) {
        this.permissoes = pc;
    }

    public boolean tornarPadrao() {
        this.setPermissoes(this.permissoes.tornarPadrao());

        return true;
    }

    public boolean tornarEspecialista() {
        Date now = new Date();
        long trintaDiasMs = 30L * 24L * 60L * 60L * 1000L;

        long count = this.listaJaVistas.stream().filter(item -> now.getTime() - item.getDataVizualizacao().getTime() <= trintaDiasMs).count();
        if (count >= 5) {
            this.setPermissoes(this.permissoes.tornarEspecialista());
            return true;
        }

        return false;
    }

    public boolean tornarProfissional() {
        this.setPermissoes(this.permissoes.tornarProfissional());

        return true;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "nomeUsuario='" + nomeUsuario + '\'' +
                ", listaParaVer=" + listaParaVer +
                ", listaJaVistas=" + listaJaVistas +
                ", comentarista=" + permissoes +
                '}';
    }
}
